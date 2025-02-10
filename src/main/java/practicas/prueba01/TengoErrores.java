package practicas.prueba01;

import java.util.ArrayList;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Format;
import practicas.auxiliar.Par;

public class TengoErrores {
	
	private AVLTree<Par<String, AVLTree<Par<String, ArrayList<String>>>>> datos;
	
	public TengoErrores() {
		this.datos = new AVLTree<>();
	}

	/**
	 * Añadimos valores a datos
	 * Si las K ya existen, añadimos valor nuevo
	 * Si no, lo creamos
	 * 
	 * @param clave01 Estudiante
	 * @param clave02 Asignatura
	 * @param valoraciones
	 */
	public void add(String clave01, String clave02, Double...valoraciones) {
		Par<String, AVLTree<Par<String, ArrayList<String>>>> par1 = this.datos.find(new Par<>(clave01, null));
		if (par1 == null) this.datos.add(par1 = new Par<>(clave01, new AVLTree<>()));
		Par<String, ArrayList<String>> par2 = par1.getValue().find(new Par<>(clave02, null)); 
		if (par2 == null) {
			par2 = new Par<>(clave02, new ArrayList<>());
			par1.getValue().add(par2);
		}
		for (Double valor: valoraciones) {
			if (valor == null) continue;
			par2.getValue().add(Format.formatDouble(valor, 3));
		}
	}
	
	@Override
	public String toString() {
		return this.datos.toString();
	}
	
	public static void main(String[] args) {
		TengoErrores arreglame = new TengoErrores();
		arreglame.add("estudiante02", "asignatura01", 1., 9.);
		arreglame.add("estudiante02", "asignatura02", 10., 6.);
		arreglame.add("estudiante01", "asignatura01", 8., null, 8.);
		arreglame.add("estudiante01", "asignatura02", 8., 4.);
		
		String salidaEsperada = "[estudiante01 <[asignatura01 <[8.000, 8.000]>, asignatura02 <[8.000, 4.000]>]>, estudiante02 <[asignatura01 <[1.000, 9.000]>, asignatura02 <[10.000, 6.000]>]>]";
		
		//Aquí puedes mostrar por Consola arreglame.toString()

		System.out.println(salidaEsperada.equals(arreglame.toString()) ? "¡¡¡OK!!!" : "Error");
	}

}
