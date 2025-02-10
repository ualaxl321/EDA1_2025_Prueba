package practicas.practica02.parte03;

import java.util.ArrayList;

import practicas.auxiliar.BSTree;
import practicas.auxiliar.Par;

public class MyTreeMap<K extends Comparable<K>,V> {
	
	private final BSTree<Par<K,V>> treePar;

	public MyTreeMap(){
		this.treePar = new BSTree<>();
	}

	public V put(K key, V value) {
		Par<K,V> parCurr = this.treePar.find(new Par<>(key, null));
		V vOld = null;
		if (parCurr == null) {
			this.treePar.add(new Par<>(key, value));
		} else {
		vOld = parCurr.getValue();
		parCurr.setValue(value);
		}
		return vOld;
	}
	
	public V get(K key) {
		Par <K, V> parCurr = this.treePar.find(new Par<>(key, null));
		return (parCurr != null) ? parCurr.getValue() : null;
	}
	
	public boolean containsKey(K key) {
		return this.get(key) != null;
	}
	
	public void clear() {
		this.treePar.clear();
	}
	
	public boolean isEmpty() {
		return this.treePar.isEmpty();
	}

	public int size() {
		return this.treePar.size();
	}

	public ArrayList<K> keySet(){
		ArrayList<K> resultado  = new ArrayList<>();
		for (Par<K, V> par : this.treePar) {
			resultado.add(par.getKey());
		}
		return resultado;
	}
	
	public ArrayList<V> values(){
		ArrayList<V> resultado  = new ArrayList<>();
		for (Par<K, V> par : this.treePar) {
			resultado.add(par.getValue());
		}
		return resultado;
	}
	
	public ArrayList<Par<K,V>> pairSet(){
		ArrayList<Par<K,V>> resultado  = new ArrayList<>();
		for (Par<K, V> par : this.treePar) {
			resultado.add(par);
		}
		return resultado;
	}

	@Override
	public String toString() {
		return this.pairSet().toString();
	}
}