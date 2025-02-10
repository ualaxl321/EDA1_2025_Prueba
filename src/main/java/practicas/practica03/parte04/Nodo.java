package practicas.practica03.parte04;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class Nodo <T extends Comparable<T>> implements Comparable<Nodo<T>> {
	
	private ArrayList<T> componentes;

	
	@SafeVarargs
	public Nodo(T ... componentes) {
		this.componentes = new ArrayList<>();
		//1 for()
		TreeSet<T> conjunto = new TreeSet<>();
		for (T componente : componentes) {
	        if (conjunto.add(componente)) this.componentes.add(componente); 
	    }
		this.componentes.sort(null);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.componentes);
	}

	
	@Override
	public boolean equals(Object otro) { 
		if (this == otro) return true;
		if (!(otro instanceof Nodo))return false;
		return this.compareTo((Nodo) otro) == 0;
	}

	@Override
	public int compareTo(Nodo<T> otro) { 
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