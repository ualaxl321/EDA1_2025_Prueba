package examenes.examenSeptiembre2019;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class UserManagement {
	
	private TreeMap<User, TreeMap<Device, TreeMap<String, Integer>>> datos;
	
	public UserManagement() {
		datos = new TreeMap<User, TreeMap<Device, TreeMap<String, Integer>>>();
	}
	
	public void add(User user, Device device, String ... messages) {
		TreeMap<String, Integer> frecuencias = null;
		Integer cont = null;
		
		if(!datos.containsKey(user)) datos.put(user, new TreeMap<Device, TreeMap<String, Integer>>());
		if(!datos.get(user).containsKey(device)) datos.get(user).put(device, new TreeMap<String, Integer>());
		frecuencias = datos.get(user).get(device);
		for(String mensaje : messages) {
			cont = frecuencias.get(mensaje);
			frecuencias.put(mensaje, cont==null?1:cont+1);
		}
	}
	
	public int getNumUsers() {
		return datos.size();
	}
	
	public TreeSet<Device> getDevices(User user){
		TreeMap<Device, TreeMap<String, Integer>> aux = datos.get(user);
		return aux == null ? null : new TreeSet<Device>(aux.keySet());
	}
	
	public ArrayList<String> getPalabrasFrecuentes(User user) {
		ArrayList<String> resultado = null;
		if (!datos.containsKey(user)) return null;
		
		//Haremos uso de estas dos estructuras de datos intermedias
		TreeMap<String, Integer> palabraFreq = new TreeMap<String, Integer>();
		TreeMap<Integer, TreeSet<String>> freqPalabras = new TreeMap<Integer, TreeSet<String>>(new Greater<Integer>());
		Integer cont = null;
		
		for(TreeMap<String, Integer> mensajes : datos.get(user).values()) {
			for(Entry<String, Integer> mensaje : mensajes.entrySet()) {
				cont = palabraFreq.get(mensaje.getKey());
				palabraFreq.put(mensaje.getKey(), mensaje.getValue() + (cont==null ? 0 : cont));
			}
		}
		
		for(Entry<String, Integer> pair : palabraFreq.entrySet()) {
			if(!freqPalabras.containsKey(pair.getValue())) freqPalabras.put(pair.getValue(), new TreeSet<String>());
			freqPalabras.get(pair.getValue()).add(pair.getKey());
		}
		
		resultado = new ArrayList<String>();
		for(Entry<Integer, TreeSet<String>> pair : freqPalabras.entrySet()) {
			for(String palabra : pair.getValue()) {
				resultado.add(palabra + " (" + pair.getKey() + ")");
			}
		}
		return resultado;
	}
	
	public ArrayList<String> getPalabrasFrecuentes(Device device) {
		ArrayList<String> resultado = null;
		//Haremos uso de estas dos estructuras de datos intermedias
		TreeMap<String, Integer> palabraFreq = new TreeMap<String, Integer>();
		TreeMap<Integer, TreeSet<String>> freqPalabras = new TreeMap<Integer, TreeSet<String>>(new Greater<Integer>());
		Integer cont = null;
		
		for(TreeMap<Device, TreeMap<String, Integer>> devices : datos.values()) {
			if(!devices.containsKey(device)) continue;	
			for(Entry<String, Integer> mensaje : devices.get(device).entrySet()) {
				cont = palabraFreq.get(mensaje.getKey());
				palabraFreq.put(mensaje.getKey(), mensaje.getValue()+
				(cont==null?0:cont));
			}
		}
		
		if(palabraFreq.isEmpty()) return resultado;
		
		for(Entry<String, Integer> pair : palabraFreq.entrySet()) {
			if(!freqPalabras.containsKey(pair.getValue()))
			freqPalabras.put(pair.getValue(), new TreeSet<String>());
			freqPalabras.get(pair.getValue()).add(pair.getKey());
		}
		resultado = new ArrayList<String>();
		
		for(Entry<Integer, TreeSet<String>> pair : freqPalabras.entrySet()) {
			for(String palabra : pair.getValue()) {
				resultado.add(palabra + " (" + pair.getKey() + ")");
			}
		}
		return resultado;
	}
	
	public ArrayList<String> similars(User user){
		ArrayList<String> resultado = null;
		TreeMap<User, Integer> result = null;
		Integer sim = 0;
		
		if (!datos.containsKey(user)) return null;
		resultado = new ArrayList<String>();
		result = new TreeMap<User, Integer>();
		
		//Haremos uso de datos.get(user).keySet() para obtener el conjunto de dispositivos
		for(Device device : datos.get(user).keySet()) {
			for(Entry<User, TreeMap<Device, TreeMap<String, Integer>>> par: datos.entrySet()) {
				if(par.getKey().equals(user)) continue;
				for(Device device2 : par.getValue().descendingKeySet()) {
					sim = device.intersects(device2) + (result.get(par.getKey())==null?0:result.get(par.getKey()));
					result.put(par.getKey(), sim);
				}
			}
		}
		
		for(Entry<User, Integer> parUS : result.entrySet()) {
			resultado.add(parUS.getKey() + " (" + parUS.getValue() + ")");
		}
		return resultado;
	}
	
	public int getNumDevices(User user) {
		TreeMap<Device, TreeMap<String, Integer>> aux = datos.get(user);
		return aux == null ? 0 : aux.size(); //aux????
	}
	
	/* EJERCICIO 1 - Método getNumDevices
	 * Devuelve el número de dispositivos pertenecientes a un determinado usuario con clave nombre.
	 * Si el usuario no existe, devolverá el valor 0.
	 * 
	 */
	public int getNumDevices(String nombre) {
	    for (Entry<User, TreeMap<Device, TreeMap<String, Integer>>> entry : datos.entrySet()) {
	        if (entry.getKey().equals(nombre)) return entry.getValue().size();
	    }
	    return 0;
	}
	
	/* EJERCICIO 1 - Método similarityDegree
	 * Devuelve el grado de similitud de dos usuarios especificados a través de su clave (nombre).
	 * Si se trata del mismo usuario, el método devolverá Integer.maxValue.
	 * Si no, el grado de similitud será igual a la suma de características comunes existentes entre
	 * dos dispositivos diferentes que poseen.
	 * El número de características comunes existentes entre dos dispositivos se calcula mediante 
	 * el método intersects() de la clase Device.
	 * Recuerda que dos usuarios pueden compartir dispositivos, si se da el caso, no se tendrá en 
	 * cuenta sus valores de intersección.
	 */
	public int similarityDegree(String nombre1, String nombre2) {
		if (nombre1.equals(nombre2)) {
	        return Integer.MAX_VALUE;
	    }

	    User user1 = null;
	    User user2 = null;

	    // Buscar los usuarios por nombre
	    for (User user : datos.keySet()) {
	        if (user.getNick().equals(nombre1)) {
	            user1 = user;
	        }
	        if (user.getNick().equals(nombre2)) {
	            user2 = user;
	        }
	    }

	    if (user1 == null || user2 == null) {
	        return 0;
	    }

	    int similitud = 0;

	    // Obtener los dispositivos de cada usuario
	    TreeMap<Device, TreeMap<String, Integer>> devicesUser1 = datos.get(user1);
	    TreeMap<Device, TreeMap<String, Integer>> devicesUser2 = datos.get(user2);

	    // Calcular la similitud entre los dispositivos
	    for (Device device1 : devicesUser1.keySet()) {
	        for (Device device2 : devicesUser2.keySet()) {
	            if (!device1.equals(device2)) {
	                similitud += device1.intersects(device2);
	            }
	        }
	    }

	    return similitud;
	}
	
	/* EJERCICIO 1 - Método similar()
	 * Devuelve el conjunto de usuarios similares a un usuario especificado a partir de su nombre,
	 * donde dos usuarios son similares si su grado de similitud es mayor o igual que el valor 
	 * establecido del parámetro simMin.
	 * Por ejemplo, el conjunto de usuarios similares a "Pepe" entendiendo que dos usuarios son
	 * similares si su grado de similitud es mayor o igual que 50.
	 */
	public ArrayList<String> similar(String nombre, int simMin) {
	    User user = new User(nombre);
	    TreeMap<Integer, TreeSet<User>> mapAux = new TreeMap<>(new Greater<Integer>());
	    ArrayList<String> resultado = new ArrayList<>();
	    int cmp = 0; //¿Se emplea para algo?

	    if (!datos.containsKey(user)) return resultado; 

	    for (User otherUser : datos.keySet()) {
	        if (!otherUser.equals(user)) {
	            int similitud = similarityDegree(user.getNick(), otherUser.getNick());

	            if (similitud >= simMin) {
	                if (!mapAux.containsKey(similitud)) mapAux.put(similitud, new TreeSet<>());
	                mapAux.get(similitud).add(otherUser);
	            }
	        }
	    }

	    for (Entry<Integer, TreeSet<User>> entry : mapAux.entrySet()) {
	        int similitud = entry.getKey();
	        for (User similarUser : entry.getValue()) {
	            resultado.add(similarUser.getNick() + " (" + similitud + ")");
	        }
	    }

	    return resultado;
	}
	
	@Override
	public String toString() {
		return datos.toString();
	}
}