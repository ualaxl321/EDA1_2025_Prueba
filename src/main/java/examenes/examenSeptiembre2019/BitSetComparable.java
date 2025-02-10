package examenes.examenSeptiembre2019;

import java.util.BitSet;

public class BitSetComparable implements Comparable<BitSetComparable>{
	
	private BitSet bitSet;
	
	public BitSetComparable(String bitSet) {
		if(bitSet==null) System.out.println(Parameters.NULL_PARAMETERS);
		this.bitSet = new BitSet(Parameters.NUM_FEATURES);
		for (int i=0; i<Parameters.NUM_FEATURES; i++) {
			if((bitSet.length()-1)>=i) {
				this.bitSet.set(i, (bitSet.charAt(i)=="1".charAt(0) ? true : false));
				continue;
			}
			this.bitSet.set(i, false);
		}
	}
	
	public int toDecimal() {
		return (int) this.bitSet.toLongArray()[0];
	}
	
	public int intersects(BitSetComparable o) {
		int cont = 0;
		for (int i=0; i<Parameters.NUM_FEATURES; i++) {
			cont += ((this.bitSet.get(i)==true) && (o.bitSet.get(i)==true))?1:0;
		}
		return cont;
	}
	
	@Override
	public String toString() {
		String resultado = "";
		//...Haced uso de Parameters.NUM_FEATURES
		for (int i=0; i<Parameters.NUM_FEATURES; i++) {
			resultado += this.bitSet.get(i)==true?"1":"0";
		}
		return resultado + " (" + String.valueOf(toDecimal()) + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		return this.compareTo((BitSetComparable)o) == 0;
	}
	
	@Override
	public int compareTo(BitSetComparable o) {
		//Orden según el valor decimal (less)
		return Integer.compare(this.toDecimal(), o.toDecimal());
	}
}
