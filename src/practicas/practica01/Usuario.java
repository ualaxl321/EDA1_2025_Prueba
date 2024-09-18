package practicas.practica01;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

public class Usuario implements Iterable<Dispositivo> {
	private static int numUsuarios = 0; //atributo estático...¿qué significa esto?
	private String nombre; //atributo clave
	private int usuarioId;
	private ArrayList<Dispositivo> dispositivos;
	
	public static void inicializarNumUsuarios() {
		numUsuarios = 0;//¿Sería posible escribir this.numUsuarios = 0?
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Usuario(String nombre) {
		//Si el parámetro nombre es nulo o está vacío se lanza excepción
		//Al atributo this.usuarioId se le asigna el número de usuarios creados hasta el momento (identificador autoincremental)
		//4 líneas
		//...
	}
	
	public void vaciarDispositivos() {
		//1 for()
		//...
		this.dispositivos.clear();
	}
	
	public void addDispositivos(Dispositivo... dispositivos) {
		//La realización de este método es esencial para el correcto funcionamiento de la práctica
		//Atención al uso del constructor copia a la hora de añadir un dispositivo a la lista de dispositivos del usuario
		//En la colección this.dispositivos se añade una copia de los dispositivos especificados en el array de parámetros de entrada (no copian las referencias)
		//No se permite almacenar dispositivos repetidos
		for (Dispositivo dispositivo : dispositivos) {
			//2 líneas
			//...
		}
	}
	
	public int getNumDispositivos() { 
		return this.dispositivos.size();
	}

	public boolean enviarMensaje(String nombreDispositivo, String mensaje) {
		//En primer lugar comprobamos que el usuario tenga el dispositivo especificado como parámetro de entrada (uso del método indexOf())
		//Si no existe el dispositivo especificado se devuvelve false
		//En caso contrario se hace uso del método enviarMensaje() del dispositivo correspondiente
		///3 líneas
		//...
		int pos = //...
		if (pos == -1) return false;
		//...
		return true;
	}
	
	public int getNumPalabras(String nombreDispositivo) {
		//Si el usuario no tiene el dispositivo especificado como parámetro de entrada, se devuelve el valor 0
		//En caso contrario se hará uso del método getNumPalabras() del dispositivo correspondiente
		//¿A qué nos referimos con lo del "dispositivo correspondiente"? 
		//3 líneas
		//...
	}
	
	public ArrayList<String> getPalabras() {
		ArrayList<String> result = new ArrayList<>();
		//Queremos el conjunto completo de palabras (sin repetir) que han sido enviadas por el usuario, independientemente del dispositivo utilizado
		//Ojo a la forma de iterar sobre la colección this.dispositivos y, sobre todo, cómo iteramos sobre la colección this.registroPalabras que tiene cada uno de los dispositivos
		//Prohibido esto de de Iterator<?> iter = new Iterar... En su lugar, forEach...   
		//2 for()
		//...
		//Hay que ordenar el resultado, ¿verdad?
		return result;
	}
	
	public BitSet getInterseccion(Usuario otro) {
		BitSet result = new BitSet(Parametros.numCaracteristicas);
		//Intersecar dos usuarios implica realizar la operación de intersección de todos los dispositivos que poseen (de dos en dos)
		//2 for()
		//...
		return result;
	}
	
	@Override
	public String toString() {
		return //...;
	}
	
	@Override
	public boolean equals(Object otro) {
		//Recuerda que la clave de un usuario es su nombre
		return //...
	}

	@Override
	public Iterator<Dispositivo> iterator() {
		//Iterar sobre un usuario implica iterar sobre su colección de dispositivos
		return //...
	}
}