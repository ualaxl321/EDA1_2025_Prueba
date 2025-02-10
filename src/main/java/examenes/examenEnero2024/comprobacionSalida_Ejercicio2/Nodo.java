package examenes.examenEnero2024.comprobacionSalida_Ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class Nodo<T extends Comparable<T>> implements Comparable<Nodo<T>>{

	private ArrayList<T> componentes;
	
	@SafeVarargs
	public Nodo(T...componentes) {
		this.componentes = new ArrayList<>(List.of(componentes));
	}
	
	@Override
	public String toString() {
		return this.componentes.toString();
	}
	
	@Override
	public int compareTo(Nodo<T> otra) {
		int lim1 = this.componentes.size();
		int lim2 = otra.componentes.size();
		
		for (int i = 0; i < Math.min(lim1, lim2); i++) {
			int cmp = this.componentes.get(i).compareTo(otra.componentes.get(i));
			if (cmp != 0) return cmp;
		}
		return lim1-lim2;
	}
}
