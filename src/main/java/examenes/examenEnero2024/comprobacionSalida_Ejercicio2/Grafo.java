package examenes.examenEnero2024.comprobacionSalida_Ejercicio2;

import java.util.Map.*;
import java.util.TreeMap;

public class Grafo<T extends Comparable<T>>{
	private TreeMap<Nodo<T>,TreeMap<Nodo<T>,Integer>> data = new TreeMap<>();
	public void add(Nodo<T> origen,Nodo<T> destino){
		if(origen.equals(destino)) return;
		TreeMap<Nodo<T>,Integer> value = this.data.get(origen);
		if(value == null) this.data.put(origen,value = new TreeMap<>());
		Integer cont = value.get(destino);
		value.put(destino,cont==null ? 1: cont+1);
	}
	
	@Override
	public String toString() {
		String res = "";
		for(Entry<Nodo<T>,TreeMap<Nodo<T>,Integer>> entry :
		this.data.entrySet()){
			res += entry.getKey().toString() + "->" +
			entry.getValue().toString()+"\n";
		}
		return res;
	}
	
	public static void main(String[] args) {
		Grafo<String> grafo = new Grafo<>();
		Nodo<String> nodo1 = new Nodo<>("espana","italia","suiza");
		Nodo<String> nodo2 = new Nodo<>("argentina");
		Nodo<String> nodo3 = new Nodo<>("ghana","angola","argelia");
		Nodo<String> nodo4 = new Nodo<>("india","indonesia","iran");
		grafo.add(nodo1,nodo2);
		grafo.add(nodo1,nodo2);
		grafo.add(nodo1,nodo3);
		grafo.add(nodo2,nodo3);
		grafo.add(nodo3,nodo2);
		grafo.add(nodo1,nodo2);
		grafo.add(nodo1,nodo4);
		grafo.add(nodo4,nodo3);
		grafo.add(nodo3,nodo2);
		System.out.println(grafo);
	}
}