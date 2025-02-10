package examenes.examenEnero2025.practica2;

import java.util.ArrayList;
import java.util.Comparator;

public class Asignatura implements Comparable<Asignatura>{
    private final String asignaturaId;
    private final int cuatrimestre;
    private final ArrayList<String> docentesId;
    private final ArrayList<Integer> valoraciones;

    public Asignatura(String asignaturaId, int cuatrimestre) {
    	this.asignaturaId = (asignaturaId == null || asignaturaId.trim().isEmpty()) ? "sinNombre" : asignaturaId.toLowerCase().trim();
    	this.cuatrimestre = (cuatrimestre >= 1 && cuatrimestre <= 8) ? cuatrimestre : 0;
    	this.docentesId = new ArrayList<String>();
    	this.valoraciones = new ArrayList<Integer>();
    }

    public Asignatura(String asignaturaId) {
        this(asignaturaId, 0);
    }
    
    public int getCuatrimestre() {
        return this.cuatrimestre;
    }

    public String getAsignaturaId() {
        return this.asignaturaId;
    }

    public void addDocentes(String... docentesId) {
    	for (String docente : docentesId) {
    		if (docente != null) {
    			String docentito = docente.trim().toLowerCase();
    			if (!docentito.isEmpty() && !this.docentesId.contains(docentito)) this.docentesId.add(docentito);
    		}
		}
    }

    public void addValoraciones(Integer... valoraciones) {
    	for (int i = 0; i < valoraciones.length; i++) {
			if (valoraciones[i] == null) this.valoraciones.add(0);
			else if (valoraciones[i] < 0 || valoraciones[i] > 10) continue;
			this.valoraciones.add(valoraciones[i]);
		}
    }
    
    public int getValoracionMaxima() {
    	int result = Integer.MIN_VALUE;
    	if (valoraciones.isEmpty()) return 0;
    	for (int valoracion : valoraciones) {
			if(valoracion>result) result = valoracion;
		}
    	return result;
    }
    
    public boolean esDocente(String docenteId) {
    	if (docenteId == null || docenteId.isEmpty()) return false;
		return this.docentesId.contains(docenteId.trim().toLowerCase());
    }

    public ArrayList<String> getDocentes(Comparator<String> comp) {
    	ArrayList<String> docente = new ArrayList<String>(docentesId);
    	docente.sort(comp);
    	return docente;
    }
    
    public void clear() {
        docentesId.clear();
        valoraciones.clear();
    }

    @Override
    public String toString() {
        String cuatrimestre;
        switch (this.cuatrimestre) {
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
        
        int valoracionMaxima = getValoracionMaxima();
        return asignaturaId + " (" + cuatrimestre + ") <" +  valoracionMaxima + ">";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Asignatura)) return false; 
        return this.compareTo((Asignatura)other) == 0; 
    }

    @Override
    public int compareTo(Asignatura other) {
        return this.asignaturaId.compareTo(other.asignaturaId);
    }
}