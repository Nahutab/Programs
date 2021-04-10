/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

import java.util.Scanner;

/**
 * This is the main driver class for user input.
 */
public class DepartmentStore {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        boolean flag = true;

        stdin.useDelimiter("\n");


        ItemList list = new ItemList();


        while (flag) {


            mainMenu();

            String input = stdin.next();

            if (input.equalsIgnoreCase("Q")) {
                flag = false;
            } else if (input.equalsIgnoreCase("I")) {

                System.out.print("Enter the name: ");
                String name = stdin.next();


                System.out.print("Enter the RFID: ");
                String rfid = stdin.next();


                System.out.print("Enter the original location: ");
                String origin = stdin.next();


                System.out.print("Enter the price: ");
                double price = stdin.nextDouble();

                list.insertInfo(name, rfid, price, origin);
            } else if (input.equalsIgnoreCase("M")) {
                System.out.print("Enter the RFID: ");
                String rfid = stdin.next();

                System.out.print("Enter the original location: ");
                String origin = stdin.next();

                System.out.print("Enter the new location: ");
                String currentLocation = stdin.next();


                if (origin.equals("out") || currentLocation.equals("out")) {
                    System.out.print("Invalid location(s)");
                } else {
                    if (list.moveItem(rfid, origin, currentLocation) == false) {
                        System.out.print("The item was not found.");
                    }
                }
            } else if (input.equalsIgnoreCase("L")) {
                System.out.print("Enter the location: ");
                String location = stdin.next();
                System.out.print(String.format("%-21s%-26s%-19s%-13s%9s", "Item Name", "RFID", "Original Location",
                        "Current Location", "Price"));
                System.out.print("\n");

                list.printByLocation(location);

            } else if (input.equalsIgnoreCase("P")) {
                System.out.print(String.format("%-21s%-26s%-19s%-13s%9s", "Item Name", "RFID", "Original Location",
                        "Current Location", "Price \n"));
                list.printAll();
            } else if (input.equalsIgnoreCase("O")) {
                System.out.print("Enter cart number: ");
                String cart = stdin.next();
                System.out.print(String.format("%-21s%-26s%-19s%-13s%9s", "Item Name", "RFID", "Original Location",
                        "Current Location", "Price"));
                System.out.print("\n");
                double price = list.checkOut(cart);

                System.out.print("\n The total cost for all merchandise in the cart was $" + price + "\n");


            } else if (input.equalsIgnoreCase("C")) {
                System.out.print("The following item(s) have been moved back to their original locations:\n");
                System.out.print(String.format("%-21s%-26s%-19s%-13s%9s", "Item Name", "RFID", "Original Location",
                        "Current Location", "Price"));
                System.out.print("\n");


                list.cleanStore();
            } else if (input.equalsIgnoreCase("U")) {
                System.out.print("The following item(s) have removed from the system:\n");
                System.out.print(String.format("%-21s%-26s%-19s%-13s%9s", "Item Name", "RFID", "Original Location",
                        "Current Location", "Price"));
                System.out.print("\n");

                list.updateSystem();

            } else if (input.equalsIgnoreCase("r")) {

                System.out.print("Enter the RFID: ");
                String rfidTag = stdin.next();

                System.out.print(String.format("%-21s%-26s%-19s%-13s%9s", "Item Name", "RFID", "Original Location",
                        "Current Location", "Price"));
                System.out.print("\n");

                list.printByRFID(rfidTag);

            }


        }


    }

    /**
     * This is a helper method to easily print out the main menu and make it more readable.
     */
    public static void mainMenu() {
        System.out.print("    C - Clean store \n" +
                "    I - Insert an item into the list \n" +
                "    L - List by location \n" +
                "    M - Move an item in the store \n" +
                "    O - Checkout  \n" +
                "    P - Print all items in store \n" +
                "    R - Print by RFID tag number \n" +
                "    U - Update inventory system   \n" +
                "    Q - Exit the program. \n" +
                "    Please select an option: ");
    }


}
