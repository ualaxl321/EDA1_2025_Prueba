package practicas.practica02.parte02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

public class Libro implements Comparable<Libro>, Iterable<Par<String, Integer>>{
	private final String libroId; 
	private final AVLTree<Par<String, Integer>> palabrasFrecuencias;
	
	public Libro(String libroId) {
		this.libroId = libroId.trim().toLowerCase();
		this.palabrasFrecuencias = new AVLTree<>();
	}
	
	public String getLibroId() {
		return this.libroId;
	}
		
	public void clear() {
		this.palabrasFrecuencias.clear();
	}
		
	public boolean load(String fileName) {
		Scanner scan;
		try {
			scan = new Scanner(new File(fileName));
		}catch (IOException e) {
			return false;
		}
		while (scan.hasNextLine()){
			String line = scan.nextLine().trim();
			if (line.isEmpty()) continue;
			String[] palabras = line.split("\\s+");
	        add(palabras);
		}
		scan.close();
		return true;
	}
	
	public void add(String...palabras) {
		for (String palabra : palabras) {
			if (palabra != null && !palabra.trim().isEmpty()) {
				Par <String, Integer> current = this.palabrasFrecuencias.find(new Par<>(palabra.toLowerCase(), null));
				if (current == null) palabrasFrecuencias.add(new Par <>(palabra.toLowerCase(), 1));
				else {current.setValue(current.getValue() + 1);}
			}
		}
	}
	
	public ArrayList<Par<Integer, ArrayList<String>>> getPalabrasFrecuentes(int freqMin) {
		ArrayList<Par<Integer, ArrayList<String>>> result = new ArrayList<>();
		for (Par <String, Integer> par : palabrasFrecuencias) {
			if (par.getValue() < freqMin) continue;
			int pos = result.indexOf(new Par <> (par.getValue(), null));
			if (pos == -1) {
				ArrayList<String> listaPalabras = new ArrayList<String>();
				listaPalabras.add(par.getKey());
				result.add(new Par <> (par.getValue(), listaPalabras));
			} else {result.get(pos).getValue().add(par.getKey());}
		}
		result.sort(null);
		return result;
	}
		
	@Override
	public String toString() {
		return this.libroId + " -> " + (this.palabrasFrecuencias.isEmpty() ? "[empty]" : this.palabrasFrecuencias);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Libro)) return false;
		if (this == o) return true;
		return this.compareTo((Libro)o) == 0;
	}
	
	@Override
	public int compareTo(Libro o) {
		return this.libroId.compareTo(o.libroId);
	}
	
	@Override
	public Iterator<Par<String, Integer>> iterator() {
		return this.palabrasFrecuencias.iterator();
	}
}