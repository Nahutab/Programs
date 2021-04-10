/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no.3
 */


import java.util.Comparator;

public class OrganComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        Patient p1 = (Patient) o1;
        Patient p2 = (Patient) o2;

        String org1 = p1.getOrgan();
        String org2 = p2.getOrgan();

        return org1.compareTo(org2);
    }
}
