package examenes.examenEnero2025.practica3;

public class MyTree<T extends Comparable<T>> {

	private static class Node<T extends Comparable<T>> {
		private T data;
		private Node<T> left, right;
		private Node(T data ) {
			this.data = data;
			this.left = this.right = null;
		}
	}
	
	private Node<T> root;

	public MyTree(Node<T> root) {
		this.root = null;
	}
	
	public boolean add (T data) {
		if (data == null) return false;
	    if (this.root == null) {
	        this.root = new Node<>(data);
	        return true;
	    }
	    return add(data, this.root) != null;
	}
	
	private Node<T> add (T data, Node<T> current) {
		if (current == null) return new Node<>(data);

        int cmp = data.compareTo(current.data);
        if (cmp < 0) {
            current.left = add(data, current.left);
        } else if (cmp > 0) {
            current.right = add(data, current.right);
        }
        return current;
	}
	
	private String toStringIND (Node<T> current) {
		if (current == null) return "";

        String result = "";
        result += toStringIND(current.left);
        result += current.data + " ";
        result += toStringIND(current.right);
        return result;
	}

	@Override
	public String toString() {
		return this.root == null ? "" : toStringIND(this.root);
	}
	
	public int getLevel(T data) {
		return getLevel(data, this.root, 0);
	}
	
	// MÉTODO A IMPLEMENTAR
	private int getLevel(T data, Node<T> current, int currentLevel) {
		if (current == null) return -1;

        int cmp = data.compareTo(current.data);
        if (cmp == 0) return currentLevel;
        if (cmp < 0) return getLevel(data, current.left, currentLevel + 1); 
        return getLevel(data, current.right, currentLevel + 1);
	}
	
    public static void main(String[] args) {
        MyTree<Integer> arbol = new MyTree<Integer>(null);

        arbol.add(10);
        arbol.add(6);
        arbol.add(15);
        arbol.add(3);
        arbol.add(20);
        arbol.add(17);
        arbol.add(23);

        System.out.println("Árbol (in-order): " + arbol);

        System.out.println("Nivel de 10: " + arbol.getLevel(10));
        System.out.println("Nivel de 15: " + arbol.getLevel(15));
        System.out.println("Nivel de 23: " + arbol.getLevel(23));
        System.out.println("Nivel de 3: " + arbol.getLevel(3));
        System.out.println("Nivel de 44: " + arbol.getLevel(44));
        
        System.out.println("Comprobación del seguimiento exhaustivo:");
        System.out.println("Nivel de 20: " + arbol.getLevel(20));
        System.out.println("Nivel de 1: " + arbol.getLevel(1));
	}
}