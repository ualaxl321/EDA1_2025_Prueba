package practicas.practica02.parte01;

import java.util.*;

import practicas.auxiliar.Format;

public class Estudiante implements Comparable<Estudiante>, Iterable<AsignaturaNotas>{
    private final String alumnoId;
    private final ArrayList<AsignaturaNotas> matricula;

    public Estudiante(String alumnoId) {
    	this.alumnoId = (alumnoId == null || alumnoId.isEmpty()) ? "sinIdentificación" : alumnoId.trim().toLowerCase();
    	matricula = new ArrayList<AsignaturaNotas>();
    }

    public void addAsignaturas(Asignatura... asignaturas) {
    	for (Asignatura asignatura : asignaturas) {
    		if (asignatura != null) {
    			if (matricula.indexOf(new AsignaturaNotas(asignatura)) == -1) matricula.add(new AsignaturaNotas(asignatura));
    		}
		}
    }

    public boolean addNotas(String asignaturaId, Double... notas) {
    	int pos = matricula.indexOf(new AsignaturaNotas(new Asignatura(asignaturaId, 0)));
    	if (pos == -1) return false;
    	matricula.get(pos).addNotas(notas);
        return true;
    }

    public String getNotaMedia() {
        double suma = .0;
        for (AsignaturaNotas notas : matricula) {
        	suma += Double.valueOf(notas.getNotaMedia());
		}
        return this.matricula.size() == 0 ? "0.00" : Format.formatDouble(suma / matricula.size());
    }

    public String getNotaMedia(String asignaturaId) {
    	int pos = this.matricula.indexOf(new AsignaturaNotas(new Asignatura(asignaturaId)));
    	if (pos == -1) return "-1.00";
        return  matricula.get(pos).getNotaMedia();
    }

    public void clear() {
    	for (AsignaturaNotas asignaturaNotas : matricula) {
            asignaturaNotas.clear();
        }
        this.matricula.clear();
    }

    @Override
    public String toString() {
        String result = "Estudiante con id = " + this.alumnoId + "\n";
        matricula.sort(new AsignaturaComp());
        for (AsignaturaNotas asignaturaNotas : matricula) {
        	String cuatrimestre;
        	switch (asignaturaNotas.getCuatrimestre()) {
		        case 1: cuatrimestre = "1º-1C"; break;
		        case 2: cuatrimestre = "1º-2C"; break;
		        case 3: cuatrimestre = "2º-1C"; break;
		        case 4: cuatrimestre = "2º-2C"; break;
		        case 5: cuatrimestre = "3º-1C"; break;
		        case 6: cuatrimestre = "3º-2C"; break;
		        case 7: cuatrimestre = "4º-1C"; break;
		        case 8: cuatrimestre = "4º-2C"; break;
		        default: cuatrimestre = "?º-?C";
		    }
        	String asignaturaId = asignaturaNotas.getAsignaturaId() + " (" + cuatrimestre + ") <" + "0" + "> -> " + asignaturaNotas.toString().split(" -> ")[1].split(" <")[0] + " <" + asignaturaNotas.getNotaMedia() + ">";
            result += "\t" + asignaturaId + "\n";
        }
        return result;
    }

    @Override
    public int compareTo(Estudiante other) {
        return this.alumnoId.compareTo(other.alumnoId);
    }

    @Override
    public Iterator<AsignaturaNotas> iterator() {
        return matricula.iterator();
    }
}