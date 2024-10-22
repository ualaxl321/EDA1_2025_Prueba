package prueba_de_nivel_en_Java;

import java.util.ArrayList;
import java.util.Comparator;

//Primer test: Bien
//Segundo test: Bien
//Tercer test:

public class Ejercicio3 {
	public static void main(String[] args) {
	    ArrayList<ArrayList<Integer>> datos = new ArrayList<>();
	    ArrayList<Integer> aux; 
	    for (int i = 0; i < 5; i++) {
	        aux = new ArrayList<>(); //Creo mi ArrayList
	        for (int j = 0; j < 4; j++) { 
	            aux.add(j); //Lo relleno
	        }
	        //Ordeno el array de forma inversa si es impar o de natural si es par
	        aux.sort(i % 2 != 0 ? Comparator.reverseOrder() : Comparator.naturalOrder());
	        //Añado el array a datos
	        datos.add(aux);
	        //Borro el array y reinicio bucle, por lo que aux siempre está vacío y no se acumula
	        aux.clear();
	        
	        //Deja aux con null, pero no lo borra
	        //aux = null; 
	    }
	    //No muestra nada por que está vacío
	    System.out.println(datos);
	}

}

