package practicas.prueba01;

import java.util.ArrayList;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

public class DumpData {
	private ArrayList<Par<String, AVLTree<Integer>>> estructuraOriginal = new ArrayList<>();
	private AVLTree<Par<String, AVLTree<Integer>>> estructuraDestino = new AVLTree<>();
	
	public void add(String clave, Integer...valores) {
		AVLTree<Integer> treeCurrent;
		int pos = this.estructuraOriginal.indexOf(new Par<>(clave, null));
		if (pos == -1) {
			this.estructuraOriginal.add(new Par<>(clave, treeCurrent = new AVLTree<>()));
		} else {
			treeCurrent = this.estructuraOriginal.get(pos).getValue();
		}
		for (Integer valor: valores) {
			treeCurrent.add(valor);
		}
	}
	
	/**
	 * Paso los datos de una estructura original T a otra destino T.
	 * Para la K en original copiamos sus V y
	 * los metemos en destino.
	 * T = K + V
	 */
	private void dumpData() {
		for (Par<String, AVLTree<Integer>> parCurr : estructuraOriginal) {
			AVLTree<Integer> numeroCurr = new AVLTree<>();
			for (Integer numero : parCurr.getValue()) {
				numeroCurr.add(numero);
			}
			estructuraDestino.add(new Par<>(parCurr.getKey(), numeroCurr));
		}
	}
	
	public boolean check() {
	//	System.out.println(this.estructuraOriginal.toString());
	//	System.out.println(this.estructuraOriginal.toString());
		return  this.estructuraOriginal.toString().equals(this.estructuraDestino.toString());
	}
	
	public static void main(String[] args) {
		DumpData prueba = new DumpData();
		prueba.add("clave00", 3, 4, 5, 6, 7, 8, 9);
		prueba.add("clave01", 1, 2, 3, 4, 5, 6, 7);
		prueba.add("clave03", 6, 7, 8);
		prueba.add("clave00", 1, 2);
		prueba.add("clave01", 8, 9);
		
		prueba.dumpData();
	
		System.out.println(prueba.check() ? "¡¡¡OK!!!" : "Error");
	}

}
