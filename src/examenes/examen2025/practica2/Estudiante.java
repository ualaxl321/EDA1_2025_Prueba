package examenes.examen2025.practica2;

import java.util.Objects;

public class Estudiante implements Comparable<Estudiante>{
    private final String apellido;
    private final String nombre;
    private String telefono;
    private String direccion;

    public Estudiante(String apellido, String nombre) {
        this.apellido = apellido.trim().toLowerCase();
        this.nombre = nombre.trim().toLowerCase();
        this.telefono = "";
        this.direccion = "";
    }
    
    public void setTelefono(String telefono) {
    	this.telefono = telefono.trim().toLowerCase();
    }
    
    public void setDireccion(String direccion) {
    	this.direccion = direccion.trim().toLowerCase();
    }
    public String getNombreApellidos() {
    	return this.nombre + " " + this.apellido;
    }
    
    public void clear() { 
    	this.telefono = "";
    	this.direccion = "";
    }
    @Override
    public String toString() {
    	String result = this.apellido + ", " + this.nombre;
    	if (!this.telefono.isBlank()) result +=  " (" + this.telefono + ")";
    	if (!this.direccion.isBlank()) result += " - " + this.direccion;
    	return result;
    }

    @Override
    public int compareTo(Estudiante other) {
        int cmp = this.apellido.compareTo(other.apellido);
        return cmp != 0 ? cmp : this.nombre.compareTo(other.nombre);
    }
    
    //PRÁCTICA 4 PARTE 2
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null) return false;
    	return this.compareTo((Estudiante) obj) == 0;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(this.apellido, this.nombre);
    }
   
}