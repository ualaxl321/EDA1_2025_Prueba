package metodosHechosEnClase;

import java.util.ArrayList;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

public class TransformAVL {
	
	private AVLTree<Par<String, Integer>> datos = new AVLTree<>();
	
	public void add(Par<String, Integer> par) {
        datos.add(par);
    }
	
	/*
	 * Transformar una estructura de claves con valores a otra estructura donde,
	 * los valores sean las claves y se asocien a un arraylist de valores que 
	 * contienen dicha clave
	 */
	public AVLTree<Par<Integer, ArrayList<String>>> transform() {
	    AVLTree<Par<Integer, ArrayList<String>>> result = new AVLTree<>();
	    for (Par<String, Integer> par : datos) {
	        Par<Integer, ArrayList<String>> current = result.find(new Par<>(par.getValue(), null));
	        if (current == null) {
	            result.add(new Par<>(par.getValue(), new ArrayList<>()));
	            current = result.find(new Par<>(par.getValue(), null));
	        }
	        current.getValue().add(par.getKey());
	    }
	    return result;
	}
	
	@Override
    public String toString() {
        return this.datos.toString();
    }

    public static void main(String[] args) {
        TransformAVL conversor = new TransformAVL();

        conversor.add(new Par<>("ABEL", 30));
        conversor.add(new Par<>("MARÍA", 50));
        conversor.add(new Par<>("NAZAR", 30));
        conversor.add(new Par<>("PEPE", 50));
        conversor.add(new Par<>("ZACARÍAS", 28));

        AVLTree<Par<Integer, ArrayList<String>>> resultado = conversor.transform();

        System.out.println("Datos de origen: ");
        for (Par<String, Integer> par : conversor.datos) {
            System.out.println(par.getKey() + ": " + par.getValue());
        }
        System.out.println("\nResultado de la transformación: ");
        for (Par<Integer, ArrayList<String>> par : resultado) {
            System.out.println(par.getKey() + ": " + par.getValue());
        }
    }
}

