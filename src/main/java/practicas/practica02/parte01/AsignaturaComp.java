package practicas.practica02.parte01;

import java.util.Comparator;

public class AsignaturaComp implements Comparator<AsignaturaNotas> {
    @Override
    public int compare(AsignaturaNotas o1, AsignaturaNotas o2) {
    	int cmp = Integer.compare(o1.getCuatrimestre(), o2.getCuatrimestre());
        return cmp;
    }
}
