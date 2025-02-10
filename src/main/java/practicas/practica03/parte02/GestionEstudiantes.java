package practicas.practica03.parte02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import practicas.auxiliar.Format;
import practicas.practica02.parte01.Asignatura;

//IMPORTANTE AÑADIR PARA P4P2 EL IMPLEMENTS ITERABLE
public class GestionEstudiantes implements Iterable<Entry<Estudiante, TreeMap<Estudiante, Integer>>> {
	private final String centroId;
	private final TreeMap<String, Asignatura> asignaturasOfertadas = new TreeMap<>();
	private final TreeMap<Estudiante, Estudiante> estudiantesMatriculados = new TreeMap<>();

	private final TreeMap<Estudiante, TreeMap<Asignatura, ArrayList<Double>>> datos = new TreeMap<>();

	public GestionEstudiantes(String centroId) {
		this.centroId = centroId;
	}

	public boolean load(String fileName) {
		Scanner scan;
		String line;
		String[] items;
		
		this.datos.clear();
		this.asignaturasOfertadas.clear();
		this.estudiantesMatriculados.clear();
		
		try {
			scan = new Scanner(new File(fileName));
		} catch (IOException e) {
			return false;
		}
		while (scan.hasNextLine()) {
			line = scan.nextLine().trim();
			if (line.isEmpty())
				continue;
			if (line.startsWith("#"))
				continue;
			if (line.startsWith("@")) {
				items = line.split("[ ]+");
				switch (items[0]) {
				case "@Asignaturas":
					for (int i = 0; i < Integer.parseInt(items[1]); i++) {
						String[] itemsAux = scan.nextLine().trim().split("[ ]+");
						
						Asignatura asignatura = new Asignatura(itemsAux[0], Integer.parseInt(itemsAux[1]));
						asignatura.addValoraciones(Integer.parseInt(itemsAux[2]));
						asignatura.addDocentes(Arrays.copyOfRange(itemsAux, 3, itemsAux.length));
						
						this.asignaturasOfertadas.put(itemsAux[0], asignatura);
					}
					break;
				case "@Estudiantes":
					for (int i = 0; i < Integer.parseInt(items[1]); i++) {
						String[] itemsAux = scan.nextLine().trim().split("[-]+");
						
						Estudiante estudiante = new Estudiante(itemsAux[1].trim(), itemsAux[0].trim());
						estudiante.setTelefono(itemsAux[2].trim());
				        estudiante.setDireccion(itemsAux[3].trim());
						
						this.estudiantesMatriculados.put(new Estudiante(itemsAux[1], itemsAux[0]), estudiante);
					}
					break;
				case "@Notas":
	                for (int i=0; i < Integer.parseInt(items[1]); i++){
	                	String[] itemsAux = scan.nextLine().trim().split("[ \t]+");
	                	//Los estudiantes y las asignaturas deben existir (contenidos en this.estudiantesMatriculados y this.asignaturasOfertadas
	                	
	                	Estudiante estudiante = this.estudiantesMatriculados.get(new Estudiante(itemsAux[1], itemsAux[0]));
	                	if (estudiante == null) continue;
	                	
	                	Asignatura asignatura = this.asignaturasOfertadas.get(itemsAux[2]);
	                	if (asignatura == null) continue;
	                	
	                	Double[] notas = new Double[itemsAux.length];
	                	for (int j = 3; j < itemsAux.length; j++) {
	                		notas[j] = Double.parseDouble(itemsAux[j]);
	                	}
	                	notas = Arrays.copyOfRange(notas, 3, notas.length);
	                	
	                    this.add(estudiante, asignatura, notas);
	                }
					break;
				default:
					return false;
				}
			}
		}
		scan.close();
		return true;
	}

	public void add(Estudiante estudiante, Asignatura asignatura, Double... notas) {
		// 0 containsKey()
		// 2 get()
		// 2 put()
		// 0 for()

		TreeMap<Asignatura, ArrayList<Double>> asignaturasDelEstudiante = this.datos.get(estudiante);
		if (asignaturasDelEstudiante == null) this.datos.put(estudiante, asignaturasDelEstudiante = new TreeMap<>());
		
		ArrayList<Double> notasDeLaAsignatura = asignaturasDelEstudiante.get(asignatura);
		if (notasDeLaAsignatura == null) asignaturasDelEstudiante.put(asignatura, notasDeLaAsignatura = new ArrayList<>());
		
		notasDeLaAsignatura.addAll(Arrays.asList(notas));
	}
	
