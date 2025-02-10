package practicas.prueba01;

import java.util.ArrayList;
import java.util.Collections;
import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

public class Reduce {
	private AVLTree<Par<String, ArrayList<Integer>>> estructuraOriginal = new AVLTree<>();
	private ArrayList<Par<String, String>> estructuraDestino = new ArrayList<>();
	
	public void add(String clave, Integer...valores) {
		Par<String, ArrayList<Integer>> parCurr = this.estructuraOriginal.find(new Par<>(clave, null));
		if (parCurr == null) {
			this.estructuraOriginal.add(parCurr = new Par<>(clave, new ArrayList<>()));
		}
		for (Integer valor: valores) {
			parCurr.getValue().add(valor);
		}
	}
	
	/**
	 * Pasamos datos de original a destino
	 * Calculamos los valores asociados a sus claves y lo almacenamos en destino con los decimales 
	 * De ahí el uso de format y el casteo a double
	 * Como necesitamos el min, hago uso de collections visto cosas java tema 2
	 * Lo metemos en destino como cadena de texto
	 */
	public void reduce() {
		AVLTree<Par<String, ArrayList<Integer>>> aux = new AVLTree<>();
		for (Par<String, ArrayList<Integer>> parCurr : estructuraOriginal) {
			Par<String, ArrayList<Integer>> parAux = aux.find(parCurr);
			if (parAux == null) aux.add(parAux = new Par<>(parCurr.getKey(), new ArrayList<>()));
			parAux.getValue().addAll(parCurr.getValue());
		}
		for (Par<String, ArrayList<Integer>> parAux2 : aux) {
			estructuraDestino.add(new Par<>(parAux2.getKey(), String.format("%.2f", (double) Collections.min(parAux2.getValue()))));
		}
	}
	
	public boolean check() {
		//System.out.println(this.estructuraOriginal.toString());
		//System.out.println(this.estructuraDestino.toString());
		return this.estructuraDestino.toString().equals("[clave00 <1.00>, clave01 <1.00>, clave03 <6.00>]");
	}
	
	public static void main(String[] args) {
		Reduce prueba = new Reduce();
		prueba.add("clave00", 3, 4, 5, 6, 7, 8, 9);
		prueba.add("clave01", 1, 2, 3, 4, 5, 6, 7);
		prueba.add("clave03", 6, 7, 8);
		prueba.add("clave00", 1, 2);
		prueba.add("clave01", 8, 9);
		
		prueba.reduce();
	
		System.out.println(prueba.check() ? "¡¡¡OK!!!" : "Error");
	}
}
