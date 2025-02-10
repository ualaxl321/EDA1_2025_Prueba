package practicas.practica01;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;

public class Usuario implements Iterable<Dispositivo> {
	private static int numUsuarios = 0;
	private String nombre; 
	private int usuarioId;
	private ArrayList<Dispositivo> dispositivos;
	
	public static void inicializarNumUsuarios() {
		numUsuarios = 0;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Usuario(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) throw new RuntimeException("El atributo nombre no puede ser ni nulo ni vacío");
		this.nombre = nombre.trim();
        this.usuarioId = ++numUsuarios; 
        this.dispositivos = new ArrayList<>();
	}
	
	public void vaciarDispositivos() {
		 for (Dispositivo dispositivo : dispositivos) {
			 dispositivo.vaciarDispositivo();
		 }
		 this.dispositivos.clear();
	}
	
	public void addDispositivos(Dispositivo... dispositivos) {
		for (Dispositivo dispositivo : dispositivos) {
			if (dispositivo != null && !this.dispositivos.contains(dispositivo)) this.dispositivos.add(new Dispositivo(dispositivo));
		}
	}
	
	public int getNumDispositivos() { 
		return this.dispositivos.size();
	}

	public boolean enviarMensaje(String nombreDispositivo, String mensaje) {
		int pos = dispositivos.indexOf(new Dispositivo(nombreDispositivo));
		if (pos == -1) return false;
		dispositivos.get(pos).enviarMensaje(mensaje);
	    return true;
	}
	
	public int getNumPalabras(String nombreDispositivo) {
		int index = this.dispositivos.indexOf(new Dispositivo(nombreDispositivo));
		return index >= 0 ? this.dispositivos.get(index).getNumPalabras() : 0;
	}
	
	public ArrayList<String> getPalabras() {
		ArrayList<String> result = new ArrayList<>();
		for (Dispositivo dispositivo : this.dispositivos) {
			for (String palabra : dispositivo) {
				if (!result.contains(palabra)) {
					result.add(palabra);
				}
			}
		}
		Collections.sort(result);
		return result;
	}
	
	public BitSet getInterseccion(Usuario otro) {
		BitSet result = new BitSet(Parametros.numCaracteristicas);
		for (Dispositivo dispositivo1 : this.dispositivos) {
	        for (Dispositivo dispositivo2 : otro.dispositivos) {
	            result.or(dispositivo1.getInterseccion(dispositivo2));
	        }
	    }
		return result;
	}
	
	@Override
	public String toString() {
		return usuarioId + ".- " + this.nombre + " -> " + dispositivos.toString();
	}
	
	@Override
	public boolean equals(Object otro) {
		if (this == otro) return true;
		if (!(otro instanceof Usuario)) return false;
		Usuario otroUsuario = (Usuario) otro;
		return this.nombre.equals(otroUsuario.nombre);
	}

	@Override
	public Iterator<Dispositivo> iterator() {
		return this.dispositivos.iterator();
	}
}