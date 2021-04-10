/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */


/**
 * This is the class that uses ItemInfoNodes for a linkedlist
 */
public class ItemList {

    private ItemInfoNode head;
    private ItemInfoNode cursor;

    /**
     * getter method to get the cursor.
     * @return returns cursor node.
     */
    public ItemInfoNode getCursor() {
        return cursor;
    }

    /**
     * setter method to set the cursor.
     * @param cursor input is a cursor node.
     */
    public void setCursor(ItemInfoNode cursor) {
        this.cursor = cursor;
    }

    /**
     * setter method to set the head.
     * @param head head input is an ItemInfoNode.
     */
    public void setHead(ItemInfoNode head) {
        this.head = head;
    }

    /**
     * getter method to get the head node.
     * @return returns head node.
     */
    public ItemInfoNode getHead() {
        return head;
    }

    /**
     * empty constructor for the list.
     */
    public ItemList() {

    }

    /**
     * Method to insert the node into the linkedlist.
     *
     * Order of Complexity: O(n).
     *
     * @param name String type for name of the merchandise.
     * @param rfidTag rfid tag String type for merchandise.
     * @param price Price is a type double.
     * @param initPosition The original position, String type.
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) {

        ItemInfo info = null;
        try {
            info = new ItemInfo(name, rfidTag, price, initPosition);
        } catch (InvalidStringException e) {
            e.printStackTrace();
        } catch (NegativePriceException e) {
            e.printStackTrace();
        }

        ItemInfoNode newNode = new ItemInfoNode(info);

        cursor = head;


        if (head == null) {
            setHead(newNode);
        } else {
            while (cursor != null) {

                if (cursor.getInfo().getRfidTagNumber().compareTo(rfidTag) < 0) {
                    if (cursor.getNext() == null) {
                        newNode.setPrev(cursor);
                        cursor.setNext(newNode);
                        return;
                    } else
                        cursor = cursor.getNext();

                } else if (cursor.getInfo().getRfidTagNumber().compareTo(rfidTag) == 0) {

                    if (cursor.getNext() == null) {
                        cursor.setNext(newNode);
                        newNode.setPrev(cursor);
                        return;
                    } else {
                        newNode.setNext(cursor.getNext());
                        newNode.getNext().setPrev(newNode);
                        newNode.setPrev(cursor);
                        cursor.setNext(newNode);
                        return;
                    }
                } else if (cursor.getInfo().getRfidTagNumber().compareTo(rfidTag) > 0) {

                    if (cursor.getPrev() == null) {
                        newNode.setNext(cursor);
                        cursor.setPrev(newNode);
                        setHead(newNode);
                        return;

                    } else {
                        newNode.setPrev(cursor.getPrev());
                        newNode.setNext(cursor);
                        cursor.getPrev().setNext(newNode);
                        cursor.setPrev(newNode);
                        return;

                    }
                }

            }


        }


    }

    /**
     * Helper method to remove a node from a linked list.
     * @param removableNode the node to be removed.
     * @return returns the removed node to be printed later.
     */
    public ItemInfoNode removeNode(ItemInfoNode removableNode) {

        if (removableNode == head) {
            head = null;
            if (removableNode.getNext() != null) {
                setHead(removableNode.getNext());
            }
        } else if (removableNode.getNext() == null) {
            removableNode.getPrev().setNext(null);
            removableNode.setPrev(null);
        } else if (removableNode.getPrev() != null && removableNode.getNext() != null) {
            removableNode.getPrev().setNext(removableNode.getNext());
            removableNode.getNext().setPrev(removableNode.getPrev());
        }

        return removableNode;
    }

    /**
     * Removes all the merchandise that has been purchased, labeled "out" and prints it out.
     *
     * Order of complexity: O(n).
     */
    public void removeAllPurchased() {
        cursor = head;

        while (cursor != null) {

            if (cursor.getInfo().getCurrentLocation().equals("out")) {
                System.out.print(cursor.getInfo().toString());
                removeNode(cursor);
            } else {
                cursor = cursor.getNext();
            }

        }

    }

    /**
     * A method to move an item from a source to a destination within the info, not the LinkedList.
     *
     * Order of Complexity: O(n)
     *
     * @param rfidTag String rfidtag of the merchandise that is to be moved.
     * @param source String of the original location of the merchandise.
     * @param dest String destination of where the merchandise is to be moved.
     * @return returns true if it was able to find the merchandise to move, false otherwise.
     */
    public boolean moveItem(String rfidTag, String source, String dest) {

        cursor = head;

        while (cursor != null) {
            if (cursor.getInfo().getRfidTagNumber().equals(rfidTag) && cursor.getInfo().getCurrentLocation()
                    .equals(source)) {
                cursor.getInfo().setCurrentLocation(dest);

                return true;
            }
            cursor = cursor.getNext();
        }

        return false;

    }

    /**
     * Prints all the merchandise
     *
     *
     * Order of complexity: O(n).
     */
    public void printAll() {
        cursor = head;

        while (cursor != null) {
            System.out.print(cursor.getInfo().toString() + "\n");
            cursor = cursor.getNext();
        }

    }

    /**
     * This is a method to print all the merchandise that are at the same current location.
     *
     * Order of Complexity: O(n)
     *
     * @param location location that is to be searched.
     */
    public void printByLocation(String location) {
        cursor = head;

        while (cursor != null) {

            if (cursor.getInfo().getCurrentLocation().equals(location)) {
                System.out.print(cursor.getInfo().toString() + "\n");
            }
            cursor = cursor.getNext();

        }
    }

    /**
     * Method that moves all items that aren't at their original location to the original location.
     *
     *
     * Order of Complexity: O(n)
     */
    public void cleanStore() {

        cursor = head;

        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().equals(cursor.getInfo().getOriginalLocation()) == false &&
                    cursor.getInfo().getCurrentLocation().equals("out") == false) {
                System.out.print(cursor.getInfo().toString() + "\n");
                cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());
            }
            cursor = cursor.getNext();
        }


    }

    /**
     * Method that prints and sets the location of the merchandise in the cart to "out".
     *
     * Order of Complexity: O(n)
     *
     * @param cartNumber String cart number to search for in the linkedlist.
     * @return returns the total price of the merchandise in the shopping cart.
     */
    public double checkOut(String cartNumber) {

        cursor = head;


        double priceTotal = 0;
        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().equals(cartNumber)) {
                System.out.print(cursor.getInfo().toString());
                priceTotal += cursor.getInfo().getPrice();
                cursor.getInfo().setCurrentLocation("out");
            }
            cursor = cursor.getNext();
        }
        return priceTotal;
    }

    /**
     * A method that removes all items at the current location "out".
     */
    public void updateSystem() {

        cursor = head;

        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().equals("out")) {
                System.out.print(removeNode(cursor).getInfo().toString());
            }
            cursor = cursor.getNext();
        }

    }

    /**
     * Method to print the nodes with the same RFID tag.
     * @param RFIDTag RFID Tag that is being searched to print the node information.
     */
    public void printByRFID(String RFIDTag) {
        cursor = head;

        while (cursor != null) {
            if (cursor.getInfo().getRfidTagNumber().equals(RFIDTag)) {
                System.out.print(cursor.getInfo().toString() + "\n");
            }

            cursor = cursor.getNext();
        }


    }


}
