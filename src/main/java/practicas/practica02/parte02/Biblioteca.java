package practicas.practica02.parte02;

import java.util.ArrayList;
import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;

public class Biblioteca {
	private final String bibliotecaId;
 	private final AVLTree<Libro> libros;
	private final AVLTree<String> usuarios;
	private final AVLTree<Par<Libro, String>> prestamos;
	private final AVLTree<Par<String, ArrayList<Libro>>> historicoPrestamos;
	
	public Biblioteca(String bibliotecaId) {
		this.bibliotecaId = bibliotecaId;
		this.libros = new AVLTree<>();
		this.usuarios = new AVLTree<>();
		this.prestamos = new AVLTree<>();
		this.historicoPrestamos = new AVLTree<>();
	}
	
	public void addLibro(Libro libro) {
		this.libros.add(libro);
	}
	
	public void addLibro(String libroId) {
		this.libros.add(new Libro(libroId));
	}
		
	public void addUsuario(String usuarioId) {
		this.usuarios.add(usuarioId);
	}
	
	public void clear() {
		this.libros.clear();
		this.usuarios.clear();
		this.prestamos.clear();
		this.historicoPrestamos.clear();
	}
	
	public boolean prestarLibro(String usuarioId, String libroId) {
		if (this.libros.find(new Libro(libroId)) == null) return false;
		if (!this.usuarios.contains(usuarioId)) return false;
		if (this.prestamos.contains(new Par<>(libros.find(new Libro(libroId)), usuarioId))) return false;
		this.prestamos.add(new Par<>(libros.find(new Libro(libroId)), usuarioId));
		guardarHistorico(libros.find(new Libro(libroId)), usuarioId);
		return true;
	}

	private void guardarHistorico(Libro libro, String usuario) {
		Par<String, ArrayList<Libro>> prestamoExistente = historicoPrestamos.find(new Par<>(usuario, null));
		if (prestamoExistente != null) prestamoExistente.getValue().add(libro);
		ArrayList<Libro> listaDeLibros = new ArrayList<>();
        listaDeLibros.add(libro);
        historicoPrestamos.add(new Par<>(usuario, listaDeLibros));
	}

	public boolean devolverLibro(String usuarioId, String libroId) {
		if (this.libros.find(new Libro(libroId)) == null) return false;
		if (!this.usuarios.contains(usuarioId)) return false;
		if (!this.prestamos.contains(new Par<>(libros.find(new Libro(libroId)), usuarioId))) return false;
		if (!this.prestamos.find(new Par<>(this.libros.find(new Libro(libroId)), usuarioId)).getValue().equals(usuarioId)) return false;
		this.prestamos.remove(new Par<>(libros.find(new Libro(libroId)), usuarioId)); return true;
	}

	public ArrayList<String> getUsuariosLibro(String libroId) {		
		ArrayList<String> result = new ArrayList<>();
		if (this.libros.find(new Libro(libroId)) == null) return null;
		for (Par <String, ArrayList <Libro>> prestamo : this.historicoPrestamos) {
			if (prestamo.getValue().contains(this.libros.find(new Libro(libroId)))) result.add(prestamo.getKey());
		}
		return result;
	}
	
	public ArrayList<String> getLibrosPrestados(String usuarioId){
		ArrayList<String> result = new ArrayList<>();
		if (this.usuarios.find(usuarioId) == null) return null;
		for (Libro libro : this.historicoPrestamos.find(new Par<>(usuarioId, null)).getValue()) {
			result.add(libro.getLibroId());
		}
		return result;
	}
		
	public ArrayList<String> getLibrosPrestados(){
		ArrayList<String> result = new ArrayList<>();
		for (Par <Libro, String> prestamo : this.prestamos) {
			result.add(prestamo.getKey().getLibroId());
		}
		return result;
	}

	@Override
	public String toString() {
		return this.bibliotecaId + " ("+ libros.size() + " libros y " + usuarios.size() + " usuarios)";
	}
}