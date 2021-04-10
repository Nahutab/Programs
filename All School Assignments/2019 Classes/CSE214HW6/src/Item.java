/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation: No 3.
 */

/**
 * Item class is a class that's an object of type Item.
 */
public class Item {

    private String itemCode;
    private String name;
    private int qtyInStore;
    private int averageSalesPerDay;
    private int onOrder;
    private int arrivalDay;
    private double price;

    /**
     * This is an empty constructor to create an item object.
     */
    public Item() {

    }

    /**
     * This is the to String method to create a string representation of the item.
     * @return String representation of the item.
     */
    public String toString() {
        String output = new String("");
        output += getItemCode() + "  " + getName() + "  " + getQtyInStore() + "  " + getAverageSalesPerDay() + "  " +
                getPrice() + "  " + getOnOrder() + "  " + getArrivalDay();


        return String.format("%-10s%-25s%-10d%-10d%-10.2f%-10d%-10d",getItemCode(),getName(),
                getQtyInStore(), getAverageSalesPerDay(), getPrice(),
               getOnOrder(), getArrivalDay());


    }

    /**
     * This is a getter method for price
     * @return double type price
     */
    public double getPrice() {
        return price;
    }

    /**
     * This is a getter for arrival Day for the order.
     * @return int type variable
     */
    public int getArrivalDay() {
        return arrivalDay;
    }

    /**
     * This is a getter method to get the average sales per day.
     * @return int type variable
     */
    public int getAverageSalesPerDay() {
        return averageSalesPerDay;
    }

    /**
     * This is a getter method to get the amount ordered
     * @return int type variable
     */
    public int getOnOrder() {
        return onOrder;
    }

    /**
     * This is a getter method to get the qty of the item in the store
     * @return int type variable
     */
    public int getQtyInStore() {
        return qtyInStore;
    }

    /**
     * this is a getter method to get the code of the item
     * @return String type variable
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * getter method to get the name of the item
     * @return String type variable
     */
    public String getName() {
        return name;
    }

    /**
     * This is a setter method to set the day of arrival for an order
     * @param arrivalDay int type input
     */
    public void setArrivalDay(int arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    /**
     * This is a setter
     * @param averageSalesPerDay
     */
    public void setAverageSalesPerDay(int averageSalesPerDay) {
        this.averageSalesPerDay = averageSalesPerDay;
    }

    /**
     * This is a setter method to set the item code.
     * @param itemCode String type input
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * This is a setter method to set the name of the item
     * @param name String type input
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is a setter method to set the amount ordered.
     * @param onOrder int type input
     */
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    /**
     * This is a setter method to set the price of the item
     * @param price double type input
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This is a setter method to set the amount of the item in the store
     * @param qtyInStore int type input
     */
    public void setQtyInStore(int qtyInStore) {
        this.qtyInStore = qtyInStore;
    }

}
