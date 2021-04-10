/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

/**
 * This is the class that holds the information about a certain item.
 */
public class ItemInfo {

    private String productName;
    private double price;
    private String rfidTagNumber;
    private String originalLocation;
    private String currentLocation;

    /**
     * This is an empty constructor to create an empty information object.
     */
    public ItemInfo() {

    }

    /**
     * This is a constructor to create an information object with information inputs.
     *
     * @param name         name of the item as a String.
     * @param rfidTag      rfid tag of the item as a String.
     * @param price        price of the item as a double.
     * @param initPosition the original position of the item as a String.
     * @throws InvalidStringException Thrown when the rfid or original position doesn't fit the format needed.
     * @throws NegativePriceException Thrown when price is negative.
     */
    public ItemInfo(String name, String rfidTag, double price, String initPosition) throws InvalidStringException,
            NegativePriceException {

        if (rfidTag.length() != 9 || !rfidTag.matches("-?[0-9a-fA-F]+")) {
            throw new InvalidStringException("Invalid rfid input");
        }

        if (initPosition.length() != 6 || initPosition.charAt(0) != 's') {
            throw new InvalidStringException("Invalid original position.");
        }

        if (price < 0) {
            throw new NegativePriceException("Negative price");
        }


        this.productName = name;
        this.rfidTagNumber = rfidTag;
        this.price = price;
        this.originalLocation = initPosition;
        this.currentLocation = initPosition;
    }

    /**
     * Setter method to set the price of the info object.
     * @param price price of the object as a double.
     * @throws NegativePriceException Thrown when price is negative.
     */
    public void setPrice(double price) throws NegativePriceException {

        if (price <= 0) {
            throw new NegativePriceException("Invalid price");
        } else {
            this.price = price;
        }
    }

    /**
     * Setter method to set the name of the object.
     * @param productName String type for name.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Setter method to set the original location of the object.
     * @param originalLocation String type for original location.
     */
    public void setOriginalLocation(String originalLocation) {
        this.originalLocation = originalLocation;
    }

    /**
     * Setter method to set the current location of the object.
     * @param currentLocation String type for current location.
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * getter method to get the price.
     * @return returns price which is a double value.
     */
    public double getPrice() {
        return price;
    }

    /**
     * getter method to get the name of the product.
     * @return returns String type for the name of the product.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * getter method to get the current location .
     * @return returns String type for current location of the product.
     */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /**
     * getter method to get the original location
     * @return returns String type for original location of the product.
     */
    public String getOriginalLocation() {
        return originalLocation;
    }

    /**
     * getter method to get the rfid tag number
     * @return returns String type of the rfid tag
     */
    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    /**
     * This is a to string method to convert the information into a printable and readable string
     * @return returns the information as a String.
     */
    public String toString() {
        String output = new String("");

        output += String.format("%-21s%-26s%-19s%-13s%9s", getProductName(), getRfidTagNumber(), getOriginalLocation(),
                getCurrentLocation(), getPrice());


        return output;
    }
}
