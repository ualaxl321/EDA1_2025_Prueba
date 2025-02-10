package practicas.practica03.parte01;

import java.util.Arrays;
import java.util.Map.Entry;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class GestionUsuariosDispositivos {
	private final TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> datos = new TreeMap<>();


	public boolean load(String fileName) {
		Scanner scan;
		String line;
		String[] items;
		try {
			scan = new Scanner(new File(fileName));
		} catch (IOException e) {
			return false;
		}
		this.datos.clear();
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line.isBlank()) continue;
			if (line.startsWith("#")) continue;

			items = line.toLowerCase().split("[\t ,.¿?:;()]+");
			//Llamamos al método this.add() 
			//1 única línea
			this.add(items[0].trim().toLowerCase(), items[1].trim(), Arrays.copyOfRange(items, 2, items.length));
		}
		scan.close();
		return true;
	}
    
	public void add(String usuarioId, String dispositivoId, String...palabras) {
		//0 (CERO) llamadas al método containsKey()
		//3  get() y 3 put()
		//1 for()
		//Recuerda el tema de las stopWords() y la eliminación de acentos (cleanWord() - clase Auxiliar)
		
		TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = this.datos.get(usuarioId);
		if (dispositivosDelUsuario == null) this.datos.put(usuarioId, dispositivosDelUsuario = new TreeMap<>());
		
		TreeMap<String, Integer> palabrasDelDispositivo = dispositivosDelUsuario.get(dispositivoId);
		if (palabrasDelDispositivo == null) dispositivosDelUsuario.put(dispositivoId, palabrasDelDispositivo = new TreeMap<>());

		Integer nVecesPalabraRepetida;
		for (String palabra : palabras) {
			if(Auxiliar.isStopWord(palabra)) continue;
			palabra = Auxiliar.cleanWord(palabra.trim().toLowerCase());
			nVecesPalabraRepetida = palabrasDelDispositivo.get(palabra);
			palabrasDelDispositivo.put(palabra, (nVecesPalabraRepetida == null) ? 1 : nVecesPalabraRepetida+1);
		}
	}

	public int size() {
		return this.datos.size();
	}
	
	public void clear() {
		this.datos.clear();
	}

	public int getNumDispositivos(String usuarioId) {
		usuarioId = usuarioId.trim().toLowerCase();
		//1 único get()
		//2 líneas
		if (this.datos.get(usuarioId) == null) return 0;
		return this.datos.get(usuarioId).size();
	}

	public int getNumUsuarios(String dispositivoId) {
		dispositivoId = dispositivoId.trim().toLowerCase();
		//1 uso del método containsKey() (¿por qué aquí si utilizamos este método?)
		//1 for()
		int nUsuarios = 0;
		for (TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario : this.datos.values()) {
			if (dispositivosDelUsuario.containsKey(dispositivoId)) nUsuarios++;
		}
		return nUsuarios;
	}

	public int getNumPalabras(String usuarioId) {
		usuarioId = usuarioId.trim().toLowerCase();
		//1 get()
		//2 for()
		TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = this.datos.get(usuarioId);
		if (dispositivosDelUsuario == null) return 0;
		
		int nPalabras = 0;
		for (TreeMap<String, Integer> palabrasDelDispositivo : dispositivosDelUsuario.values()) {
			for (Integer contador : palabrasDelDispositivo.values()) {
				System.out.println(contador);
				nPalabras += contador;
			}
		}
		return nPalabras;
	}
	
	public int getNumPalabras(String usuarioId, String dispositivoId) {
		usuarioId = usuarioId.trim().toLowerCase();
		dispositivoId = dispositivoId.trim().toLowerCase();
		//2 get()
		//1 for()
		TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = this.datos.get(usuarioId);
		if (dispositivosDelUsuario == null) return 0;
		
		TreeMap<String, Integer> palabrasDelDispositivo = dispositivosDelUsuario.get(dispositivoId);
		if (palabrasDelDispositivo == null) return 0;
		
		int nPalabras = 0;
		for (Integer contador : palabrasDelDispositivo.values()) {
			nPalabras += contador;
		}
		return nPalabras;
	}
	
	public int getFrecuencia(String usuarioId, String palabra) {
		usuarioId = usuarioId.trim().toLowerCase();
		palabra = Auxiliar.cleanWord(palabra);
		//2 get()
		//1 for()
		TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = this.datos.get(usuarioId);
		if (dispositivosDelUsuario == null) return 0;
		
		int frecuencia = 0;
		for (TreeMap<String, Integer> palabras : dispositivosDelUsuario.values()) {
			Integer frecuenciaActual = palabras.get(palabra);
			if (frecuenciaActual != null) frecuencia += frecuenciaActual;
		}
		
		return frecuencia;
	}
	
	public int getFrecuencia(String usuarioId, String dispositivoId, String palabra) {
		usuarioId = usuarioId.trim().toLowerCase();
		dispositivoId = dispositivoId.trim().toLowerCase();
		palabra = Auxiliar.cleanWord(palabra);
		//3 get()
		TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = this.datos.get(usuarioId);
		if (dispositivosDelUsuario == null) return 0;
		
		TreeMap<String, Integer> palabrasDelDispositivo = dispositivosDelUsuario.get(dispositivoId);
		if(palabrasDelDispositivo == null) return 0;
		
		return (palabrasDelDispositivo.get(palabra) != null ? palabrasDelDispositivo.get(palabra) : 0); 
	}
	
	public TreeSet<String> getPalabras(String usuarioId) {
		usuarioId = usuarioId.trim().toLowerCase();
		TreeSet<String> result = new TreeSet<>();
		//1 get()
		//1 único for()
		TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = this.datos.get(usuarioId);
		if (dispositivosDelUsuario == null) return result;
		
		for(TreeMap<String, Integer> palabrasDelDispositivo : dispositivosDelUsuario.values()) {
			result.addAll(palabrasDelDispositivo.keySet());
		}
		
		return result;
	}
	
	public TreeSet<String> getPalabras(String usuarioId, String dispositivoId) {
		usuarioId = usuarioId.trim().toLowerCase();
		dispositivoId = dispositivoId.trim().toLowerCase();
		TreeSet<String> result = new TreeSet<>();
		//2 get()
		
		TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = this.datos.get(usuarioId);
		if (dispositivosDelUsuario == null) return result;
		
		TreeMap<String, Integer> palabrasDelDispositivo = dispositivosDelUsuario.get(dispositivoId);
		if(palabrasDelDispositivo == null) return result;
		
		result.addAll(palabrasDelDispositivo.keySet());
		
		return result;
	}

	public String getUsuarios(String palabra) {
		palabra = Auxiliar.cleanWord(palabra.toLowerCase().trim());
		TreeMap<String, Integer> result = new TreeMap<>();
		//2 for() entrySet() + values()
		//2 get()
		for (Entry<String, TreeMap<String, TreeMap<String, Integer>>> usuarios : this.datos.entrySet()) {
	        TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = usuarios.getValue();
	        for (Entry<String, TreeMap<String, Integer>> palabrasDelDispositivo : dispositivosDelUsuario.entrySet()) {
	            TreeMap<String, Integer> palabras = palabrasDelDispositivo.getValue();
	            Integer frecuencia = palabras.get(palabra);
	            if (frecuencia != null) {
	            	Integer frecuenciaActual = result.get(usuarios.getKey());
	                if (frecuenciaActual == null) frecuenciaActual = 0;
	                result.put(usuarios.getKey(), frecuenciaActual + frecuencia);
	            }
	        }
	    }
		return result.toString();
	}
	
	public TreeSet<String> getPalabrasRepetidas(String usuarioId) {
		usuarioId = usuarioId.trim().toLowerCase();
		TreeSet<String> result = new TreeSet<>();
		//Una única pista: lo mismo interesa crear una estructura TreeMap<K,V> auxiliar
		//this.datos --> auxiliar --> result
		TreeMap<String, Integer> auxiliar = new TreeMap<>();
		
		TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = this.datos.get(usuarioId);
		if (dispositivosDelUsuario == null) return result;
		
		 for (TreeMap<String, Integer> palabrasDelDispositivo : dispositivosDelUsuario.values()) {
		 	for (Entry<String, Integer> palabra : palabrasDelDispositivo.entrySet()) {
    			Integer frecuencia = auxiliar.get(palabra.getKey());
				if (frecuencia == null) auxiliar.put(palabra.getKey(), frecuencia = 0);
				auxiliar.put(palabra.getKey(), frecuencia +1);
    		}
	    }
		for (Entry<String, Integer> entry : auxiliar.entrySet()) {
	        if (entry.getValue() > 1) result.add(entry.getKey());
	    }
		return result;
	}
	
	@Override 
	public String toString() {
		String aux = "";
		//3 for() entrySet() + entrySet() + entrySet()
		//Atención al uso del método lastEntry()
		for (Entry<String, TreeMap<String, TreeMap<String, Integer>>> usuarios : this.datos.entrySet()) { 
			String usuarioId = usuarios.getKey();
			TreeMap<String, TreeMap<String, Integer>> dispositivosDelUsuario = usuarios.getValue();
			aux += usuarioId + ":\n";
			for (Entry<String, TreeMap<String, Integer>> dispositivos : dispositivosDelUsuario.entrySet()) {
				String dispositivoId = dispositivos.getKey();
	            TreeMap<String, Integer> palabras = dispositivos.getValue();
	            aux += "\t" + dispositivoId + ": ";
	            boolean first = true;
				for (Entry<String, Integer> entryPalabra : palabras.entrySet()) {
					String palabra = entryPalabra.getKey();
	                Integer frecuencia = entryPalabra.getValue();
	                if (!first) aux += " - ";
	                aux += palabra + " <" + frecuencia + ">";
	                first = false;
				}
				aux += "\n";
			}
			
		}
		return aux;
	}
}