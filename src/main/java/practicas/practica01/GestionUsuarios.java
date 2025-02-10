package practicas.practica01;

import java.util.ArrayList;

public class GestionUsuarios {
	private ArrayList<Usuario> usuarios;
	 
	public GestionUsuarios() {
		this.usuarios = new ArrayList<>();
	}
	
	public void addDispositivos(Usuario usuario, Dispositivo... dispositivos) {
		int pos = usuarios.indexOf(usuario);
	    if (pos == -1) {
	        usuarios.add(usuario); 
	        pos = usuarios.size() - 1;
	    }
	    usuarios.get(pos).addDispositivos(dispositivos);
	}
	
	public String getGradosSimilitud(Usuario usuario) {
		String result = usuario.getNombre() + " -> ";
		for (Usuario otroUsuario : usuarios) {
			if (!otroUsuario.equals(usuario)) {
				int grado = usuario.getInterseccion(otroUsuario).cardinality();
				result += otroUsuario.getNombre() + " <" + grado + "> ";
			}
		}
		return result;
	}

	public boolean enviarMensaje(String nombreUsuario, String nombreDispositivo, String mensaje) {
		for (Usuario usuario : usuarios) {
	        if (usuario.getNombre().equals(nombreUsuario)) return usuario.enviarMensaje(nombreDispositivo, mensaje);
	    }
	    return false;
	}
	
	public ArrayList<String> getPalabrasUsuario(String nombreUsuario) {
		ArrayList<String> result = new ArrayList<>();
		int pos = usuarios.indexOf(new Usuario(nombreUsuario));
		if (pos == -1) return null;
		for (Dispositivo dispositivo : this.usuarios.get(pos)) {
			for (String palabra : dispositivo) {
				if (!result.contains(palabra)) result.add(palabra);
			}
		}
	    return result;
	}
	
	@Override
	public String toString() {
		return this.usuarios.toString();
	}
}