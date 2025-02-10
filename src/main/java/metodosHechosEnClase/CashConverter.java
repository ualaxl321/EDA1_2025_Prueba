package metodosHechosEnClase;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CashConverter {
	
	private TreeMap<String, TreeMap<String, ArrayList<String>>> datos;
	
	public CashConverter() {
        this.datos = new TreeMap<>();
    }

	public void add(String atributo, String articulo, String palabra) {
        if (!datos.containsKey(atributo)) datos.put(atributo, new TreeMap<>());
        if (!datos.get(atributo).containsKey(articulo)) datos.get(atributo).put(articulo, new ArrayList<>());
        datos.get(atributo).get(articulo).add(palabra);
    }
    
    public void mostrarOrigen() {
        for (Entry<String, TreeMap<String, ArrayList<String>>> atribEntry : datos.entrySet()) {
            System.out.println(atribEntry.getKey());
            for (Entry<String, ArrayList<String>> artEntry : atribEntry.getValue().entrySet()) {
                System.out.print("    " + artEntry.getKey() + " --> ");
                System.out.println(String.join(", ", artEntry.getValue()));
            }
        }
    }
    
    public void mostrarResultado(TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> result) {
        for (Entry<String, TreeMap<String, TreeMap<String, Integer>>> atribEntry : result.entrySet()) {
            System.out.println(atribEntry.getKey());
            for (Entry<String, TreeMap<String, Integer>> artEntry : atribEntry.getValue().entrySet()) {
                System.out.println("    " + artEntry.getKey());
                for (Entry<String, Integer> countEntry : artEntry.getValue().entrySet()) {
                    System.out.println("        " + countEntry.getKey() + " --> " + countEntry.getValue());
                }
            }
        }
    }

    public TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> converter() {
        TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> result = new TreeMap<>();

        for (Entry<String, TreeMap<String, ArrayList<String>>> entry : this.datos.entrySet()) {
            TreeMap<String, TreeMap<String, Integer>> aux = new TreeMap<>();
            for (Entry<String, ArrayList<String>> entry2 : entry.getValue().entrySet()) {
                String articulo = entry2.getKey();
                ArrayList<String> valores = entry2.getValue();
                TreeMap<String, Integer> aux2 = new TreeMap<>();
                for (String palabra : valores) {
                    if (!aux2.containsKey(palabra)) aux2.put(palabra, 1);
                    	aux2.put(palabra, aux2.get(palabra) + 1);
                }
                aux.put(articulo, aux2);
            }
            result.put(entry.getKey(), aux);
        }
        return result;
	}

	public static void main(String[] args) {
        CashConverter gg = new CashConverter();
        
        gg.add("atrib00", "art00", "u01");
        gg.add("atrib00", "art00", "u02");
        gg.add("atrib00", "art00", "u01");
        gg.add("atrib00", "art00", "u02");
        gg.add("atrib00", "art00", "u08");
        gg.add("atrib00", "art00", "u02");
        gg.add("atrib00", "art01", "u01");
        gg.add("atrib00", "art01", "u08");
        gg.add("atrib00", "art01", "u04");
        gg.add("atrib00", "art01", "u01");
        gg.add("atrib01", "art03", "u01");
        gg.add("atrib01", "art03", "u01");
        gg.add("atrib01", "art03", "u01");
        gg.add("atrib01", "art03", "u01");
        gg.add("atrib01", "art03", "u01");
        gg.add("atrib01", "art03", "u01");
        gg.add("atrib01", "art03", "u01");
        gg.add("atrib02", "art04", "u03");
        gg.add("atrib02", "art04", "u03");
        gg.add("atrib02", "art04", "u03");

        System.out.println("ESTRUCTURA ORIGEN:");
        gg.mostrarOrigen();
        System.out.println();

        System.out.println("ESTRUCTURA RESULTADO:");
        TreeMap<String, TreeMap<String, TreeMap<String, Integer>>> result = gg.converter();
        gg.mostrarResultado(result);
    }
}
