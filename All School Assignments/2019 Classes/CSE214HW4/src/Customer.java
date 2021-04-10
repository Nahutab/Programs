/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation No. 3
 */

public class Customer {

    private static int totalCustomers = 0;
    private int orderNumber = 0;
    private String food;
    private int priceOfFood = 0;
    private int timeArrived = 0;
    private int timeToServe = 0;

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getPriceOfFood() {
        return priceOfFood;
    }

    public int getTimeArrived() {
        return timeArrived;
    }

    public int getTimeToServe() {
        return timeToServe;
    }

    public static int getTotalCustomers() {
        return totalCustomers;
    }

    public static void setTotalCustomers(int totalCustomers) {
        Customer.totalCustomers = totalCustomers;
    }

    public String getFood() {
        return food;
    }

    public void setTimeToServe(int timeToServe) {
        this.timeToServe = timeToServe;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }


    public Customer() {

    }

    public Customer(String food, int orderNumber){
       this.food = food;
       this.orderNumber = orderNumber;
    }


    public String toString() {

        switch (getFood()) {
            case "S":
                setTimeToServe(30+15);
                break;
            case "CW":
                setTimeToServe(30+15);
                break;
            case "CB":
                setTimeToServe(25+15);
                break;
            case "CT":
                setTimeToServe(25+15);
                break;
            case "GC":
                setTimeToServe(15+15);
                break;
        }



        return new String("[Customer #" + getOrderNumber() + ", " + getFood() + ", " + getTimeToServe() + " min.]");
    }


}
