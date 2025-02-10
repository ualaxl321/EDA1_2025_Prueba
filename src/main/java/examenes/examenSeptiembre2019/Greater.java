package examenes.examenSeptiembre2019;

import java.util.Comparator;

public class Greater<T> implements Comparator<Integer> {

    @Override
    public int compare(Integer num1, Integer num2) {
        // Ordenar en orden descendente (de mayor a menor)
        return ((java.lang.Integer) num2).compareTo((java.lang.Integer) num1);
    }
}