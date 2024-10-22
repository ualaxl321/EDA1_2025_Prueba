package prueba_de_nivel_en_Java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import practicas.auxiliar.AVLTree;

//Primer test: Mal
//Segundo test: Bien
//Tercer test:

public class ComparatorStr3 {

    public static void main(String[] args) {
        String[] arr = {"hola", "que", "tal", "Hola", "adiós"};
        AVLTree<String> arbol = new AVLTree<>();

        for (String cadena : arr) {
            arbol.add(cadena.toLowerCase());
        }

        int i = 0;
        for (String cadena : arbol) {
            assertEquals(cadena, arr[i++]); //Al repetirse Hola en lower case, dará error.
        }

        System.out.println("OK!!!");
    }
}
