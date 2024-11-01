package prueba_de_nivel_en_Java;

//Primer test: Mal
//Segundo test: Bien
//Tercer test:

public class Prueba implements Comparable<Prueba> {
    private String c1;
    private String c2;

    public Prueba(String c1, String c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public int compareTo(Prueba other) {
        int cmp = this.c1.compareTo(other.c1);
        return cmp != 0 ? cmp : other.c2.compareTo(this.c2);
    }

    public static void main(String[] args) {
        Prueba prueba01 = new Prueba("az", "az");
        Prueba prueba02 = new Prueba("az", "za");
        Prueba prueba03 = new Prueba("az", "za");
        
        // Comparamos c1 ("az") con c1 ("az"), son iguales (cmp == 0), 
        // entonces comparamos c2 ("az") con c2 ("za"), como "az" < "za", 
        // devuelve un valor negativo (< 0), por lo que la expresión es true.
        System.out.println(prueba01.compareTo(prueba02) < 0);

        // Comparamos c1 ("az") con c1 ("az"), son iguales (cmp == 0),
        // entonces comparamos c2 ("az") con c2 ("za"), como "az" < "za", 
        // devuelve un valor negativo (< 0), por lo que la expresión es false.
        System.out.println(prueba01.compareTo(prueba03) > 0);

        // Comparamos c1 ("az") con c1 ("az"), son iguales (cmp == 0), 
        // entonces comparamos c2 ("za") con c2 ("za"), son iguales, 
        // por lo que devuelve 0 (== 0), y la expresión es true.
        System.out.println(prueba02.compareTo(prueba03) == 0);
    }
}

