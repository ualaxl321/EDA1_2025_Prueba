package examenes.examenFebrero2024;
import java.util.*;
import java.util.Map.Entry;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;
public class GestionArticulos {

	private final TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> data = new TreeMap<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ArrayList<Par<String, TreeSet<String>>> reduceAL(){
		ArrayList<Par<String, TreeSet<String>>> result = new ArrayList<>();
		for (TreeMap<String, TreeMap<String, Integer>> autorCurr : data.values()) {
			for(Entry<String, TreeMap<String, Integer>> artEntry : autorCurr.entrySet()) {
				for (String wordCurr : artEntry.getValue().keySet()) {
					TreeSet<String> artsSet;
					int pos = result.indexOf(new Par<>(wordCurr, artsSet = new TreeSet<>()));
					if(pos == -1) result.add(new Par<>(wordCurr, artsSet = new TreeSet<>()));
					artsSet.add(artEntry.getKey());
				}
			}
		}
		return result;
	}
	
	public AVLTree<Par<String, TreeSet<String>>> reduceAVL(){
		AVLTree<Par<String, TreeSet<String>>> result = new AVLTree<>();
		for (TreeMap<String, TreeMap<String, Integer>> autorCurr : data.values()) {
			for(Entry<String, TreeMap<String, Integer>> artEntry : autorCurr.entrySet()) {
				for (String wordCurr : artEntry.getValue().keySet()) {
					
				}
			}
		}
		return result;
	}
}
