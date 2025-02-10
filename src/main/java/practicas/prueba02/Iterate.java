package practicas.prueba02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Iterate implements Iterable<Entry<String, ArrayList<String>>> {
	private TreeMap<String, TreeMap<String, ArrayList<String>>> datos = new TreeMap<>();
	
	public void add(String mainKey, String secondKey, String...values) {
		TreeMap<String, ArrayList<String>> mainValues = this.datos.get(mainKey);
		if (mainValues == null) this.datos.put(mainKey, mainValues = new TreeMap<>());
		ArrayList<String> secondValues = mainValues.get(secondKey);
		if (secondValues == null) mainValues.put(secondKey, secondValues = new ArrayList<>());
		secondValues.addAll(List.of(values));
	}
	
	@Override
	public String toString() {
		return this.datos.toString();

	}

	/**
	 * Iterador de TreeMap:
	 * 		La clave es un valor ("w01", "w02").
	 *		El valor es una lista de claves secundarias ("sK01", "sK02")  asociadas a ese valor en la estructura original.
	 * @return Un iterador sobre el TreeMap generado.
	 */
	@Override
	public Iterator<Entry<String, ArrayList<String>>> iterator() {
		TreeMap<String, ArrayList<String>> result = new TreeMap<>();
		for (Entry<String, TreeMap<String, ArrayList<String>>> entry : this.datos.entrySet()) {
			TreeMap<String, ArrayList<String>> mapa = entry.getValue();
			if (mapa != null) {
				for (Entry<String, ArrayList<String>> entry2 : mapa.entrySet()) {
					String claveSecond = entry2.getKey();
					for (String valor : entry2.getValue()) {
						ArrayList<String> valores = result.get(valor);
						if (valores == null) result.put(valor, valores = new ArrayList<>());
						if (!valores.contains(claveSecond)) valores.add(claveSecond);
					}
				}
			}
		}
		return result.entrySet().iterator();
	}
	
	public boolean check(TreeMap<String, ArrayList<String>> aux) {
		boolean w01 = "[sK01, sK02, sK03]".equals(aux.get("w01").toString());
		boolean w02 = "[sK01, sK03]".equals(aux.get("w02").toString());
		boolean w03 = "[sK01, sK02, sK03]".equals(aux.get("w03").toString());
		boolean w04 = "[sK01, sK02]".equals(aux.get("w04").toString());
		return w01 && w02 && w03 && w04;
	}
	
	public static void main(String[] args) {
		Iterate ejercicio = new Iterate();
		ejercicio.add("mK01",  "sK01", "w01", "w02", "w03", "w04", "w01", "w01");
		ejercicio.add("mK01",  "sK02", "w03", "w04", "w04");
		ejercicio.add("mK02",  "sK01", "w01", "w02", "w03", "w04");
		ejercicio.add("mK02",  "sK02", "w01", "w04", "w01", "w04");
		ejercicio.add("mK02",  "sK03", "w01", "w02", "w03", "w02", "w03");
		
		System.out.println(ejercicio);
		TreeMap<String, ArrayList<String>> aux = new TreeMap<>();
		for (Entry<String, ArrayList<String>> par: ejercicio) {
			aux.put(par.getKey(), par.getValue());
		}
		
		System.out.println(ejercicio.check(aux) ? "¡¡¡OK!!!" : "¡¡¡Error!!!");
	}	
}