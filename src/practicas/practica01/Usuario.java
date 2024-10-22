package practicas.practica01;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

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
		if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("El atributo nombre no puede ser ni nulo ni vacío");
        }
		this.nombre = nombre.trim();
        this.usuarioId = numUsuarios++; 
        this.dispositivos = new ArrayList<>();
	}
	
	public void vaciarDispositivos() {
		 for (Dispositivo dispositivo : dispositivos) {
		        
		    }
		    this.dispositivos.clear();
	}
	
	public void addDispositivos(Dispositivo... dispositivos) {
		//La realización de este método es esencial para el correcto funcionamiento de la práctica
		//Atención al uso del constructor copia a la hora de añadir un dispositivo a la lista de dispositivos del usuario
		//En la colección this.dispositivos se añade una copia de los dispositivos especificados en el array de parámetros de entrada (no copian las referencias)
		//No se permite almacenar dispositivos repetidos
		for (Dispositivo dispositivo : dispositivos) {
			if (dispositivo != null && !this.dispositivos.contains(dispositivo)) {
	            this.dispositivos.add(new Dispositivo(dispositivo));
			}
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
		int pos = -1;
	    for (int i = 0; i < this.dispositivos.size(); i++) {
	        if (this.dispositivos.get(i).equals(new Dispositivo(nombreDispositivo))) {
	            pos = i;
	            break;
	        }
	    }
	    if (pos == -1) return false;
	    this.dispositivos.get(pos).enviarMensaje(mensaje);
	    return true;
	}
	
	public int getNumPalabras(String nombreDispositivo) {
		//Si el usuario no tiene el dispositivo especificado como parámetro de entrada, se devuelve el valor 0
		//En caso contrario se hará uso del método getNumPalabras() del dispositivo correspondiente
		//¿A qué nos referimos con lo del "dispositivo correspondiente"? 
		//3 líneas
		for (int i = 0; i < this.dispositivos.size(); i++) {
			 if (this.dispositivos.get(i).equals(new Dispositivo(nombreDispositivo))) {
				 return this.dispositivos.get(i).getNumPalabras();
			 }
		}
		return 0;
	}
	
	public ArrayList<String> getPalabras() {
		//Queremos el conjunto completo de palabras (sin repetir) que han sido enviadas por el usuario, independientemente del dispositivo utilizado
		//Ojo a la forma de iterar sobre la colección this.dispositivos y, sobre todo, cómo iteramos sobre la colección this.registroPalabras que tiene cada uno de los dispositivos
		//Prohibido esto de de Iterator<?> iter = new Iterar... En su lugar, forEach...   
		//2 for()
		//...
		//Hay que ordenar el resultado, ¿verdad?
		Set<String> palabrasUnicas = new LinkedHashSet<>();
		for (Dispositivo dispositivo : this.dispositivos) {
			dispositivo.registroPalabras.forEach(palabra -> {
	            palabrasUnicas.add(palabra);
			});
		}
		ArrayList<String> result = new ArrayList<>(palabrasUnicas);
		Collections.sort(result);
		return result;
	}
	
	public BitSet getInterseccion(Usuario otro) {
		BitSet result = new BitSet(Parametros.numCaracteristicas);
		//Intersecar dos usuarios implica realizar la operación de intersección de todos los dispositivos que poseen (de dos en dos)
		//2 for()
		for (Dispositivo dispositivo1 : this.dispositivos) {
	        for (Dispositivo dispositivo2 : otro.dispositivos) {
	            BitSet interseccion = dispositivo1.getInterseccion(dispositivo2);
	            result.or(interseccion);
	        }
	    }
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	    sb.append("Usuario: ").append(this.nombre).append(", Dispositivos: [");
	    
	    for (int i = 0; i < dispositivos.size(); i++) {
	        sb.append(dispositivos.get(i).toString());
	        if (i < dispositivos.size() - 1) {
	            sb.append(", "); // Añadir una coma si no es el último dispositivo
	        }
	    }
	    
	    sb.append("]");
	    return sb.toString();
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
		//Iterar sobre un usuario implica iterar sobre su colección de dispositivos
		return dispositivos.iterator();
	}
}