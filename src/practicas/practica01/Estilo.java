package practicas.practica01;

import java.util.ArrayList;
import java.util.Iterator;

public class Estilo {
	
//	Acceso ArrayList desde main
//	private ArrayList<String> arr = new ArrayList<String>();
//	public static void main(String[]args) {
//		Estilo ejemplo = new Estilo();
//		ejemplo.arr.add("3");
//		System.out.println(ejemplo.arr.toString());
//	}
	
	
//	Acceso ArrayList desde variable local
//	public static void main(String[]args) {
//		ArrayList<String> arr = new ArrayList<String>();
//		arr.add("3");
//		System.out.println(arr.toString());
//	}
	
	
//	ArrayList 50 valores consecutivos
//	public static void main(String[]args) {
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		for (int i = 1; i < 50; i++) {
//			arr.add(String.valueOf(i));
//		}
//		System.out.println(arr.toString());
//	}
	
	
//	ArrayList 50 valores consecutivos con valores positivos o negativos con probabilidad del 50%
//	public static void main(String[]args) {
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		for (int i = 1; i < 50; i++) {
//			if (Math.random() < .5) {
//				arr.add(String.valueOf(i));
//			} else {
//				arr.add(String.valueOf(-i));
//			}
//		}
//		System.out.println(arr.toString());
//	}
	
	
//	Mismo problema con operador ternario
//	public static void main(String[]args) {
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		for (int i = 1; i < 50; i++) {
//			arr.add(String.valueOf(Math.random() < .5 ? i : -i));
//		}
//		System.out.println(arr.toString());
//	}
	
	
//	Mismo problema contando negativos de 3 formas
//	public static void main(String[]args) {
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		for (int i = 1; i < 50; i++) {
//			arr.add(String.valueOf(Math.random() < .5 ? i : -i));
//		}
//		
//		int numNegativos1 = 0;
//		int numNegativos2 = 0;
//		int numNegativos3 = 0;
//		
//		//FORMA 1 FOR
//		for (int i = 0; i < arr.size(); i++) {
//			if(Integer.valueOf(arr.get(i)) < 0) numNegativos1++;
//		}
//		
//		//FORMA 2 Iterator
//		//Esta nunca la vamos a utilizar, mucho código
//		Iterator<String> it = arr.iterator();
//		while(it.hasNext()) {
//			if (Integer.valueOf(it.next())< 0) numNegativos2++;
//		}
//		
//		//FORMA 3 FOR EACH
//		for(String valorStr : arr) {
//			if (Integer.valueOf(valorStr) < 0) numNegativos3++;
//		}
//		
//		System.out.println("El número de elementos negativos según forma 1: " + numNegativos1);
//		System.out.println("El número de elementos negativos según forma 2: " + numNegativos2);
//		System.out.println("El número de elementos negativos según forma 3: " + numNegativos3);
//		System.out.println(arr.toString());
//	}

	
//	Mismo problema pero si encuentra un negativo lo transforma a positivo
//	public static void main(String[]args) {
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		for (int i = 1; i < 50; i++) {
//			arr.add(String.valueOf(Math.random() < .5 ? i : -i));
//		}
//		
//		System.out.println("Antes de modificar la estructura: " + arr.toString());
//		
//		for (int i = 0; i < arr.size(); i++) {
//			if (Integer.valueOf(arr.get(i)) < 0) arr.set(i, String.valueOf(- Integer.valueOf(arr.get(i))));
//		}
//		
//		System.out.println("Despues de modificar la estructura: " + arr.toString());
//	}
//	
	

//	Recorrer la estructura y en el momento que encuentro - informo y termino
//	public static void main(String[]args) {
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		for (int i = 1; i < 50; i++) {
//			arr.add(String.valueOf(Math.random() < .5 ? i : -i));
//		}
//		
//		for (String valorStr : arr) {
//			if (Integer.valueOf(valorStr) <0) {
//				System.out.println("He encontrado el primer valor negativo: " + valorStr);
//				break;
//			}
//			System.out.println("Valor positivo hallado");
//			//ELSE NO ES NECESRIO POR EL BREAK
////			} else {
////				System.out.println("Valor positivo hallado");
////			}
//		}
//	}
	
	
//	Recorrer la estructura y en el momento que encuentro - informo y termino con una instrucción
//	public static void main(String[]args) {
//		ArrayList<String> arr = new ArrayList<String>();
//		
//		for (int i = 1; i < 50; i++) {
//			arr.add(String.valueOf(Math.random() < .5 ? i : -i));
//		}
//		
//		for (String valorStr : arr) {
//			if (Integer.valueOf(valorStr) >= 0)  continue;
//			System.out.println("He encontrado el primer valor negativo: " + valorStr);
//			break;
//		}
//	}
	 
	
//	Hacemos uso de While(true)
	public static void main(String[]args) {
		ArrayList<String> arr = new ArrayList<String>();

		for (int i = 1; i < 50; i++) {
			arr.add(String.valueOf(Math.random() < .99 ? i : -i));
		}
		
		System.out.println(arr.toString());
		
		int i = 0;
		while (true) {
			if (i == arr.size()) {
				System.out.println("No se ha encontrado ningún valor negativo");
				break;
			}
			if (Integer.valueOf(arr.get(i)) >= 0) {
				i++;
				continue;
			}
			
			System.out.println("He encontrado el primer valor negativo: " + arr.get(i));
			break;
		}
		System.out.println("He terminado");
	}
	
//BUSCAR MEJORA CON BREAK Y CONTINUE Y BUCLE FINITO
	
	
	
}