	public void clear() {
		this.datos.clear();
		this.asignaturasOfertadas.clear();
		this.estudiantesMatriculados.clear();
	}
	
	public int size() {
		return this.datos.size();
	}
	
	public int getNumEstudiantesMatriculados() {
		return this.estudiantesMatriculados.size();
	}
	
	public int getNumAsignaturasOfertadas() {
		return this.asignaturasOfertadas.size();
	}
	
	public TreeSet<String> getAsignaturasMatriculadas(String apellido, String nombre) {
		apellido = apellido.trim().toLowerCase();
		nombre = nombre.trim().toLowerCase();
		// 2 get()
		//1 for()
		TreeSet<String> result = new TreeSet<>();
		
		Estudiante estudiante = this.estudiantesMatriculados.get(new Estudiante(apellido, nombre));
		if (estudiante == null) return null;
		
		TreeMap<Asignatura, ArrayList<Double>> asignaturasDelEstudiante = this.datos.get(estudiante);
		if (asignaturasDelEstudiante == null) return new TreeSet<>();
		
		for (Asignatura asignatura : asignaturasDelEstudiante.keySet()) {
			result.add(asignatura.getAsignaturaId());
		}
		
		return result;
	}
	
	public String getNotaMedia(String apellido, String nombre) {
		apellido = apellido.trim().toLowerCase();
		nombre = nombre.trim().toLowerCase();
		// Si el estudiante no existe se devuelve -1.00; 
		// Si existe, pero no está matriculado de ninguna asignatura, se devuelve 0.00
		ArrayList<Double> aux = new ArrayList<>();
		//1 for()
		Estudiante estudiante = this.estudiantesMatriculados.get(new Estudiante(apellido, nombre));
		if (estudiante == null) return "-1.00";
		
		TreeMap<Asignatura, ArrayList<Double>> asignaturasDelEstudiante = this.datos.get(estudiante);
		if (asignaturasDelEstudiante == null || asignaturasDelEstudiante.isEmpty()) return "0.00";
		
		for (ArrayList<Double> notasDeLaAsignatura : asignaturasDelEstudiante.values()) {
			if (notasDeLaAsignatura != null && !notasDeLaAsignatura.isEmpty()) aux.addAll(notasDeLaAsignatura);
		}
		
		if (aux.isEmpty()) return "0.00";
		return Format.formatDouble(MyMath.calculaMedia(aux));
	}
	
	public TreeSet<String> getAlumnosMatriculados(String asignaturaId) {
		asignaturaId = asignaturaId.toLowerCase().trim();
		//Si la asignatura no existe, se devuelve null
		TreeSet<String> result = new TreeSet<>();
		//1 for()
		Asignatura asignaturasDelEstudianteMatriculado = this.asignaturasOfertadas.get(asignaturaId);
		if (asignaturasDelEstudianteMatriculado == null) return null;
		
		for (Entry<Estudiante, TreeMap<Asignatura, ArrayList<Double>>> estudiante : this.datos.entrySet()) {
			if (estudiante.getValue().containsKey(asignaturasDelEstudianteMatriculado)) result.add(estudiante.getKey().getNombreApellidos());
		}
		
		return result;
	}
	
	public String getNotaMedia(String asignaturaId) {
		asignaturaId = asignaturaId.toLowerCase().trim();
		ArrayList<Double> aux = new ArrayList<>();
		//1 for()
		Asignatura asignaturaDelEstudianteMatriculado = this.asignaturasOfertadas.get(asignaturaId);
		if (asignaturaDelEstudianteMatriculado == null) return "-1.00";
		
		for (TreeMap<Asignatura, ArrayList<Double>> notasDelEstudiante : this.datos.values()) {
			ArrayList<Double> notas = notasDelEstudiante.get(asignaturaDelEstudianteMatriculado);
			if (notas != null) aux.addAll(notas);
		}
		
		if (aux.isEmpty()) return "0.00";
		return Format.formatDouble(MyMath.calculaMedia(aux));
	}
	 
