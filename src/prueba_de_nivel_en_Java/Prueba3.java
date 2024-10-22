package prueba_de_nivel_en_Java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

//Primer test: Mal
//Segundo test: Bien
//Tercer test:

public class Prueba3 {
    private AVLTree<Par<String, ArrayList<Integer>>> datos;

    public Prueba3() {
        this.datos = new AVLTree<>();
    }

    public void add(String clave, Integer... valores) {
        Par<String, ArrayList<Integer>> parCurrent = this.datos.find(new Par<>(clave, null));
        if (parCurrent == null) {
            parCurrent = new Par<>(clave, new ArrayList<>());
            this.datos.add(parCurrent);
        }
        parCurrent.getValue().addAll(List.of(valores));
    }

    public ArrayList<Par<Integer, ArrayList<String>>> transform() {
        ArrayList<Par<Integer, ArrayList<String>>> result = new ArrayList<>();
        for (Par<String, ArrayList<Integer>> par : this.datos) {
            for (Integer valor : par.getValue()) {
                int pos = result.indexOf(new Par<>(valor, null));
                if (pos == -1) {
                    result.add(new Par<>(valor, new ArrayList<>()));
                    pos = result.size() - 1;
                }
                result.get(pos).getValue().add(par.getKey());
            }
        }
        result.sort(Comparator.reverseOrder());
        return result;
    }

    @Override
    public String toString() {
        return this.datos.toString();
    }

    public static void main(String[] args) {
        Prueba3 prueba3 = new Prueba3();
        prueba3.add("pepe", 1, 4, 5, 4, 5, 1);
        prueba3.add("maria", 2, 4, 5, 4);
        prueba3.add("pepe", -1, -2, -1);
        prueba3.add("maria", -2, -1, 4);
        
        //Muestro datos (Estos se ordenarán por la clave)
        System.out.println(prueba3);
        
        //Con transform, sacamos por orden inverso los números, 5 - 4 - 2 - 1 - -1
        //Con el número los ordenamos por clave, por ejemplo, 5 [maría(Aparece 1 vez), pepe, pepe (Aparece 2 veces)]
        //4 [maría, maría, maría (Aparece 3 veces), pepe, pepe (Aparece 2 veces)]
        System.out.println(prueba3.transform().toString());
    }
}
