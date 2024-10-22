package prueba_de_nivel_en_Java;
import java.util.ArrayList;
import java.util.LinkedList;

import practicas.auxiliar.AVLTree;

//Primer test: Mal
//Segundo test: Bien
//Tercer test:

public class ComparatorStr2 {

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        AVLTree<Integer> arbol = new AVLTree<>();
        for (int i = 0; i < 10; i++) {
        	//Añado al array (Si el número es par -1, si es impar dejo la i
			array.add(i%2 == 0 ? -1 : i);
		}
        //Al añadirse a AVLTree, los valores repetidos se dejan como 1 solo
        for (Integer valor : array) {
			arbol.add(valor);
		}
        System.out.println(arbol);
    }
}
