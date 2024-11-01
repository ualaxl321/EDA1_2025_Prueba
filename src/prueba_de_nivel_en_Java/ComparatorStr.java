package prueba_de_nivel_en_Java;

import java.util.*;

// Primer test: Bien
// Segundo test: Bien
// Tercer test:

public class ComparatorStr implements Comparator<String> {
	
	// Cogemos el primer dato que llega y el segundo
	// Los comparamos
	// Como tenemos -Integer, ordernará los Strings de mayor a menor
	// Si fuese Integer, los ordenaría de forma natural, de menor a mayor
	// Esta ordenación se produce gracias a sort (El que ordena), -Integer los compara ordenándolos al revés
    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }

    public static void main(String[] args) {
        LinkedList<String> datos = new LinkedList<>();
        datos.add("1234");
        datos.add("123");
        datos.add("12345");
        datos.add("1");
        datos.add("123456");

        datos.sort(new ComparatorStr());
        System.out.println(datos);
    }
}
