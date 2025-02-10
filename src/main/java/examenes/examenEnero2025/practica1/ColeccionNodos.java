package examenes.examenEnero2025.practica1;

import java.util.*;
import java.util.Map.Entry;

public class ColeccionNodos <T extends Comparable<T>> {
	
	protected final TreeMap<Nodo<T>, TreeSet<Nodo<T>>> data = new TreeMap<>();

	public void add(Nodo<T> NodoOrigen, Nodo<T> NodoDestino) {
		TreeSet<Nodo<T>> setValues = this.data.get(NodoOrigen);
		if (setValues == null) this.data.put(NodoOrigen, setValues = new TreeSet<>());
		setValues.add(NodoDestino);
	}

	@Override
	public String toString() {
		String result = "";
		for (Entry<Nodo<T>, TreeSet<Nodo<T>>> parMain : this.data.entrySet()) {
	        result += parMain.getKey() + "\n";
	        for (Nodo<T> nodoComponente : parMain.getValue()) {
	            result += "\t" + nodoComponente.toString() + "\n";
	        }
	    }
		return result;
	}
}