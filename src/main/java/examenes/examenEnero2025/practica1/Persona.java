package examenes.examenEnero2025.practica1;

public class Persona implements Comparable<Persona>{
	
	private String nombre;
	private int edad;
	
	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return this.nombre + "<" + this.edad + ">";
	}
	
	@Override
	public int compareTo(Persona o) {
		return this.nombre.compareTo(o.nombre);
	}
	
	public static void main (String[] args) {
		Nodo<Persona> nodo01 = new Nodo<>(new Persona("lola", 30), new Persona("juani", 35));
		Nodo<Persona> nodo02 = new Nodo<>(new Persona("emilio", 30), new Persona("emilia", 35));
		Nodo<Persona> nodo03 = new Nodo<>(new Persona("juani", 30), new Persona("noa", 35));
		Nodo<Persona> nodo04 = new Nodo<>(new Persona("lola", 30), new Persona("dolores", 35), new Persona("lola", 35));
		
		ColeccionNodos<Persona> coleccion = new ColeccionNodos<>();
		coleccion.add(nodo01, nodo02);
		coleccion.add(nodo01, nodo03);
		coleccion.add(nodo02, nodo01);
		coleccion.add(nodo02, nodo01);
		coleccion.add(nodo02, nodo04);
		coleccion.add(nodo02, nodo04);
		coleccion.add(nodo03, nodo04);
		coleccion.add(nodo04, nodo02);
		coleccion.add(nodo04, nodo01);
		System.out.println(coleccion.toString());
	}
}
