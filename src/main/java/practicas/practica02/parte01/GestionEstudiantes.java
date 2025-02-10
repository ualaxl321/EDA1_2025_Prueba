package practicas.practica02.parte01;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Format;


public class GestionEstudiantes {
    private final String centroId;
    private final AVLTree<Asignatura> asignaturasOfertadas;
    private final AVLTree<Estudiante> estudiantesMatriculados;

    public GestionEstudiantes(String centroId) {
        this.centroId = centroId.trim();
        this.asignaturasOfertadas = new AVLTree<>();
        this.estudiantesMatriculados = new AVLTree<>();
    }

    public void clear() {
        this.asignaturasOfertadas.clear();
        this.estudiantesMatriculados.clear();
    }

    public void addAsignaturas(Asignatura... asignaturas) {
    	for (Asignatura asignatura : asignaturas) {
			this.asignaturasOfertadas.add(asignatura);
		}
    }

    public void addEstudiantes(Estudiante... estudiantes){
    	for (Estudiante estudiante : estudiantes) {
    		this.estudiantesMatriculados.add(estudiante);
		}
    }

    public boolean addMatricula(String estudianteId, String...asignaturasId) {
    	if (this.estudiantesMatriculados.find(new Estudiante(estudianteId)) == null) return false;
    	for (String asignaturaId : asignaturasId) {
    		Asignatura asignatura = this.asignaturasOfertadas.find(new Asignatura(asignaturaId, 0));
    		if (asignatura != null)	this.estudiantesMatriculados.find(new Estudiante(estudianteId)).addAsignaturas(asignatura);
		}
        return true;
    }

    public boolean addNotas(String estudianteId, String asignaturaId, Double... notas){
    	Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
    	if (estudiante == null) return false;
    	return estudiante.addNotas(asignaturaId, notas);
    }

    public String getNotaMedia(String estudianteId) {
    	Estudiante estudiante = estudiantesMatriculados.find(new Estudiante(estudianteId));
    	if (estudiante != null) return estudiante.getNotaMedia();
    	return Format.formatDouble(-1.00);
    }

    public String getNotaMedia(String estudianteId, String asignaturaId) {
    	Estudiante estudiante = estudiantesMatriculados.find(new Estudiante(estudianteId));
        if (estudiante != null) return estudiante.getNotaMedia(asignaturaId);
        return "-1.00";
    }

    public String getNotaMediaAsignatura(String asignaturaId) {
    	double suma = .0;
    	int cont = 0;
    	for (Estudiante estudiante : estudiantesMatriculados) {
            String notaMedia = estudiante.getNotaMedia(asignaturaId);
            if (notaMedia == "-1.00") continue;
                suma += Double.valueOf(notaMedia);
                cont++;
        }
    	return cont == 0 ? "-1.00" : Format.formatDouble(suma/cont);
    }

    public String getEquipoDocenteEstudiante(String estudianteId) {
        ArrayList<String> result = new ArrayList<>();
        Estudiante estudianteCurr = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
        if (estudianteCurr == null) return "[]";
        for (AsignaturaNotas asignaturaNotas : estudianteCurr) {
        	ArrayList<String> docentes = asignaturaNotas.getDocentes();
            for (String docente : docentes) {
                if (!result.contains(docente)) result.add(docente);
            }
        }
        return result.toString();
    }

    public boolean load(String fileName) {
        Scanner scan;
        String line;
        String[] items;
        this.asignaturasOfertadas.clear();
        this.estudiantesMatriculados.clear();
        try {
            scan = new Scanner(new File(fileName));
        } catch (IOException e) {
            return false; 
        }
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("#")) continue;
            if (line.startsWith("@")) {
                items = line.split("[ ]+");
                int iter = Integer.valueOf(items[1]);
                switch (items[0]){
                    case "@Asignaturas":
                    	for (int i = 0; i < iter; i++) {
                            items = scan.nextLine().trim().split("[ \t]+");
                            Asignatura asignatura = new Asignatura(items[0], Integer.valueOf(items[1]));
                            asignatura.addDocentes(Arrays.copyOfRange(items, 2, items.length));
                            this.asignaturasOfertadas.add(asignatura);
                        }
                        break;
                    case "@Estudiantes":
                        for (int i = 0; i < iter; i++) {
                            this.estudiantesMatriculados.add(new Estudiante(scan.nextLine().trim()));
                        }
                        break;
                    case "@Matrículas":
                        for (int i = 0; i < iter; i++) {
							items = scan.nextLine().trim().split("[ \t]+");
							for (int j = 1; j < items.length; j++) {
								addMatricula(items[0], items[j]);
							}
						}
                        break;
                    case "@Notas":
                    	for (int i = 0; i < iter; i++) {
							items = scan.nextLine().trim().split("[ \t]+");
							for (int j = 2; j < items.length; j++) {
								addNotas(items[0], items[1], Double.valueOf(items[j]));
							}
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

    @Override
    public String toString() {
        return this.centroId;
    }
}