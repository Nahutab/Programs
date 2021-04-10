/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class for a Folder object
 */
public class Folder implements Serializable {

    /**
     * Constructor for a folder object
     *
     * @param name String name of the folder
     */
    public Folder(String name) {
        this.name = name;
        setCurrentSortingMethod("Subject Ascending");
    }

    private ArrayList<Email> emails = new ArrayList<Email>();

    private String name;

    private String currentSortingMethod;

    /**
     * Sets the current sorting method.
     *
     * @param currentSortingMethod String name of the current sorting method.
     */
    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     * setter method to set the emails.
     *
     * @param emails email arraylist
     */
    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    /**
     * sets the name of the folder
     *
     * @param name String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method to get the arraylist of emails
     *
     * @return returns arraylist of emails
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * getter method to get the String current sorting method
     * @return returns string of the current sorting method
     */
    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * getter method to get the name of the folder.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * method to add the email to the folder
     * @param email Email object being added
     */
    public void addEmail(Email email) {
        if (currentSortingMethod.equals("Subject Ascending")) {

            if (emails.size() == 0) {
                emails.add(email);
                return;
            }


            for (int i = 0; i < emails.size(); i++) {
                if (emails.get(i).compareTo(email) == 0) {
                    emails.add(i, email);
                } else if (emails.get(i).compareTo(email) > 0) {

                } else if (emails.get(i).compareTo(email) < 0 && emails.get(i + 1).compareTo(email) > 0) {
                    emails.add(i + 1, email);
                }
            }

        } else if (currentSortingMethod.equals("Subject Descending")) {

            if (emails.size() == 0) {
                emails.add(email);
                return;
            }


            for (int i = 0; i < emails.size(); i++) {
                if (emails.get(i).compareTo(email) == 0) {
                    emails.add(i, email);
                } else if (emails.get(i).compareTo(email) < 0) {

                } else if (emails.get(i).compareTo(email) > 0 && emails.get(i + 1).compareTo(email) < 0) {
                    emails.add(i + 1, email);
                }
            }
        } else if (currentSortingMethod.equals("Date Ascending")) {

            if (emails.size() == 0) {
                emails.add(email);
                return;
            }

            for (int i = 0; i < emails.size(); i++) {
                if (emails.get(i).getTimestamp().getTime().toString().compareTo
                        (email.getTimestamp().getTime().toString()) == 0) {
                    emails.add(i, email);
                } else if (emails.get(i).getTimestamp().getTime().toString().compareTo
                        (email.getTimestamp().getTime().toString()) > 0) {

                } else if (emails.get(i).getTimestamp().getTime().toString().compareTo
                        (email.getTimestamp().getTime().toString()) < 0
                        && emails.get(i + 1).getTimestamp().getTime().toString().compareTo
                        (email.getTimestamp().getTime().toString()) > 0) {
                    emails.add(i + 1, email);
                }
            }
        } else if (currentSortingMethod.equals("Date Descending")) {

            if (emails.size() == 0) {
                emails.add(email);
                return;
            }


            for (int i = 0; i < emails.size(); i++) {
                if (emails.get(i).getTimestamp().getTime().toString().compareTo
                        (email.getTimestamp().getTime().toString()) == 0) {
                    emails.add(i, email);
                } else if (emails.get(i).getTimestamp().getTime().toString().compareTo
                        (email.getTimestamp().getTime().toString()) < 0) {

                } else if (emails.get(i).getTimestamp().getTime().toString().compareTo
                        (email.getTimestamp().getTime().toString()) > 0
                        && emails.get(i + 1).getTimestamp().getTime().toString().compareTo
                        (email.getTimestamp().getTime().toString()) < 0) {
                    emails.add(i + 1, email);
                }
            }

        }
    }

    /**
     * method to remove the email at a certain index
     * @param index index of the email to be removed
     * @return returns the email object
     */
    public Email removeEmail(int index) {
        return emails.remove(index);
    }

    /**
     * sorting method to sort by Subject Ascending
     */
    public void sortBySubjectAscending() {
        Collections.sort(emails);
        setCurrentSortingMethod("Subject Ascending");
    }

    /**
     * Sorting method to sort by subject descending
     */
    public void sortBySubjectDescending() {
        Collections.sort(emails);
        Collections.reverse(emails);
        setCurrentSortingMethod("Subject Descending");
    }

    /**
     * sorting method to sort by date ascending
     */
    public void sortByDateAscending() {
        Collections.sort(emails, new Comparator<Email>() {
            @Override
            public int compare(Email o1, Email o2) {
                Email e1 = (Email) o1;
                Email e2 = (Email) o2;

                if (e1.getTimestamp().getTime().toString().equals(e2.getTimestamp().getTime().toString())) {
                    return 0;
                } else if (e1.getTimestamp().getTime().toString().compareTo(e2.getTimestamp().getTime().toString())
                        > 0) {
                    return 1;
                } else return -1;
            }
        });
        setCurrentSortingMethod("Date Ascending");
    }

    /**
     * sorting method to sort by date descending
     */
    public void sortByDateDescending() {
        sortByDateAscending();
        Collections.reverse(emails);
        setCurrentSortingMethod("Date Descending");
    }

    /**
     * To string method to create a string of the information of the folder.
     * @return returns String information about the folder.
     */
    public String toString() {
        String output = new String("");
        for (int i = 0; i < emails.size(); i++) {
            output += String.format("%4d %4S %20S", i + 1,
                    emails.get(i).getTimestamp().getTime().toString(), emails.get(i).getSubject());
            output += "\n";
        }

        return output;

    }


}
