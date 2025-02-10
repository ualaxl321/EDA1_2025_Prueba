package metodosHechosEnClase;

import java.util.Map.Entry;
import java.util.TreeMap;

public class Combine<Vertex> extends TreeMap<Vertex, TreeMap<Vertex, Double>>{

	private static final long serialVersionUID = 1L;

	public void combine(TreeMap<Vertex, TreeMap<Vertex, Double>> otro) {
		for(Entry<Vertex, TreeMap<Vertex, Double>> par : otro.entrySet()) {
			
			TreeMap<Vertex, Double> aux = this.get(par.getKey());
            if (aux == null) this.put(par.getKey(), aux = new TreeMap<>());
			
			for (Entry<Vertex, Double> par2 : par.getValue().entrySet()) {
                Double aux2 = aux.get(par2.getKey());
                aux.put(par2.getKey(), (par2.getValue() + (aux2 == null ? 0 : aux2)) / 2);
            }

		}
	}
}
