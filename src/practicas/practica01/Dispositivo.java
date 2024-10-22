package practicas.practica01;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class Dispositivo implements Iterable<String> {
	private String modelo; //atributo clave
	private BitSet caracteristicas;
	protected LinkedList<String> registroPalabras; 
		
	public Dispositivo(String modelo) {
		//Si el parámetro modelo es vacío, el modelo del dispositivo será "noModel"; 
		//en caso contrario, asignamos como atributo modelo el valor del parámetro eliminando los posibles espacios 
		//en blanco que tenga delante y detrás (haz uso del método trim())
		this.modelo = (modelo == null) ? "noModel" : modelo.trim();
		
		//Asignamos a las diez características (haz uso de la constante Parametros.numCaracteristicas) el valor false
		this.caracteristicas = new BitSet(Parametros.numCaracteristicas);
		
		//Inicializamos la estructura registroPalabras
		this.registroPalabras = new LinkedList<>();
	}
	
	public Dispositivo(String modelo, String caracteristicas) {
		//Si el número de características es distinto de la constante Parametros.numCaracteristicas 
		//se ha de lanzar una excepción (throw new RuntimeException(...))
		//El resto exactamente igual que el constructor anterior salvo la asignación de valores a las características del dispositivo
		//Recuerda que si el valor es '0', la característica se asigna como false; en caso contrario, true (sea el carácter que sea)
		//El único for() del método deberá estar compuesto por una única línea (uso del operador ternario ... vídeo del Ejercicio01)
		if (caracteristicas.length() != Parametros.numCaracteristicas) {
	        throw new RuntimeException("El número de características debe ser, exactamente, igual a 10");
	    }
	    
	    this.modelo = (modelo == null) ? "noModel" : modelo.trim();
	    this.caracteristicas = new BitSet(Parametros.numCaracteristicas);
	    
	    for (int i = 0; i < Parametros.numCaracteristicas; i++) {
	        this.caracteristicas.set(i, caracteristicas.charAt(i) == '0' ? false : true);
	    }
	    
	    this.registroPalabras = new LinkedList<>();
	}
	
	public Dispositivo(Dispositivo otro) {
		//Se trata de implementar el constructor copia de Dispositivo
		
		//3 líneas
		this.modelo = otro.modelo;
		//Para el atributo this.características haremos uso del método clone()
		this.caracteristicas = (BitSet) otro.caracteristicas.clone();
		//Para el atributo this.registroPalabras haremos uso del constructor copia de la colección LinkedList<>
		this.registroPalabras = new LinkedList<>(otro.registroPalabras);
	}
	
	public BitSet getInterseccion(Dispositivo otro) {
		//Devolvemos un nuevo BitSet con la intersección de this.caracteristicas y otro.caracteristicas
		//Vamos a echar un vistazo a los métodos de la clase BitSet que implementan operaciones lógicas
		//1 única línea
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
		//Para "trocear" la cadena vamos a hacer uso del método split() 
		//Como tenemos que eliminar todas las ocurrencias de los espacios en blanco, comas y puntos, utilizaremos el patrón "[ ,.]+"
		String[] palabras = mensaje.split("[ ,.]+");
		
		//Ten en cuenta que las palabras se guardan en minúsculas y que no se permiten palabras repetidas
		Set<String> palabritas = new LinkedHashSet<>();
		
		//1 único for()
		for (String palabra : palabras) {
	        if (!palabra.isEmpty()) {
	            palabritas.add(palabra.toLowerCase());
	        }
	    }
	    this.registroPalabras.addAll(palabritas);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	    sb.append(this.modelo).append(" {");
	    
	    for (int i = this.caracteristicas.nextSetBit(0); i >= 0; i = this.caracteristicas.nextSetBit(i + 1)) {
	        sb.append(i).append(", ");
	    }
	    
	    if (sb.length() > this.modelo.length() + 2) {
	        sb.setLength(sb.length() - 2); 
	    }
	    
	    sb.append("}");
	    return sb.toString();
	}
	
	@Override
	public boolean equals(Object otro) {
		//Como la clave de Dispositivo es el atributo this.modelo, dos dispositivos serán iguales sii (si y solo si) sus modelos lo son
		//Determinar si dos dispositivos son iguales implicará comparar las cadenas this.modelo y otro.modelo
		//La clase String ya tiene su método compareTo(), ¿verdad?
		//1 única línea
		return (otro instanceof Dispositivo) && this.modelo.equals(((Dispositivo) otro).modelo);
	}

	@Override
	public Iterator<String> iterator() {
		//Itrerar sobre un dispositivo equivale a iterar sobre la colección this.registroPalabras
		//1 única línea
		return this.registroPalabras.iterator();
	}
}