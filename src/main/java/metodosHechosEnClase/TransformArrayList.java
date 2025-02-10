package metodosHechosEnClase;

import java.util.ArrayList;
import practicas.auxiliar.Par;

public class TransformArrayList {
	
	private ArrayList<Par<String, Integer>> datos = new ArrayList<>();
    
    public void add(Par<String, Integer> par) {
        datos.add(par);
    }
	
    /*
	 * Transformar un arraylist de pares clave - valor a otra estructura donde,
	 * los valores sean las claves y se asocien a un arraylist de valores que 
	 * contienen dicha clave
	 */
	public ArrayList<Par<Integer, ArrayList<String>>> transform() {
        ArrayList<Par<Integer, ArrayList<String>>> result = new ArrayList<>();
        for (Par<String, Integer> par : datos) {
            int pos = -1;
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).getKey().equals(par.getValue())) {
                    pos = i;
                    break;
                }
            }
            if (pos == -1) {
                result.add(new Par<>(par.getValue(), new ArrayList<>()));
                pos = result.size() - 1;
            }
            result.get(pos).getValue().add(par.getKey());
        }
        return result;
	}
	
	@Override
    public String toString() {
        return this.datos.toString();
    }
	
	public static void main(String[] args) {
	    TransformArrayList conversor = new TransformArrayList();

	    conversor.add(new Par<>("ABEL", 30));
	    conversor.add(new Par<>("MARÍA", 50));
	    conversor.add(new Par<>("NAZAR", 30));
	    conversor.add(new Par<>("PEPE", 50));
	    conversor.add(new Par<>("ZACARÍAS", 28));

	    ArrayList<Par<Integer, ArrayList<String>>> resultado = conversor.transform();

	    System.out.println("Datos de origen:");
	    for (Par<String, Integer> par : conversor.datos) {
	        System.out.println(par.getKey() + ": " + par.getValue());
	    }

	    System.out.println("\nResultado de la transformación:");
	    for (Par<Integer, ArrayList<String>> par : resultado) {
	        System.out.println(par.getKey() + ": " + par.getValue());
	    }
	}
}
