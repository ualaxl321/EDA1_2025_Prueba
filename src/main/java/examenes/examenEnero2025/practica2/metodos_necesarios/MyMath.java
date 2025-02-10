package examenes.examenEnero2025.practica2.metodos_necesarios;

import java.util.ArrayList;

public class MyMath {
	public static double calculaMedia(ArrayList<Double> notas) {
		double suma = .0;
		for (Double nota: notas) {
			suma += nota;
		}
		return suma / notas.size();
	}
}
