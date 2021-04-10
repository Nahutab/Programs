/**
 * Name:Batuhan Aykac
 * SBU ID: 112813167
 * Recitation: No. 3
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class is to create a hashtable of Item objects.
 */
public class HashedGrocery {
    private int businessDay = 1;
    private Hashtable<String, Item> hashTable = new Hashtable<String, Item>();

    /**
     * This is a empty constructor to create a HashedGrocery object, hashtable.
     */
    public HashedGrocery() {

    }

    /**
     * This method is to add an item into the hashtable/
     * @param item the input of type Item that we are trying to add into the Hashtable
     * @throws ItemCodeAlreadyExistsException thrown when an Item with the same code exists.
     */
    public void addItem(Item item) throws ItemCodeAlreadyExistsException {
        if (hashTable.containsKey(item.getItemCode())) {
            throw new ItemCodeAlreadyExistsException("An item with the same item code already exists.");
        } else {
            hashTable.put(item.getItemCode(), item);
        }

    }

    /**
     * Method to update the amount of the item in the store.
     * @param item Input of type item
     * @param adjustByQty int type that we are adjusting by
     */
    public void updateItem(Item item, int adjustByQty) {
        item.setQtyInStore(adjustByQty);
    }

    /**
     * method that adds all the items from a text file into the hashtable
     * @param filename input type String that determines what file to be read
     */
    public void addItemCatalog(String filename) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        JSONParser parser = new JSONParser();
        JSONArray objs = null;
        try {
            objs = (JSONArray) parser.parse(isr);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < objs.size(); i++) {
            Item tempItem = new Item();
            JSONObject obj = (JSONObject) objs.get(i);
            tempItem.setItemCode((String) obj.get("itemCode"));
            tempItem.setName((String) obj.get("itemName"));
            tempItem.setAverageSalesPerDay(Integer.parseInt((String) obj.get("avgSales")));
            tempItem.setQtyInStore(Integer.parseInt((String) obj.get("qtyInStore")));
            tempItem.setPrice(Double.parseDouble((String) obj.get("price")));
            tempItem.setOnOrder(Integer.parseInt((String) obj.get("amtOnOrder")));
            try {
                this.addItem(tempItem);
                System.out.println(tempItem.getItemCode() + ":  " + tempItem.getName() + " is added to inventory.");
            } catch (ItemCodeAlreadyExistsException e) {
                System.out.println(tempItem.getItemCode() + ":  " + tempItem.getName() + " was NOT successfully added." +
                        "Item code already exists.");
            }

        }
    }

    /**
     * Method that processes the items sold that day
     * @param filename input type String that determines what file to be read
     */
    public void processSales(String filename) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        JSONParser parser = new JSONParser();
        JSONArray objs = null;
        try {
            objs = (JSONArray) parser.parse(isr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < objs.size(); i++) {
            JSONObject obj = (JSONObject) objs.get(i);
            Item tempItem = new Item();
            if (hashTable.containsKey((String) obj.get("itemCode"))) {
                tempItem = hashTable.get((String) obj.get("itemCode"));
            }

            if (tempItem.getQtyInStore() > Integer.parseInt((String) obj.get("qtySold"))) {
                tempItem.setQtyInStore(tempItem.getQtyInStore() - Integer.parseInt((String) obj.get("qtySold")));

                if ((3 * tempItem.getAverageSalesPerDay()) > tempItem.getQtyInStore()) {
                    tempItem.setOnOrder(2 * tempItem.getAverageSalesPerDay());
                    tempItem.setArrivalDay(businessDay + 3);
                    System.out.println(tempItem.getItemCode() + ":   " + tempItem.getName() + ":   " + (2 * tempItem.getAverageSalesPerDay() + " units have been ordered. Also, "
                            + Integer.parseInt((String) obj.get("qtySold")) + " units have been sold"));
                } else {
                    System.out.println(tempItem.getItemCode() + ":   " + tempItem.getName() + ":   " + Integer.parseInt((String) obj.get("qtySold")) + " units have been sold");
                }


            } else {
                System.out.println(Integer.parseInt((String)obj.get("itemCode"))+ ":   Not enough in stock to sell. Not updated.");
            }


        }
    }

    /**
     * Method that increments business day and prints out what units have arrived or if none have arrived
     */
    public void nextBusinessDay() {
        businessDay++;
        boolean flag = true;
        for (String itemCode : hashTable.keySet()) {
            if (hashTable.get(itemCode).getArrivalDay() == businessDay) {
                hashTable.get(itemCode).setQtyInStore(hashTable.get(itemCode).getQtyInStore() +
                        hashTable.get(itemCode).getOnOrder());

                System.out.println(hashTable.get(itemCode).getItemCode() + ": " +
                        hashTable.get(itemCode).getOnOrder() + " units of " +
                        hashTable.get(itemCode).getName() + " have arrived.");
                hashTable.get(itemCode).setArrivalDay(0);
                hashTable.get(itemCode).setOnOrder(0);
                flag = false;
            }


        }
        if (flag) {
            System.out.println("No orders have arrived.");
        }
    }

    /**
     * This is a method that prints all items in a neatly formatted table
     * @return String type of the information found in the table.
     */
    public String toString() {
        String output2 = new String("");

        String output1 = String.format("%-10s%-25s%-10s%-10s%-10s%-10s%-10s"
                , "Item code", "Name", "Qty", "AvgSales", "Price", "OnOrder", "ArrOnBusDay") + "\n" + "" +
                "------------------------------------------------------------------------------------------\n";
        String output3 = new String("");

        output3 += output1;

        for (String itemCode : hashTable.keySet()) {
            Item tempItem = hashTable.get(itemCode);

            output3 += tempItem.toString();
            output3 += "\n";
        }
        return output3;
    }

    /**
     * This is a getter method to get the business day.
     * @return int type variable
     */
    public int getBusinessDay() {
        return businessDay;
    }

}
