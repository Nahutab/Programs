/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for a mailbox object
 */
public class Mailbox implements Serializable {

    private Folder inbox = new Folder("Inbox");
    private Folder trash = new Folder("Trash");
    private ArrayList<Folder> folders = new ArrayList<Folder>();
    public static Mailbox mailbox;
    private Folder currentFolder;

    /**
     * empty constructor of a mailbox object.
     */
    public Mailbox() {
        folders.add(inbox);
        folders.add(trash);
    }

    /**
     * Adds a folder to the mailbox
     * @param folder folder to be added
     * @throws FolderAlreadyExistsException thrown when a folder already exists that is the same as the
     * one being added
     */
    public void addFolder(Folder folder) throws FolderAlreadyExistsException {

        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equals(folder.getName())) {
                throw new FolderAlreadyExistsException("Folder already exists.");
            }
        }

        folders.add(folder);

    }

    /**
     * method to delete a folder in the mailbox
     * @param name Name of the folder to be deleted.
     */
    public void deleteFolder(String name) {

        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equals(name)) {
                folders.remove(i);
                break;
            }
        }

    }

    /**
     * method to create an email with user input.
     */
    public void composeEmail() {
        Scanner stdin = new Scanner(System.in);

        stdin.useDelimiter("\n");

        System.out.print("Enter recipient (To): ");
        String to = stdin.next();
        System.out.print("Enter carbon copy recipients (CC): ");
        String cc = stdin.next();
        System.out.print("Enter blind carbon copy recipients (BCC): ");
        String bcc = stdin.next();
        System.out.print("Enter the subject line: ");
        String subject = stdin.next();
        System.out.print("Enter body: ");
        String body = stdin.next();

        Email email = new Email(to, cc, bcc, subject, body);

        inbox.addEmail(email);

    }

    /**
     * method that deletes a certain email
     * @param email Email to be deleted
     */
    public void deleteEmail(Email email) {

        trash.addEmail(email);

        for (int i = 0; i < inbox.getEmails().size(); i++) {
            if (inbox.getEmails().get(i).equals(email)) {
                inbox.removeEmail(i);
            }
        }

    }

    /**
     * method that clears the trash folder
     */
    public void clearTrash() {
        for (int i = 0; i < trash.getEmails().size(); i++) {
            trash.removeEmail(i);
        }
    }

    /**
     * moves an email from one folder to another
     * @param email email to be moved
     * @param target the target folder destination
     */
    public void moveEmail(Email email, Folder target) {

        int doesItExist = 0;

        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equals(target.getName())) {
                doesItExist = 1;
            }
        }

        if (doesItExist == 1) {

            for (int i = 0; i < folders.size(); i++) {
                for (int j = 0; j < folders.get(i).getEmails().size(); j++) {
                    if (folders.get(i).getEmails().get(j).getSubject().equalsIgnoreCase(email.getSubject())) {
                        folders.get(i).removeEmail(j);
                    }
                }
            }

            target.addEmail(email);

        } else {
            inbox.addEmail(email);
        }


    }

    /**
     * getter method to get a specific folder
     * @param name name of the folder
     * @return returns folder object if it exists, null if it doesn't
     */
    public Folder getFolder(String name) {
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equals(name)) {
                return folders.get(i);
            }
        }
        return null;
    }

    /**
     * main method of the program
     * @param args generic args parameter for main method.
     */
    public static void main(String args[]) {
        Scanner stdin = new Scanner(System.in);
        Mailbox mailbox = new Mailbox();


        FileInputStream file = null;
        try {
            file = new FileInputStream("mailbox.obj");
            ObjectInputStream fin = new ObjectInputStream(file);
            mailbox = (Mailbox) fin.readObject();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.print("Previous save not found, starting with an empty mailbox. \n");
        }

        boolean flag = true;
        stdin.useDelimiter("\n");

        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;


        while (flag) {

            System.out.print("Mailbox:\n" +
                    "--------\n");
            for (int i = 0; i < mailbox.folders.size(); i++) {
                System.out.print(mailbox.folders.get(i).getName() + "\n");
            }


            System.out.print("\n");

            printMenu();
            String input = stdin.next();

            if (input.equalsIgnoreCase("A")) {
                System.out.print("Enter the folder name:");
                String folderName = stdin.next();
                Folder folder = new Folder(folderName);
                try {
                    mailbox.addFolder(folder);
                } catch (FolderAlreadyExistsException e) {
                    e.printStackTrace();
                }
            } else if (input.equalsIgnoreCase("R")) {
                System.out.print("Enter the folder name: ");
                String folderName = stdin.next();

                mailbox.deleteFolder(folderName);
            } else if (input.equalsIgnoreCase("C")) {
                mailbox.composeEmail();
            } else if (input.equalsIgnoreCase("F")) {

                System.out.print("Enter the folder name: ");
                String folderName = stdin.next();

                printTableHeading();

                System.out.print(mailbox.getFolder(folderName).toString());

                mailbox.currentFolder = mailbox.getFolder(folderName);

                while (flag4) {

                    System.out.print(mailbox.currentFolder.getName() + "\n");

                    if (mailbox.currentFolder.getEmails().size() == 0) {
                        System.out.print(mailbox.currentFolder.getName() + " is currently empty \n");
                    }

                    printTableHeading();

                    System.out.print(mailbox.currentFolder.toString() + "\n");

                    printSubMenu();

                    String input2 = stdin.next();

                    if (input2.equalsIgnoreCase("M")) {
                        System.out.print("Enter the index of the email to move: ");

                        int index = stdin.nextInt();

                        System.out.print("Folders: ");
                        for (int i = 0; i < mailbox.folders.size(); i++) {
                            System.out.print(mailbox.folders.get(i).getName() + "\n");
                        }

                        System.out.print("Select a folder to move the email to: ");
                        String target = stdin.next();


                        mailbox.moveEmail(mailbox.currentFolder.getEmails().get(index - 1), mailbox.getFolder(target));


                    } else if (input2.equalsIgnoreCase("D")) {
                        System.out.print("Enter email index: ");
                        int index = stdin.nextInt();

                        mailbox.deleteEmail(mailbox.currentFolder.getEmails().get(index - 1));
                        mailbox.currentFolder.removeEmail(index - 1);


                    } else if (input2.equalsIgnoreCase("V")) {
                        System.out.print("Enter email index: ");
                        int index = stdin.nextInt();

                        Email tempEmail = mailbox.currentFolder.getEmails().get(index - 1);


                        System.out.print("To: " + tempEmail.getTo() + "\n" + "CC: " + tempEmail.getCc() + "\n" +
                                "BCC: " + tempEmail.getBcc() + "\n" + "Subject: " + tempEmail.getSubject() + "\n"
                                + tempEmail.getBody() + "\n");

                    } else if (input2.equalsIgnoreCase("SA")) {
                        mailbox.currentFolder.sortBySubjectAscending();
                        System.out.print("Sorted by subject ascending. \n");
                    } else if (input2.equalsIgnoreCase("SD")) {
                        mailbox.currentFolder.sortBySubjectDescending();
                        System.out.print("Sorted by subject descending. \n");

                    } else if (input2.equalsIgnoreCase("DA")) {
                        mailbox.currentFolder.sortByDateAscending();
                        System.out.print("Sorted by date ascending. \n");
                    } else if (input2.equalsIgnoreCase("DD")) {
                        mailbox.currentFolder.sortByDateDescending();
                        System.out.print("Sorted by date descending. \n");
                    } else if (input2.equalsIgnoreCase("R")) {
                        flag4 = false;
                    }
                }


            } else if (input.equalsIgnoreCase("I")) {

                while (flag2) {

                    System.out.print("Inbox: \n");

                    if (mailbox.inbox.getEmails().size() == 0) {
                        System.out.print("The inbox is empty. \n");
                    }

                    printTableHeading();

                    System.out.print(mailbox.inbox.toString() + "\n");

                    printSubMenu();

                    String input2 = stdin.next();

                    if (input2.equalsIgnoreCase("M")) {
                        System.out.print("Enter the index of the email to move: ");

                        int index = stdin.nextInt();

                        System.out.print("Folders: ");
                        for (int i = 0; i < mailbox.folders.size(); i++) {
                            System.out.print(mailbox.folders.get(i).getName() + "\n");
                        }

                        System.out.print("Select a folder to move the email to: ");
                        String target = stdin.next();


                        mailbox.moveEmail(mailbox.inbox.getEmails().get(index - 1), mailbox.getFolder(target));


                    } else if (input2.equalsIgnoreCase("D")) {
                        System.out.print("Enter email index: ");
                        int index = stdin.nextInt();

                        mailbox.deleteEmail(mailbox.inbox.getEmails().get(index - 1));

                    } else if (input2.equalsIgnoreCase("V")) {
                        System.out.print("Enter email index: ");
                        int index = stdin.nextInt();

                        Email tempEmail = mailbox.currentFolder.getEmails().get(index - 1);


                        System.out.print("To: " + tempEmail.getTo() + "\n" + "CC: " + tempEmail.getCc() + "\n" +
                                "BCC: " + tempEmail.getBcc() + "\n" + "Subject: " + tempEmail.getSubject() + "\n"
                                + tempEmail.getBody() + "\n");

                    } else if (input2.equalsIgnoreCase("SA")) {
                        mailbox.inbox.sortBySubjectAscending();
                        System.out.print("Sorted by subject ascending. \n");
                    } else if (input2.equalsIgnoreCase("SD")) {
                        mailbox.inbox.sortBySubjectDescending();
                        System.out.print("Sorted by subject descending. \n");

                    } else if (input2.equalsIgnoreCase("DA")) {
                        mailbox.inbox.sortByDateAscending();
                        System.out.print("Sorted by date ascending. \n");
                    } else if (input2.equalsIgnoreCase("DD")) {
                        mailbox.inbox.sortByDateDescending();
                        System.out.print("Sorted by date descending. \n");
                    } else if (input2.equalsIgnoreCase("R")) {
                        flag2 = false;
                    }
                }

            } else if (input.equalsIgnoreCase("T")) {
                while (flag3) {

                    System.out.print("Trash: ");

                    printTableHeading();

                    System.out.print(mailbox.trash.toString() + "\n");

                    printSubMenuTrash();

                    String input2 = stdin.next();

                    if (input2.equalsIgnoreCase("M")) {
                        System.out.print("Enter the index of the email to move: ");

                        int index = stdin.nextInt();

                        System.out.print("Folders: \n");
                        for (int i = 0; i < mailbox.folders.size(); i++) {
                            System.out.print(mailbox.folders.get(i).getName() + "\n");
                        }

                        System.out.print("Select a folder to move the email to: ");
                        String target = stdin.next();


                        mailbox.moveEmail(mailbox.trash.getEmails().get(index - 1), mailbox.getFolder(target));

                    } else if (input2.equalsIgnoreCase("D")) {
                        System.out.print("Enter email index: ");
                        int index = stdin.nextInt();

                        mailbox.deleteEmail(mailbox.trash.getEmails().get(index - 1));

                    } else if (input2.equalsIgnoreCase("V")) {
                        System.out.print("Enter email index: ");
                        int index = stdin.nextInt();

                        Email tempEmail = mailbox.trash.getEmails().get(index - 1);


                        System.out.print("To: " + tempEmail.getTo() + "\n" + "CC: " + tempEmail.getCc() + "\n" +
                                "BCC: " + tempEmail.getBcc() + "\n" + "Subject: " + tempEmail.getSubject() + "\n"
                                + tempEmail.getBody() + "\n");

                    } else if (input2.equalsIgnoreCase("SA")) {
                        mailbox.trash.sortBySubjectAscending();
                        System.out.print("Sorted by subject ascending. \n");
                    } else if (input2.equalsIgnoreCase("SD")) {
                        mailbox.trash.sortBySubjectDescending();
                        System.out.print("Sorted by subject descending. \n");

                    } else if (input2.equalsIgnoreCase("DA")) {
                        mailbox.trash.sortByDateAscending();
                        System.out.print("Sorted by date ascending. \n");
                    } else if (input2.equalsIgnoreCase("DD")) {
                        mailbox.trash.sortByDateDescending();
                        System.out.print("Sorted by date descending. \n");
                    } else if (input2.equalsIgnoreCase("R")) {
                        flag3 = false;
                    }
                }
            } else if (input.equalsIgnoreCase("E")) {
                mailbox.clearTrash();
                System.out.print("Trash has been cleared. \n");
            } else if (input.equalsIgnoreCase("Q")) {

                try {
                    FileOutputStream file2 = new FileOutputStream("mailbox.obj");
                    ObjectOutputStream fout = new ObjectOutputStream(file2);
                    fout.writeObject(mailbox);
                    fout.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                flag = false;

            }

        }


    }

    /**
     * helper method to print the menu
     */
    public static void printMenu() {
        System.out.print("A – Add folder\n" +
                "\n" +
                "R – Remove folder\n" +
                "\n" +
                "C – Compose email\n" +
                "\n" +
                "F – Open folder\n" +
                "\n" +
                "I – Open Inbox\n" +
                "\n" +
                "T – Open Trash\n" +
                "\n" +
                "E – Empty Trash\n" +
                "\n" +
                "Q – Quit\n" +
                "\n" +
                " \n" +
                "\n" +
                "Enter a user option:");
    }

    /**
     * helper method to print the table titles
     */
    public static void printTableHeading() {

        String output = new String("");
        output += String.format("%4s %4s %20s", "Index", "Time", "Subject");

        output += "\n";

        System.out.print(output);


    }

    /**
     * helper method to print submenu
     */
    public static void printSubMenu() {
        System.out.print("M – Move email\n" +
                "\n" +
                "D – Delete email\n" +
                "\n" +
                "V – View email contents\n" +
                "\n" +
                "SA – Sort by subject line in ascending order\n" +
                "\n" +
                "SD – Sort by subject line in descending order\n" +
                "\n" +
                "DA – Sort by date in ascending order\n" +
                "\n" +
                "DD – Sort by date in descending order\n" +
                "\n" +
                "R – Return to mailbox\n" +
                "\n" +
                " \n" +
                "\n" +
                "Enter a user option:");
    }

    /**
     * helper method to print the sub menu for the trash
     */
    public static void printSubMenuTrash() {
        System.out.print("M – Move email\n" +
                "\n" +
                "D – Clean Trash\n" +
                "\n" +
                "V – View email contents\n" +
                "\n" +
                "SA – Sort by subject line in ascending order\n" +
                "\n" +
                "SD – Sort by subject line in descending order\n" +
                "\n" +
                "DA – Sort by date in ascending order\n" +
                "\n" +
                "DD – Sort by date in descending order\n" +
                "\n" +
                "R – Return to mailbox\n" +
                "\n" +
                " \n" +
                "\n" +
                "Enter a user option:");
    }


}
