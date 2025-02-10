package metodosHechosEnClase;

import java.util.ArrayList;
import java.util.TreeSet;
import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

public class Transform_y_Reduce_ArrayListToAVLTree {
	
	private ArrayList<Par<String, TreeSet<Integer>>> datosOriginal;
	private ArrayList<Par<String, AVLTree<Integer>>> datosOriginal2;
	private AVLTree<Par<String, Double>> datosDestino;
	private AVLTree<Par<String, Double>> datosDestino2;
	
	public Transform_y_Reduce_ArrayListToAVLTree() {
        this.datosOriginal = new ArrayList<>();
        this.datosDestino = new AVLTree<>();
        this.datosOriginal2 = new ArrayList<>();
        this.datosDestino2 = new AVLTree<>();
    }
	
	public void add(String key, Integer... values) {
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer value : values) {
            set.add(value);
        }
        datosOriginal.add(new Par<>(key, set));
        
        AVLTree<Integer> avl = new AVLTree<>();
        for (Integer value : values) {
            avl.add(value);
        }
        datosOriginal2.add(new Par<>(key, avl));
    }
	
	//CREAR MÉTODO TRANSFORM
	public AVLTree<Par<String, Double>> transform() {
        for (Par<String, TreeSet<Integer>> par : datosOriginal) {
            int suma = 0;
            for (Integer valor : par.getValue()) {
                suma += valor;
            }
            datosDestino.add(new Par<>(par.getKey(), (double) suma / par.getValue().size()));
        }
        return datosDestino;
    }

	//CREAR MÉTODO REDUCE
	public void reduce() {
        AVLTree<Par<String, ArrayList<Integer>>> aux = new AVLTree<>();
        for (Par<String, AVLTree<Integer>> parCurr : datosOriginal2) {
        	
            ArrayList<Integer> valores = new ArrayList<>();
            for (Integer valor : parCurr.getValue()) {
            	valores.add(valor);
            }

            Par<String, ArrayList<Integer>> parAux = aux.find(new Par<>(parCurr.getKey(), null));
            if (parAux == null) aux.add(parAux = new Par<>(parCurr.getKey(), new ArrayList<>()));
            parAux.getValue().addAll(valores);
        }

        for (Par<String, ArrayList<Integer>> parAux2 : aux) {
            datosDestino2.add(new Par<>(parAux2.getKey(), mediaAritmetica(parAux2.getValue())));
        }
    }
	
	private Double mediaAritmetica(ArrayList<Integer> values) {
        int suma = 0;
        for (Integer valor : values) {
            suma += valor;
        }
        return (double) suma / values.size();
    }
	
	public void printDatosOriginal() {
        System.out.println("Datos originales para transform:");
        for (Par<String, TreeSet<Integer>> par : datosOriginal) {
            System.out.println(par.getKey() + ": " + par.getValue());
        }
    }
	
	public void printDatosDestino() {
		System.out.println("\nDatos transformados a AVLTree:");
        for (Par<String, Double> par : datosDestino) {
            System.out.println(par.getKey() + ": " + par.getValue());
        }
    }
	
	public void printDatosOriginal2() {
        System.out.println("\nDatos originales para reduce:");
        for (Par<String, AVLTree<Integer>> par : datosOriginal2) {
            System.out.println(par.getKey() + ": " + par.getValue());
        }
    }
	
	public void printDatosDestino2() {
        System.out.println("\nDatos reducidos:");
        for (Par<String, Double> par : datosDestino2) {
            System.out.println(par.getKey() + ": " + par.getValue());
        }
    }
	
    public static void main(String[] args) {
        Transform_y_Reduce_ArrayListToAVLTree transformador = new Transform_y_Reduce_ArrayListToAVLTree();

        transformador.add("pepe", 1, 2, 3, 4, 5);
        transformador.add("maria", 2, 3, 4);
        transformador.add("pablo", 9);
        transformador.add("pepa", 4, 4);

        transformador.printDatosOriginal();
        transformador.transform();
        transformador.printDatosDestino();

        transformador.printDatosOriginal2();
        transformador.reduce();
        transformador.printDatosDestino2();
    }
}
