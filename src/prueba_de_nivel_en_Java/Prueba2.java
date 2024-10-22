package prueba_de_nivel_en_Java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//PREGUNTAR POR LA LÓGICA DE ESTE EJERCICIO
//Primer test: Bien
//Segundo test: Mal
//Tercer test:

public class Prueba2 {
	private ArrayList<PriorityQueue<Integer>> datos;
	
	public Prueba2() {
		this.datos = new ArrayList<>(20);
	}
	
	public void add(int indice, Integer...valores) {
		for (int i = this.datos.size()-1; i < indice; i++) {
			datos.add(null);
		}
		
//		if(datos.get(indice) == null) datos.set(indice, new PriorityQueue<>(Comparator.naturalOrder()));{
//			datos.get(indice).addAll(List.of(valores));
//		}
		
		if(datos.get(indice) == null) datos.set(indice, new PriorityQueue<>(Comparator.reverseOrder()));{
			datos.get(indice).addAll(List.of(valores));
		}
	}
	
	@Override
	public String toString () {
		return this.datos.toString();
	}
	
	public static void main(String[] args) {
		Prueba2 prueba2 = new Prueba2();
		prueba2.add(3, 1, 2, 3, 4, 5, 6);
		prueba2.add(1, 1, 2, 3, 4);
		prueba2.add(1,7);
		prueba2.add(3, 5, 6);
		prueba2.add(5, 1, 1);
		System.out.println(prueba2);
	}
}
