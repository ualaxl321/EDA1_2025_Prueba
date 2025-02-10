package practicas.practica02.parte01;

import java.util.ArrayList;

import practicas.auxiliar.Format;

public class AsignaturaNotas implements Comparable<AsignaturaNotas> {
  
	private final Asignatura asignatura;
    private final ArrayList<String> notas;

    public AsignaturaNotas(Asignatura asignatura) {
    	this.asignatura = asignatura;
    	notas = new ArrayList<String>();
    }
    
    public AsignaturaNotas(String asignaturaId) {
    	this.asignatura = new Asignatura(asignaturaId, 0);
    	this.notas = new ArrayList<String>();
    }
    
    public int getCuatrimestre() {
    	return this.asignatura.getCuatrimestre();
    }
    
    public String getAsignaturaId() {
    	return this.asignatura.getAsignaturaId();
    }
    
    public void addNotas(Double... notas) {
    	for (Double nota : notas) {
			if (nota == null) {
				this.notas.add(Format.formatDouble (0.00));
			} else {
				this.notas.add(Format.formatDouble(nota));
			}
		}
    }

    public String getNotaMedia() {
        double suma = 0.0;
        for (String nota : notas) {
        	suma += (nota == null || nota.equals("0.00")) ? 0.0 : Double.valueOf(nota);
		}
        return Format.formatDouble(notas.isEmpty() ? 0.0 : suma / notas.size());
    }

    public void clear() {
        this.notas.clear();
    }
    
    public ArrayList<String> getDocentes() {
    	return this.asignatura.getDocentes(null);
    }

    @Override
    public String toString() {
    	 return asignatura + " -> " + notas + " <" + getNotaMedia() + ">";
    }
    
    @Override
	public boolean equals(Object otra) {
    	if (this == otra) return true;
    	if (!(otra instanceof AsignaturaNotas)) return false;
		return this.compareTo((AsignaturaNotas)otra) == 0;
	}

	@Override
	public int compareTo(AsignaturaNotas otra) {
		return this.asignatura.compareTo(otra.asignatura);
	}
}
