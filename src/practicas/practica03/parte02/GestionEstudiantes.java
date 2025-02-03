package practicas.practica03.parte02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import practicas.auxiliar.Format;
import practicas.practica02.parte01.Asignatura;

public class GestionEstudiantes {
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
		//...
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
						//...
						this.asignaturasOfertadas.put(itemsAux[0], asignatura);
					}
					break;
				case "@Estudiantes":
					for (int i = 0; i < Integer.parseInt(items[1]); i++) {
						String[] itemsAux = scan.nextLine().trim().split("[-]+");
						//...
						this.estudiantesMatriculados.put(new Estudiante(itemsAux[1], itemsAux[0]), estudiante);
					}
					break;
				case "@Notas":
	                for (int i=0; i < Integer.parseInt(items[1]); i++){
	                	String[] itemsAux = scan.nextLine().trim().split("[ \t]+");
	                	//Los estudiantes y las asignaturas deben existir (contenidos en this.estudiantesMatriculados y this.asignaturasOfertadas
	                	Double[] notas = //...
	                    //...
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
		//...
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
		//...
		return result;
	}
	
	public String getNotaMedia(String apellido, String nombre) {
		apellido = apellido.trim().toLowerCase();
		nombre = nombre.trim().toLowerCase();
		// Si el estudiante no existe se devuelve -1.00; 
		// Si existe, pero no está matriculado de ninguna asignatura, se devuelve 0.00
		//...
		ArrayList<Double> aux = new ArrayList<>();
		//1 for()
		return Format.formatDouble(MyMath.calculaMedia(aux));
	}
	
	public TreeSet<String> getAlumnosMatriculados(String asignaturaId) {
		asignaturaId = asignaturaId.toLowerCase().trim();
		//Si la asignatura no existe, se devuelve null
		TreeSet<String> result = new TreeSet<>();
		//1 for()
		//...
		return result;
	}
	
	public String getNotaMedia(String asignaturaId) {
		asignaturaId = asignaturaId.toLowerCase().trim();
		//...
		ArrayList<Double> aux = new ArrayList<>();
		//1 for()
		//...
		return Format.formatDouble(MyMath.calculaMedia(aux));
	}
	 
	public String getNotaMedia(String apellido, String nombre, String asignaturaId) {
		apellido = apellido.trim().toLowerCase();
		nombre = nombre.trim().toLowerCase();
		asignaturaId = asignaturaId.toLowerCase().trim();
		//...
		
	}
	
	public String getNotaMediaDocente(String docenteId) {
		ArrayList<Double> notasAux = new ArrayList<>();
		//2 for()
		//...
		return notasAux.isEmpty() ? "-1.00" : Format.formatDouble(MyMath.calculaMedia(notasAux));
	}
	
	public String toStringEstudiante(String apellido, String nombre) {
		apellido = apellido.trim().toLowerCase();
		nombre = nombre.trim().toLowerCase();
		//2 líneas
		//...
		 
	}
	
	public String toStringAsignatura(String asignaturaId) {
		asignaturaId = asignaturaId.trim().toLowerCase();
		//2 líneas
		//...
	}
	
	public String toStringDocentes(String asignaturaId, Comparator<String> comp) {
		asignaturaId = asignaturaId.trim().toLowerCase();
		//2 líneas
	}
	
	@Override
	public String toString() {
		return this.centroId;
	}
}