/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no.3
 */

public class Patient implements Comparable {
    private String name;
    private String organ;
    private int age;
    private BloodType bloodType;
    private int ID;
    private boolean isDonor;
    private int numConnections;

    public Patient() {
    }

    public Patient(int ID, String name, int age, String organ, BloodType bloodType) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.organ = organ;
        this.bloodType = bloodType;
    }

    public Patient(String name, int age, String organ, BloodType bloodType){
        this.name = name;
        this.age = age;
        this.organ = organ;
        this.bloodType = bloodType;
    }

    public int compareTo(Object o) {
        Patient otherPatient = (Patient) o;
        return Integer.compare(this.ID, otherPatient.ID);
    }

    public int getNumConnections() {
        return numConnections;
    }

    public boolean isDonor() {
        return isDonor;
    }


    public String getName() {
        return name;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public int getAge() {
        return age;
    }

    public int getID() {
        return ID;
    }

    public String getOrgan() {
        return organ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public void setDonor(boolean donor) {
        isDonor = donor;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public void setNumConnections(int numConnections) {
        this.numConnections = numConnections;
    }
}
