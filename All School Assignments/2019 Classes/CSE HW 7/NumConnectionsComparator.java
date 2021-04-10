/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no.3
 */

import java.util.Comparator;

public class NumConnectionsComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        Patient p1 = (Patient) o1;
        Patient p2 = (Patient) o2;
        return Integer.compare(p1.getNumConnections(), p2.getNumConnections());
    }
}
