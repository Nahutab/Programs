/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no.3
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TransplantGraph {
    private ArrayList<Patient> donors;
    private ArrayList<Patient> recipients;
    public static final int MAX_PATIENTS = 100;
    private boolean[][] connections = new boolean[MAX_PATIENTS][MAX_PATIENTS];
    public int counter = 0;

    public TransplantGraph() {
        donors = new ArrayList<Patient>();
        recipients = new ArrayList<Patient>();
    }


    public static TransplantGraph buildFromFiles(String donorFile, String recipientFile) {


        TransplantGraph tpGraph = new TransplantGraph();

        try {
            Scanner reader = new Scanner(new File(donorFile));
            while (reader.hasNext()) {
                String donorText = reader.nextLine();

                String[] patientInfo = donorText.split(",");
                int id = Integer.parseInt(patientInfo[0]); // id
                String name = patientInfo[1].trim(); // name
                int age = Integer.parseInt(patientInfo[2].trim()); // age
                String organ = patientInfo[3].trim(); // organ
                BloodType bloodType = new BloodType();
                bloodType.setBloodType(patientInfo[4].trim()); // blood type
                Patient newPatient = new Patient(id, name, age, organ, bloodType);
                tpGraph.addDonor(newPatient);
            }
            reader.close();

            reader = new Scanner(new File(recipientFile));
            while (reader.hasNext()) {
                String recipientText = reader.nextLine();

                String[] recipientInfo = recipientText.split(",");

                int id = Integer.parseInt(recipientInfo[0]); // id
                String name = recipientInfo[1].trim(); // name
                int age = Integer.parseInt(recipientInfo[2].trim()); // age
                String organ = recipientInfo[3].trim(); // organ
                BloodType bloodType = new BloodType();
                bloodType.setBloodType(recipientInfo[4].trim()); // blood type
                Patient newPatient = new Patient(id, name, age, organ, bloodType);

                tpGraph.addRecipient(newPatient);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return tpGraph;
    }


    public void addRecipient(Patient patient) {
        BloodType bloodType = patient.getBloodType();
        // for a donor...

        int columnNumber = 0;
        if (recipients.size() == MAX_PATIENTS) {
            System.out.println("The array list is full, unsuccessful in adding the recipient.");
        } else {
            recipients.add(patient);
            columnNumber = recipients.size();


            for (int i = 0; i < recipients.size(); i++) {
                if (donors.size() != 0) {
                    if (organAndBloodCompatibility(patient, donors.get(i))) {
                        patient.setNumConnections(patient.getNumConnections() + 1);
                        connections[i][columnNumber] =
                                organAndBloodCompatibility(patient, donors.get(i));
                    } else {
                        connections[i][columnNumber] =
                                organAndBloodCompatibility(patient, donors.get(i));
                    }
                }
            }


            patient.setID(recipients.size() -1);
            System.out.println("The organ recipient with ID " + patient.getID() +
                    " was successfully added to the wait list!");
        }
    }


    public void addDonor(Patient patient) {
        int rowNumber = 0;

        BloodType bloodType = patient.getBloodType();


        if (donors.size() == MAX_PATIENTS) {
            System.out.println("The array list is full, unsuccessful in adding the donor.");
        } else {

            donors.add(patient);
            rowNumber = recipients.size();


            for (int i = 0; i < recipients.size(); i++) {
                if (donors.size() != 0) {
                    if (organAndBloodCompatibility(recipients.get(i), patient)) {
                        patient.setNumConnections(patient.getNumConnections() + 1);
                        connections[rowNumber][i] = organAndBloodCompatibility(recipients.get(i), patient);
                    } else {
                        connections[rowNumber][i] = organAndBloodCompatibility(recipients.get(i), patient);
                    }
                }
            }


            patient.setID(donors.size() - 1);
            System.out.println("The organ donor with ID " + patient.getID() +
                    " was successfully added to the donor list!");

        }

    }

    public void removeRecipient(String name) {

        int columnNumber = 0;


        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).getName().equals(name)) {
                columnNumber = i;
                break;
            }
        }

        for (int i = columnNumber; i < recipients.size() - 1; i++) {
            recipients.set(i, recipients.get(i + 1));
            recipients.get(i).setID(i);
        }

        recipients.remove(recipients.size() - 1);


        for (int i = 0; i < connections[i].length-1; i++) {
            connections[i][columnNumber] = false;
        }

        for (int i = 0; i < connections.length; i++) {
            for (int j = columnNumber; j < connections[i].length; j++) {
                if (j + 1 < recipients.size()) {
                    if (recipients.get(j + 1) != null) {
                        connections[i][j] = connections[i][j + 1];
                    }
                }
            }
        }

        for (int i = 0; i < recipients.size(); i++) {
            recipients.get(i).setID(i);
        }

        System.out.println(name + " was removed from the organ transplant waitlist.");

    }

    public void removeDonor(String name) {

        int rowNumber = 0;


        for (int i = 0; i < donors.size(); i++) {
            if (donors.get(i).getName().equals(name)) {
                rowNumber = i;
                break;
            }
        }

        for (int i = rowNumber; i < donors.size() - 1; i++) {
            donors.set(i, donors.get(i + 1));
            donors.get(i).setID(i);
        }

        donors.remove(donors.size() - 1);


        for (int i = 0; i < connections.length; i++) {
            connections[rowNumber][i] = false;
        }


        for (int i = rowNumber; i < connections.length; i++) {
            for (int j = 0; j < connections[i].length; j++) {
                if (i + 1 < donors.size()) {
                    if (donors.get(i + 1) != null) {
                        connections[i][j] = connections[i + 1][j];
                    }
                }
            }
        }

        System.out.println(name + " was removed from the organ donor list.");

    }


    public void printAllRecipients() {
        System.out.println(recipients.size());
        String output = new String("");
        output += "Index | Recipient Name     | Age | Organ Needed  | Blood Type | Donor ID\n" +
                "========================================================================\n";


        for (int i = 0; i < recipients.size(); i++) {
            output += i + "     " + "|  " + recipients.get(i).getName() + "         |  " + recipients.get(i).getAge() +
                    "    |" + recipients.get(i).getOrgan() + "               |        " +
                    recipients.get(i).getBloodType().getBloodType() + "            | " +
                    getIDListRecipients(recipients.get(i)) + "\n";
        }

        System.out.println(output);
    }


    public void printAllDonors() {
        System.out.println(donors.size());

        String output = new String("");

        output += "Index | Donor Name         | Age | Organ Donated | Blood Type | Recipient IDs\n" +
                "=============================================================================\n";

        for (int i = 0; i < donors.size(); i++) {
            output += "   " + i + "   | " + donors.get(i).getName() + "           |  " + donors.get(i).getAge() + " " +
                    donors.get(i).getOrgan() + "        |      " + donors.get(i).getBloodType().getBloodType() +
                    "      | " + getIDListDonors(donors.get(i)) + "\n";
        }

        System.out.println(output);
    }

    public String getIDListRecipients(Patient patient) {
        String output = new String("");
        for (int i = 0; i < donors.size(); i++) {
            if (organAndBloodCompatibility(patient, donors.get(i))) {
                output += donors.get(i).getID() + ",";
            }
        }
        return output;
    }


    public String getIDListDonors(Patient patient) {
        String output = new String("");
        for (int i = 0; i < recipients.size(); i++) {
            if (organAndBloodCompatibility(recipients.get(i), patient)) {
                output += donors.get(i).getID() + ",";
            }
        }
        return output;
    }


    public boolean organAndBloodCompatibility(Patient recipient, Patient donor) {

        if (BloodType.isCompatible(recipient.getBloodType(), donor.getBloodType()) == true
                && recipient.getOrgan().equalsIgnoreCase(donor.getOrgan())) {
            return true;
        } else return false;


    }

    public void sortRecipientsByID() {
        Collections.sort(recipients);
    }

    public void sortDonorsByID() {
        Collections.sort(donors);

    }

    public void sortRecipientsByNumConnections() {
        Collections.sort(recipients, new NumConnectionsComparator());
        printAllRecipients();
    }

    public void sortDonorsByNumConnections() {
        Collections.sort(donors, new NumConnectionsComparator());
        printAllDonors();
    }

    public void sortRecipientsByBloodType() {
        Collections.sort(recipients, new BloodTypeComparator());
        printAllRecipients();
    }

    public void sortDonorsByBloodType() {
        Collections.sort(donors, new BloodTypeComparator());
        printAllDonors();
    }

    public void sortRecipientsByOrgan() {
        Collections.sort(recipients, new OrganComparator());
        printAllRecipients();
    }

    public void sortDonorsByOrgan() {
        Collections.sort(donors, new OrganComparator());
        printAllDonors();

    }


}
