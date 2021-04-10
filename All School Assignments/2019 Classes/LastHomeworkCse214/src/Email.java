/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

import java.io.Serializable;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Class that is an object Email.
 */
public class Email implements Serializable, Comparable {

    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp;

    /**
     * Empty constructor for the email object
     */
    public Email() {

        timestamp = new GregorianCalendar();
    }

    /**
     * Constructor for an Email object that includes all of its string parameters.
     * @param to String for person to be sent to
     * @param cc String carbon copy
     * @param bcc String blind carbon copy
     * @param subject String subject of the email
     * @param body String body text of email
     */
    public Email(String to, String cc, String bcc, String subject, String body) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        timestamp = new GregorianCalendar();
    }

    /**
     * getter method to get the timestamp
     * @return returns time stamp Gregorian Calendar type
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Getter method to get the bcc
     * @return returns string bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * getter method to get the body text
     * @return returns String body text
     */
    public String getBody() {
        return body;
    }

    /**
     * getter method to get the carbon copy recipients
     * @return returns String cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * getter method to get the subject text
     * @return returns String subject text
     */
    public String getSubject() {
        return subject;
    }

    /**
     * getter method to get the recipient to
     * @return returns String to
     */
    public String getTo() {
        return to;
    }

    /**
     * setter method to set bcc
     * @param bcc String bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * setter method to set the body
     * @param body String body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * setter method to set cc
     * @param cc String cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Setter method to set the subject
     * @param subject String subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * setter method to set the timestamp
     * @param timestamp Gregoriancalendar timestamp type
     */
    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * setter method to set To
     * @param to String to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * compare method that compares subject text
     * @param o object compared
     * @return returns 0 if they are lexographically equal, less than 0 if one is less and
     * greater than 0 if the opposite
     */
    public int compareTo(Object o) {
        Email otherEmail = (Email) o;
        if (this.subject.equals(otherEmail.getSubject())) {
            return 0;
        } else if (this.subject.compareTo(otherEmail.getSubject()) > 0) {
            return 1;
        } else return -1;

    }

    /**
     * Like the compareTo method but this one compares dates.
     */
    public class DateComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            Email e1 = (Email) o1;
            Email e2 = (Email) o2;

            if (e1.getTimestamp().getTime().equals(e2.getTimestamp().getTime())) {
                return 0;
            } else if (e1.getTimestamp().getTime().compareTo(e2.getTimestamp().getTime()) > 0) {
                return 1;
            } else return -1;


        }

    }

    /**
     * overridden custom equals method to check if objects are equal
     * @param o object being compared
     * @return returns true if all the Email values are equal.
     */
    @Override
    public boolean equals(Object o) {
        Email email = (Email) o;
        return Objects.equals(to, email.to) &&
                Objects.equals(cc, email.cc) &&
                Objects.equals(bcc, email.bcc) &&
                Objects.equals(subject, email.subject) &&
                Objects.equals(body, email.body) &&
                Objects.equals(timestamp, email.timestamp);
    }


}
