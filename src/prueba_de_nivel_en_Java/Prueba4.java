package prueba_de_nivel_en_Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import practicas.auxiliar.Par;


//Primer test: Bien
//Segundo test: Bien
//Tercer test:

public class Prueba4 {
    private ArrayList<Par<String, ArrayList<Integer>>> datos;

    public Prueba4() {
        this.datos = new ArrayList<>();
    }

    public void add(String clave, Integer... valores) {
        int pos = this.datos.indexOf(new Par<>(clave, null));
        if (pos == -1) {
            this.datos.add(new Par<>(clave, new ArrayList<>()));
            pos = this.datos.size() - 1;
        }
        this.datos.get(pos).getValue().addAll(List.of(valores));
    }

    public ArrayList<Par<String, Integer>> resume() {
        ArrayList<Par<String, Integer>> result = new ArrayList<>();
        for (Par<String, ArrayList<Integer>> par : this.datos) {
            //result.add(new Par<>(par.getKey(), par.getValue().size()));
        	result.add(new Par<>(par.getKey(), Collections.max(par.getValue())));
        }
        return result;
    }

    @Override
    public String toString() {
        return this.datos.toString();
    }

    public static void main(String[] args) {
        Prueba4 prueba4 = new Prueba4();
        prueba4.add("pepe", 1, 2, 3, 4, 5, 6);
        prueba4.add("maria", 1, 2, 3, 4);
//        prueba4.add("pepe", 7);
//        prueba4.add("maria", 5, 6);
      prueba4.add("pepe", 7, 8, 9);
      prueba4.add("maria", 5, 6, 7, 8, 9);
        System.out.println(prueba4);
        //Cuenta los numeros que hay por clave
        //Pepe <[1, 2, 3, 4, 5, 6, 7]>
        //[Pepe <7>]
        System.out.println(prueba4.resume().toString());
    }
}
