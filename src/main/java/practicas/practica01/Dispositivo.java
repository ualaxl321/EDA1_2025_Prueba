package practicas.practica01;

import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Dispositivo implements Iterable<String> {
	private String modelo;
	private BitSet caracteristicas;
	protected LinkedList<String> registroPalabras; 
		
	public Dispositivo(String modelo) {
		this.modelo = (modelo == null || modelo.trim().isEmpty()) ? "noModel" : modelo.trim();
		this.caracteristicas = new BitSet(Parametros.numCaracteristicas);
		this.registroPalabras = new LinkedList<>();
	}
	
	public Dispositivo(String modelo, String caracteristicas) {
		if (caracteristicas.length() != Parametros.numCaracteristicas) {
	        throw new RuntimeException("El número de características debe ser, exactamente, igual a 10");
	    }
	    
		this.modelo = (modelo == null || modelo.trim().isEmpty()) ? "noModel" : modelo.trim();
	    this.caracteristicas = new BitSet(Parametros.numCaracteristicas);
	    
	    for (int i = 0; i < Parametros.numCaracteristicas; i++) {
	        this.caracteristicas.set(i, caracteristicas.charAt(i) == '0' ? false : true);
	    }
	    
	    this.registroPalabras = new LinkedList<>();
	}
	
	public Dispositivo(Dispositivo otro) {
		this.modelo = otro.modelo;
		this.caracteristicas = (BitSet) otro.caracteristicas.clone();
		this.registroPalabras = new LinkedList<>(otro.registroPalabras);
	}
	
	public BitSet getInterseccion(Dispositivo otro) {		
		BitSet result = (BitSet) this.caracteristicas.clone();
		result.and(otro.caracteristicas);
	    return result;
	}
	
	public int getNumPalabras() {
		return this.registroPalabras.size();
	} 
	
	public void vaciarDispositivo() {
		this.registroPalabras.clear();
	}
	
	public void enviarMensaje(String mensaje) {
		for (String palabra : mensaje.toLowerCase().split("[ ,.]+")) {
			if (!palabra.isEmpty() && !registroPalabras.contains(palabra)) {
				registroPalabras.add(palabra);
	        }
	    }
	}
	
	@Override
	public String toString() {
		return this.modelo + " " + this.caracteristicas;
	}
	
	@Override
	public boolean equals(Object otro) {
		return (otro instanceof Dispositivo) && this.modelo.compareTo(((Dispositivo) otro).modelo) == 0;
	}

	@Override
	public Iterator<String> iterator() {
		return this.registroPalabras.iterator();
	}
}