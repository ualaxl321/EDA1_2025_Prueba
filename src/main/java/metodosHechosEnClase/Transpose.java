package metodosHechosEnClase;

import java.util.Map.Entry;
import java.util.TreeMap;

public class Transpose<Vertex>{
	private TreeMap<Vertex, TreeMap<Vertex, Double>> adyacenList;
	
	public TreeMap<Vertex, TreeMap<Vertex, Double>> transpose() {
		TreeMap<Vertex, TreeMap<Vertex, Double>> result = new TreeMap<>();
		for (Entry<Vertex, TreeMap<Vertex, Double>> parMain : adyacenList.entrySet()) {
			for (Entry<Vertex, Double> parSecond : parMain.getValue().entrySet()) {
				TreeMap<Vertex, Double> value = result.get(parSecond.getKey());
				if (value == null) result.put(parSecond.getKey(), value = new TreeMap<>());
				value.put(parMain.getKey(), parSecond.getValue());
			}
		}
		return result;
	}
}
