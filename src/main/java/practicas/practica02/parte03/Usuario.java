package practicas.practica02.parte03;

import java.util.Iterator;

import practicas.auxiliar.AVLTree;
import practicas.auxiliar.Par;


public class Usuario implements Iterable<Par<String,AVLTree<String>>> {
	private final String usuarioId;
	private final MyTreeMap<String, AVLTree<String>> dispositivos;
		
	public Usuario(String userID) {
		this.usuarioId = userID;
		this.dispositivos = new MyTreeMap<>();
	}
	
	public void clear() {
		this.dispositivos.clear();
	}
	
	public void add(String dispositivoId, String...palabras) {
		AVLTree<String> dispositivoCurr = dispositivos.get(dispositivoId);
		if (dispositivoCurr == null) dispositivos.put(dispositivoId, dispositivoCurr= new AVLTree<>());
		for (String palabra : palabras) {
			dispositivoCurr.add(palabra);
		}
	}

	@Override
	public String toString() {
		return usuarioId + "=<" + (dispositivos.size() == 0 ? "0 dispositivos>" : dispositivos.size() == 1 ? "1 dispositivo>" : dispositivos.size() + " dispositivos>");
	}

	@Override
	public Iterator<Par<String,AVLTree<String>>> iterator() {
		return dispositivos.pairSet().iterator();
	}
}
