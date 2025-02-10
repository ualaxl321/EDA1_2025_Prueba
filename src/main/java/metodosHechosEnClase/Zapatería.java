package metodosHechosEnClase;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class Zapatería {
	
	private TreeMap<String, TreeMap<String, TreeSet<Integer>>> datos = new TreeMap<>();
	
	//CREAD MÉTODO ADD
	public void add(String tiendaId, String modeloId, Integer ... tallas) {
		TreeMap<String, TreeSet<Integer>> modelosCurr = this.datos.get(tiendaId);
		if (modelosCurr == null) this.datos.put(tiendaId, modelosCurr = new TreeMap<>());
		TreeSet<Integer> tallasModelo = modelosCurr.get(modeloId);
		if(tallasModelo == null) modelosCurr.put(modeloId, tallasModelo = new TreeSet<>());
		for (Integer talla : tallas) {
			tallasModelo.add(talla);
		}
	}
	
	public TreeSet<String> getTienda(int talla) {
		TreeSet<String> result = new TreeSet<>();
		for (Entry<String, TreeMap<String, TreeSet<Integer>>> parCurr : this.datos.entrySet()) {
			for (TreeSet<Integer> tallasCurr : parCurr.getValue().values()) {
				if (!tallasCurr.contains(talla)) continue;
				result.add(parCurr.getKey());
				break;
			}
		}
		return result;
	}
	
	public TreeSet<String> getModelo(int talla) {
		TreeSet<String> result = new TreeSet<>();
		for (TreeMap<String, TreeSet<Integer>> subMapa : this.datos.values()) {
			for (Entry<String, TreeSet<Integer>> parCurr : subMapa.entrySet()) {
				if (!parCurr.getValue().contains(talla)) continue;
				result.add(parCurr.getKey());
			}
		}
		return result;
	}
	
	public TreeSet<String> getTalla(String tiendaId) {
		TreeMap<String, TreeSet<Integer>> modelosCurr = this.datos.get(tiendaId);
		if (modelosCurr == null) return new TreeSet<>(); 
	    return new TreeSet<>(modelosCurr.keySet());
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, TreeMap<String, TreeSet<Integer>>> tienda : datos.entrySet()) {
            sb.append("Tienda: ").append(tienda.getKey()).append("\n");
            for (Entry<String, TreeSet<Integer>> modelo : tienda.getValue().entrySet()) {
                sb.append("\tModelo: ").append(modelo.getKey()).append(" -> Tallas: ").append(modelo.getValue()).append("\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Zapatería zapatitos = new Zapatería();

        zapatitos.add("Joma", "Vitali", 38, 40, 45);
        zapatitos.add("Joma", "Active", 42, 43, 48);
        zapatitos.add("Joma", "Casual", 42, 45);
        zapatitos.add("Adidas", "Response", 43);
        zapatitos.add("Adidas", "Breaknet", 37, 43, 42);
        zapatitos.add("Adidas", "Busenitz", 43, 44);
        zapatitos.add("Nike", "Air force", 42, 45, 48);
        zapatitos.add("Nike", "Dunklow", 37, 43, 47);
        zapatitos.add("Nike", "V2K", 42);
        zapatitos.add("Puma", "Speedcat", 42, 45, 48);
        zapatitos.add("Puma", "club", 37, 42, 43);
        zapatitos.add("Puma", "Palermo", 42);
        
        System.out.println("Datos de la zapatería:");
        System.out.println(zapatitos);
        
        System.out.println("\nPRUEBAS SOBRE EL TREEMAP");

        System.out.println("Salida de keySet(): Devuelve todas las tiendas.");
        System.out.println(zapatitos.datos.keySet());
        
        System.out.println("\nSalida de entrySet(): Devuelve los pares clave-valor de cada tienda");
        System.out.println(zapatitos.datos.entrySet());

        System.out.println("\nSalida de values(): Devuelve solo los valores de modelos");
        System.out.println(zapatitos.datos.values());

        Entry<String, TreeMap<String, TreeSet<Integer>>> primeraEntrada = zapatitos.datos.firstEntry();
        if (primeraEntrada != null) {
            System.out.println("\nSalida de getKey() haciendo uso de firstEntry sobre datos: ");
            System.out.println(primeraEntrada.getKey());
            System.out.println("\nSalida de getValue() haciendo uso de firstEntry sobre datos: ");
            System.out.println(primeraEntrada.getValue());
        } else {
            System.out.println("No hay datos en la zapatería.");
        }
    }
}
