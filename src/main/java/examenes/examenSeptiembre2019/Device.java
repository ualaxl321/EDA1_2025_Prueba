package examenes.examenSeptiembre2019;

public class Device implements Comparable<Device>{
	private String nombre;
	private BitSetComparable code;
	
	public Device(String nombre, String code) throws Exception{
		this.nombre = nombre;
		if(code==null) throw new Exception("No se permiten parametros nulos");
		this.code = new BitSetComparable(code);
	}
	
	public int intersects(Device o){
		return this.code.intersects(o.code);
	}
	
	@Override
	public String toString(){
		return this.nombre + " <" + code.toDecimal() + ">";
	}
	
	@Override
	public boolean equals(Object o){
		return this.compareTo((Device)o) == 0;
	}
	
	@Override
	public int compareTo(Device o) {
	//Orden: nombre (less) y, en segundo lugar, code (orden natural de BitSetComparable)
		int cmp = this.nombre.compareToIgnoreCase(o.nombre);//...
		return cmp != 0 ? cmp : this.code.compareTo(o.code);//...
	}
}
