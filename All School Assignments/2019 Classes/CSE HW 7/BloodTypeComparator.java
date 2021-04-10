/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no.3
 */


import java.util.Comparator;

public class BloodTypeComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        Patient p1 = (Patient) o1;
        Patient p2 = (Patient) o2;

        String bt1 = p1.getBloodType().getBloodType();
        String bt2 = p2.getBloodType().getBloodType();

        return bt1.compareTo(bt2);
    }
}
