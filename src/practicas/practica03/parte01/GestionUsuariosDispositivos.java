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
			line = this.add(items[0], items[1].trim());
			
		}
		scan.close();
		return true;
	}
    
	public void add(String usuarioId, String dispositivoId, String...palabras) {
		//0 (CERO) llamadas al método containsKey()
		//3  get() y 3 put()
		//1 for()
		//Recuerda el tema de las stopWords() y la eliminación de acentos (cleanWord() - clase Auxiliar)
		
		
		//Para añadir necesitamos sacar el usuario actual
		TreeMap<String, TreeMap<String, Integer>> usuarioActual = this.datos.get(usuarioId);
		//Si está nulo, lo añado creando TreeMap
		if (usuarioActual == null) this.datos.put(usuarioId, usuarioActual = new TreeMap<>());
		
		//Para añadir el dispositivo actual, nos traemos los dispositivos del usuario actual
		TreeMap<String, Integer> dispositivoActual = usuarioActual.get(dispositivoId);
		//Si está nulo, lo añado creando TreeMap
		if (dispositivoActual == null) usuarioActual.put(dispositivoId, dispositivoActual = new TreeMap<>());
		
		Integer frecuenciaPalabras;
		for (String palabra : palabras) {
			if(Auxiliar.isStopWord(palabra)) continue;
			palabra = Auxiliar.cleanWord(palabra.trim().toLowerCase());
			frecuenciaPalabras = dispositivoActual.get(palabra);
			dispositivoActual.put(palabra, (frecuenciaPalabras == null) ? null : frecuenciaPalabras+1);
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
		//...
	}

	public int getNumUsuarios(String dispositivoId) {
		dispositivoId = dispositivoId.trim().toLowerCase();
		//1 uso del método containsKey() (¿por qué aquí si utilizamos este método?)
		//1 for()
		//...
	}

	public int getNumPalabras(String usuarioId) {
		usuarioId = usuarioId.trim().toLowerCase();
		//1 get()
		//2 for()
		//...
	}
	
	public int getNumPalabras(String usuarioId, String dispositivoId) {
		usuarioId = usuarioId.trim().toLowerCase();
		dispositivoId = dispositivoId.trim().toLowerCase();
		//2 get()
		//1 for()
		//...
	}
	
	public int getFrecuencia(String usuarioId, String palabra) {
		usuarioId = usuarioId.trim().toLowerCase();
		palabra = Auxiliar.cleanWord(palabra);
		//2 get()
		//1 for()
		//...
	}
	
	public int getFrecuencia(String usuarioId, String dispositivoId, String palabra) {
		usuarioId = usuarioId.trim().toLowerCase();
		dispositivoId = dispositivoId.trim().toLowerCase();
		palabra = Auxiliar.cleanWord(palabra);
		//3 get()
		//...
	}
	
	public TreeSet<String> getPalabras(String usuarioId) {
		usuarioId = usuarioId.trim().toLowerCase();
		TreeSet<String> result = new TreeSet<>();
		//1 get()
		//1 único for()
		//...
		return result;
	}
	
	public TreeSet<String> getPalabras(String usuarioId, String dispositivoId) {
		usuarioId = usuarioId.trim().toLowerCase();
		dispositivoId = dispositivoId.trim().toLowerCase();
		TreeSet<String> result = new TreeSet<>();
		//2 get()
		//...
		return result;
	}

	public String getUsuarios(String palabra) {
		palabra = Auxiliar.cleanWord(palabra.toLowerCase().trim());
		TreeMap<String, Integer> result = new TreeMap<>();
		//2 for() entrySet() + values()
		//2 get()
		//...
		return result.toString();
	}
	
	public TreeSet<String> getPalabrasRepetidas(String usuarioId) {
		usuarioId = usuarioId.trim().toLowerCase();
		TreeSet<String> result = new TreeSet<>();
		//Una única pista: lo mismo interesa crear una estructura TreeMap<K,V> auxiliar
		//this.datos --> auxiliar --> result
		//...
		return result;
	}
	
	@Override 
	public String toString() {
		String aux = "";
		//3 for() entrySet() + entrySet() + entrySet()
		//Atención al uso del método lastEntry()
		//...
		return aux;
	}
}