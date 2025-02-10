package practicas.practica03.parte03;

import java.util.*;
import java.util.Map.Entry;

import practicas.practica03.parte04.Nodo;

public class ColeccionNodos<T extends Comparable<T>> {
	
	protected final HashMap<NodoComponentes, HashSet<NodoComponentes>> data;

	public ColeccionNodos(){
		this.data = new HashMap<>();
	}
	
	public void add(NodoComponentes nodoOrigen, NodoComponentes nodoDestino) {
		//1 get()
		//1 put()
		HashSet<NodoComponentes> nodos = this.data.get(nodoOrigen);
		if (nodos == null) this.data.put(nodoOrigen, nodos = new HashSet<>());
		if (!nodos.contains(nodoDestino)) nodos.add(nodoDestino);
	}

	public int size() {
		return this.data.size();
	}

	public void clear(){
		this.data.clear();
	}
	    
	private TreeMap<NodoComponentes, TreeSet<NodoComponentes>> toOrderedCollection() {
		TreeMap<NodoComponentes, TreeSet<NodoComponentes>> result = new TreeMap<>();

		//1 for()
		for (Entry<NodoComponentes, HashSet<NodoComponentes>> entrada : this.data.entrySet()) {
			TreeSet<NodoComponentes> destino = new TreeSet<>(entrada.getValue());
			result.put(entrada.getKey(), destino);
		}
		
		return result;
	}

	@Override
	public String toString() {
		TreeMap<NodoComponentes, TreeSet<NodoComponentes>> aux = this.toOrderedCollection();
		String result = "";
		//2 for()
		//Fíjate el formato de salidaEsperada especificado en el método main()
		for (NodoComponentes origen : aux.keySet()) {
	        result += origen + "\n";
	        for (NodoComponentes destino : aux.get(origen)) {
	            result += "\t" + destino + "\n";
	        }
	    }
		return result;
	}

	
	public static void main(String[]args) {
		ColeccionNodos gestion = new ColeccionNodos();
		Componente comp01 = new Componente("comp01");
		Componente comp02 = new Componente("comp02");
		Componente comp03 = new Componente("comp03");
		Componente comp04 = new Componente("comp04");
		Componente comp05 = new Componente("comp05");
		
		NodoComponentes nodo01 = new NodoComponentes(comp01, comp03, comp05, comp04, comp01);
		NodoComponentes nodo02 = new NodoComponentes(comp01, comp02, comp02);
		NodoComponentes nodo03 = new NodoComponentes(comp05, comp03, comp01, comp05);
		NodoComponentes nodo04 = new NodoComponentes(comp05);
	
		gestion.add(nodo01, nodo02);
		gestion.add(nodo02, nodo03);
		gestion.add(nodo02, nodo04);
		gestion.add(nodo03, nodo01);
		gestion.add(nodo03, nodo04);
		gestion.add(nodo04, nodo02);
		gestion.add(nodo04, nodo01);
		gestion.add(nodo01, nodo02);
		
		String salidaEsperada = "[comp01, comp02]\n"
									+ "\t[comp01, comp03, comp05]\n"
									+ "\t[comp05]\n"
								+ "[comp01, comp03, comp04, comp05]\n"
									+ "\t[comp01, comp02]\n"
								+ "[comp01, comp03, comp05]\n"
									+ "\t[comp01, comp03, comp04, comp05]\n"
									+ "\t[comp05]\n"
								+ "[comp05]\n"
									+ "\t[comp01, comp02]\n"
									+ "\t[comp01, comp03, comp04, comp05]\n";

		System.out.println(salidaEsperada.equals(gestion.toString()) ? "¡¡¡OK!!!" : "¡¡¡Error!!!");
	}
}