package practicas.prueba02;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class Complete {
	private TreeMap<String, TreeMap<String, Double>> datos = new TreeMap<>();
	
	public void add(String key, String attribute, Double value) {
		TreeMap<String, Double> values = this.datos.get(key);
		if (values == null) this.datos.put(key, values = new TreeMap<>());
		values.put(attribute, value);
	}
	
	/**
	 * COMENTARIOS PROFESOR:
	 * Observa en datos la relación entre alumnado, asignaturas y notas
	 * Observa en check() en qué consiste el proceso de completar la "matrícula" de cada estudiante
	 * ¿Para qué puedo necesitar TreeSet<String> attributes = new TreeSet<>();?
	 * 		Para almacenar las asignaturas de manera ordenada.
	 * 
	 * JAVADOC DEL ALUMNO:
	 * Completa lasmatrículas" de los estudiantes.
	 * Si una asignatura está ausente para un estudiante, se asigna un valor predeterminado de 0.0.
	 * El primer bucle almacena las asiganturas de todos los estudiantes.
	 * El segundo bucle añade valores por defecto a aquellas asiganturas sin nota por cada estudiante.
	 */
	public void complete() {
		TreeSet<String> attributes = new TreeSet<>();
		for (Entry<String, TreeMap<String, Double>> dato : datos.entrySet()) {
			attributes.addAll(dato.getValue().keySet());
		}
		for (Entry<String, TreeMap<String, Double>> dato : datos.entrySet()) {
			attributes.addAll(dato.getValue().keySet());
			TreeMap<String, Double> asignaturas = dato.getValue();
			for (String asignatura : attributes) {
				if (!asignaturas.containsKey(asignatura)) asignaturas.put(asignatura, 0.0);
			}
		}
	}
	
	@Override
	public String toString() {
		return this.datos.toString();
	}
	
	public boolean check() {
		boolean alum01 = "{asignatura01=7.5, asignatura02=0.0, asignatura03=6.5, asignatura04=4.5, asignatura05=0.0}".equals(this.datos.get("alum01").toString());
		boolean alum02 = "{asignatura01=3.5, asignatura02=0.0, asignatura03=4.25, asignatura04=5.25, asignatura05=0.0}".equals(this.datos.get("alum02").toString());
		boolean alum03 = "{asignatura01=0.0, asignatura02=8.34, asignatura03=0.0, asignatura04=0.0, asignatura05=2.34}".equals(this.datos.get("alum03").toString());
		return alum01 && alum02 && alum03;
	}
	
	public static void main(String[] args) {
		Complete ejercicio = new Complete();
		
		ejercicio.add("alum01", "asignatura01", 7.5);
		ejercicio.add("alum01", "asignatura04", 4.5);
		ejercicio.add("alum01", "asignatura03", 6.5);
		ejercicio.add("alum02", "asignatura01", 3.5);
		ejercicio.add("alum02", "asignatura03", 4.25);
		ejercicio.add("alum02", "asignatura04", 5.25);
		ejercicio.add("alum03", "asignatura05", 2.34);
		ejercicio.add("alum03", "asignatura02", 8.34);
		
		//System.out.println(ejercicio.toString());
		ejercicio.complete();
		System.out.println(ejercicio.check() ? "¡¡¡OK!!!" : "¡¡¡Error!!!");
	}

}
