package metodosHechosEnClase;

import java.util.TreeMap;
import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

public class Converter {
	private TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> datosDestino;
	private AVLTree<Par<String, AVLTree<Par<String, AVLTree<Par<String, Integer>>>>>> datosOrigen;
	
	public void converter() {
		TreeMap<String,TreeMap<String, Integer>> aux;
		for (Par<String, AVLTree<Par<String, AVLTree<Par<String, Integer>>>>> par : datosOrigen) {
			datosDestino.put(par.getKey(), aux = new TreeMap<>());
			TreeMap<String, Integer> aux2;
			for (Par<String, AVLTree<Par<String, Integer>>> parSecond: par.getValue()) {
				aux.put(parSecond.getKey(), aux2 = new TreeMap<>());
				for(Par<String, Integer> parThird : parSecond.getValue()) {
					aux2.put(parThird.getKey(), parThird.getValue());
				}
			}
		}
	}
}
