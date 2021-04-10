/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no.3
 */


import java.util.Scanner;

public class TransplantDriver {

    public static final String DONOR_FILE = "donors.txt";
    public static final String RECIPIENT_FILE = "recipients.txt";


    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);


        boolean flag = false;
        boolean flag2 = false;
        boolean flag3 = false;
        TransplantGraph tpGraph = new TransplantGraph();
        tpGraph = tpGraph.buildFromFiles(DONOR_FILE, RECIPIENT_FILE);


        while (!flag) {
            menu();
            String input = stdin.nextLine();

            if (input.equalsIgnoreCase("LR")) {
                tpGraph.printAllRecipients();
            } else if (input.equalsIgnoreCase("LO")) {
                tpGraph.printAllDonors();
            } else if (input.equalsIgnoreCase("AO")) {
                System.out.println("Please enter the organ donor name: ");
                String name = stdin.nextLine();
                System.out.println("Please enter the organs " + name + "is donating: ");
                String organ = stdin.nextLine();
                System.out.println("Please enter the blood type of " + name + ": ");
                String bloodType = stdin.nextLine();
                System.out.println("Please enter the age of " + name + ": ");
                int age = stdin.nextInt();

                BloodType bloodType1 = new BloodType();
                bloodType1.setBloodType(bloodType);

                Patient pt = new Patient(name, age, organ, bloodType1);

                tpGraph.addDonor(pt);

            } else if (input.equalsIgnoreCase("AR")) {

                System.out.println("Please enter new recipient's name: ");
                String name = stdin.nextLine();
                System.out.println("Please enter the recipient's blood type: ");
                String bloodType = stdin.nextLine();
                System.out.println("Please enter the age of the recipient: ");
                int age = stdin.nextInt();
                stdin.nextLine();
                System.out.println("Please enter the organ needed: ");
                String organ = stdin.nextLine();

                BloodType bloodType1 = new BloodType();
                bloodType1.setBloodType(bloodType);

                Patient pt = new Patient(name, age, organ, bloodType1);

                tpGraph.addRecipient(pt);


            } else if (input.equalsIgnoreCase("RO")) {

                System.out.println("Please enter the name of the organ donor to remove: ");
                String name = stdin.nextLine();

                tpGraph.removeDonor(name);
            } else if (input.equalsIgnoreCase("RR")) {
                System.out.println("Please enter the name of the recipient to remove: ");
                String name = stdin.nextLine();

                tpGraph.removeRecipient(name);
            } else if (input.equalsIgnoreCase("SR")) {

                while (!flag2) {
                    submenu(1);
                    String input2 = stdin.nextLine();
                    if (input2.equalsIgnoreCase("I")) {
                        tpGraph.sortRecipientsByID();
                    } else if (input2.equalsIgnoreCase("N")) {
                        tpGraph.sortRecipientsByNumConnections();
                    } else if (input2.equalsIgnoreCase("B")) {
                        tpGraph.sortRecipientsByBloodType();
                    } else if (input2.equalsIgnoreCase("O")) {
                        tpGraph.sortRecipientsByOrgan();
                    } else if (input2.equalsIgnoreCase("Q")) {
                        tpGraph.sortRecipientsByID();
                        flag2 = true;
                    }
                }
            } else if (input.equalsIgnoreCase("SO")) {
               while(!flag3) {
                   submenu(2);
                   String input2 = stdin.nextLine();

                   if (input2.equalsIgnoreCase("I")) {
                       tpGraph.sortDonorsByID();
                   } else if (input2.equalsIgnoreCase("N")) {
                       tpGraph.sortDonorsByNumConnections();
                   } else if (input2.equalsIgnoreCase("B")) {
                       tpGraph.sortDonorsByBloodType();
                   } else if (input2.equalsIgnoreCase("O")) {
                       tpGraph.sortDonorsByOrgan();
                   } else if (input2.equalsIgnoreCase("Q")) {
                       tpGraph.sortDonorsByID();
                       flag3=true;
                   }
               }
            } else if (input.equalsIgnoreCase("Q")) {
                flag = true;
            }


        }


    }


    public static void menu() {
        System.out.println("Menu: \n \t (LR) - List all recipients \n \t (LO) - List all donors " +
                "\n \t (AO) - Add new donor \n \t (AR) - Add new recipient \n \t (RO) - Remove donor " +
                "\n \t (RR) - Remove Recipient \n \t (SR) - Sort Recipients \n \t (SO) - Sort Donors \n \t " +
                "(Q) - Quit ");
    }

    public static void submenu(int a) {

        if (a == 1) {
            System.out.println("(I) Sort by ID \n(N) Sort by Number of Donors \n(B) Sort by Blood Type" +
                    " \n(O) Sort by Organ Needed \n(Q) Back to Main Menu");
        } else if (a == 2) {
            System.out.println("(I) Sort by ID \n(N) Sort by Number of Recipients \n(B) Sort by Blood Type " +
                    "\n(O) Sort by Organ Donated \n(Q) Back to Main Menu ");
        }

    }


}
