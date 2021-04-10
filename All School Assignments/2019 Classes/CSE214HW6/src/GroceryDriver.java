/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation: No. 3
 */


import java.util.Scanner;

/**
 * This is the class that holds the main method.
 */
public class GroceryDriver {
    /**
     * This is the main method to be able to operate the management system.
     * @param args
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        boolean flag = true;

        String menu = new String("\nMenu :\n" +
                " \n" +
                "(L) Load item catalog    \n" +
                "(A) Add items              \n" +
                "(B) Process Sales      \n" +
                "(C) Display all items\n" +
                "(N) Move to next business day  \n" +
                "(Q) Quit    ");


        HashedGrocery hash = new HashedGrocery();

        while (flag) {

            System.out.println("Business day: " + hash.getBusinessDay());

            System.out.println(menu);

            System.out.println("Enter an option");
            String input = stdin.nextLine();

            if (input.equalsIgnoreCase("L")) {
                String filename = new String("");
                System.out.println("Enter file to load: ");
                filename = stdin.nextLine();
                hash.addItemCatalog(filename);
            } else if (input.equalsIgnoreCase("A")) {
                String itemCode = new String("");
                String itemName = new String("");
                double quantity = 0;
                double avgSales = 0;
                double price = 0;
                System.out.println("Enter item code:");
                itemCode = stdin.nextLine();
                System.out.println("Enter item name: ");
                itemName = stdin.nextLine();
                System.out.println("Enter Quantity in store: ");
                quantity = stdin.nextDouble();
                System.out.println("Enter Average sales per day: ");
                avgSales = stdin.nextDouble();
                System.out.println("Enter price: ");
                price = stdin.nextDouble();
                stdin.nextLine();

                Item tempItem = new Item();
                tempItem.setItemCode(itemCode);
                tempItem.setName(itemName);
                tempItem.setQtyInStore((int) quantity);
                tempItem.setAverageSalesPerDay((int) avgSales);
                tempItem.setPrice(price);


                try {
                    hash.addItem(tempItem);
                    System.out.println(itemCode + ": " + itemName + " added to inventory");
                } catch (ItemCodeAlreadyExistsException e) {
                    e.printStackTrace();
                }

            } else if (input.equalsIgnoreCase("B")) {
                String fileName = new String("");

                System.out.println("Enter filename: ");
                fileName = stdin.nextLine();

                hash.processSales(fileName);
            } else if (input.equalsIgnoreCase("C")) {
                System.out.println(hash.toString());
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println("Advancing business day...");
                hash.nextBusinessDay();

            } else if (input.equalsIgnoreCase("Q")) {
                flag = false;
            }

        }


    }
}
