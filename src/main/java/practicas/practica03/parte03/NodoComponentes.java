package practicas.practica03.parte03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

public class NodoComponentes implements Comparable<NodoComponentes> {
	
	private ArrayList<Componente> componentes;

	public NodoComponentes(Componente... componentes) {
		this.componentes = new ArrayList<>();
		//No permitimos que existan componentes repetidos
		TreeSet<Componente> componente = new TreeSet<>(Arrays.asList(componentes));
		this.componentes.addAll(componente);

		this.componentes.sort(null);
	}

	@Override
	public int hashCode() {
		//1 línea
		return Objects.hash(this.componentes);
	}

	@Override
	public boolean equals(Object otro) { 
		if (this == otro) return true;
		if (!(otro instanceof NodoComponentes))return false;
		return this.compareTo((NodoComponentes) otro) == 0;
	}

	@Override
	public int compareTo(NodoComponentes otro) {
		//¿Cómo comparamos dos ArrayList<>?
		//Esto ya está contado en el guion, así que revísalo si no te acuerdas
		//1 único for()
		//Mira la implementación del método compareTo() de la clase String
		int tamañoMinimo = Math.min(this.componentes.size(), otro.componentes.size());
	    for (int i = 0; i < tamañoMinimo; i++) {
	        int cmp = this.componentes.get(i).compareTo(otro.componentes.get(i));
	        if (cmp != 0) return cmp;
	    }
	    return Integer.compare(this.componentes.size(), otro.componentes.size());
	} 

	@Override
	public String toString() {
		return this.componentes.toString();
	}
}