	public String getNotaMedia(String apellido, String nombre, String asignaturaId) {
		apellido = apellido.trim().toLowerCase();
		nombre = nombre.trim().toLowerCase();
		asignaturaId = asignaturaId.toLowerCase().trim();
		
		Estudiante estudiante = this.estudiantesMatriculados.get(new Estudiante(apellido, nombre));
		if (estudiante == null) return "-1.00";
		
		TreeMap<Asignatura, ArrayList<Double>> asignaturas = this.datos.get(estudiante);
		if (asignaturas == null || asignaturas.isEmpty()) return "0.00";
		
		Asignatura asignatura = this.asignaturasOfertadas.get(asignaturaId);
		if (asignatura == null) return "-1.00"; 
		
		ArrayList<Double> notas = asignaturas.get(asignatura);
		if (notas == null || notas.isEmpty()) return "-1.00";
		return Format.formatDouble(MyMath.calculaMedia(notas));
	}
	
	public String getNotaMediaDocente(String docenteId) {
		ArrayList<Double> notasAux = new ArrayList<>();
		//2 for()
		docenteId = docenteId.trim().toLowerCase();
		for (Asignatura asignatura : this.asignaturasOfertadas.values()) {
	        if (asignatura.getDocentes(null).contains(docenteId)) {
	            for (TreeMap<Asignatura, ArrayList<Double>> asignaturasDelEstudiante : this.datos.values()) {
	                ArrayList<Double> notas = asignaturasDelEstudiante.get(asignatura);
	                if (notas != null && !notas.isEmpty()) notasAux.addAll(notas);
	            }
	        }
	    }
		return notasAux.isEmpty() ? "-1.00" : Format.formatDouble(MyMath.calculaMedia(notasAux));
	}
	
	public String toStringEstudiante(String apellido, String nombre) {
		apellido = apellido.trim().toLowerCase();
		nombre = nombre.trim().toLowerCase();
		//2 líneas
		
		if (!this.estudiantesMatriculados.containsKey(new Estudiante(apellido, nombre))) return "<estudiante desconocido>";
		return this.estudiantesMatriculados.get(new Estudiante(apellido, nombre)).toString();
	}
	
	public String toStringAsignatura(String asignaturaId) {
		asignaturaId = asignaturaId.trim().toLowerCase();
		//2 líneas
		Asignatura asignatura = this.asignaturasOfertadas.get(asignaturaId);
		return asignatura == null ? "<asignatura desconocida>" : asignatura.toString();
	}
	
	public String toStringDocentes(String asignaturaId, Comparator<String> comp) {
		asignaturaId = asignaturaId.trim().toLowerCase();
		//2 líneas
		Asignatura asignatura = this.asignaturasOfertadas.get(asignaturaId);
		if (asignatura == null) return "<asignatura desconocida>";
		
		TreeSet<String> docentes = new TreeSet<>(comp);
		for (String docente : asignatura.getDocentes(comp)) {
			if (docente.matches("[a-zA-Z]+")) docentes.add(docente.trim().toLowerCase());
	    }
		return docentes.isEmpty() ? "" : docentes.toString();
	}
	
	@Override
	public String toString() {
		return this.centroId;
	}

	//PRÁCTICA 4 PARTE 2
	public Iterator<Entry<Estudiante, TreeMap<Estudiante, Integer>>> iterator() {
		TreeMap<Estudiante, TreeMap<Estudiante, Integer>> result = new TreeMap<>();
	    TreeMap<Estudiante, Integer> mapa;
	    
	    for (Entry<Estudiante, TreeMap<Asignatura, ArrayList<Double>>> estudiante: this.datos.entrySet()) {
	    	mapa = new TreeMap<>();
	    	for (Entry<Estudiante, TreeMap<Asignatura, ArrayList<Double>>> estudianteActual : this.datos.entrySet()) {
	    		if (estudiante.equals(estudianteActual)) continue;
	    		if (result.containsKey(estudianteActual.getKey())) continue;
	    		
	    		TreeSet<Asignatura> aux = new TreeSet<>(estudiante.getValue().keySet());
	    		aux.retainAll(estudianteActual.getValue().keySet());
	    		if (aux.size() == 0) continue;
	    		mapa.put(estudianteActual.getKey(), aux.size());
	    	}
	    	if (!mapa.isEmpty()) result.put(estudiante.getKey(), mapa);
	    }
		return result.entrySet().iterator();
	}
}