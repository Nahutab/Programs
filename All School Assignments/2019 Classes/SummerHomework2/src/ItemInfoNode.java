/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */


/**
 * This class is to create node objects based on the information from ItemInfo class.
 */
public class ItemInfoNode {
    private ItemInfo data;
    private ItemInfoNode next;
    private ItemInfoNode prev;

    /**
     * Constructor to create a node.
     * @param initialData information that is of ItemInfo type.
     */
    public ItemInfoNode(ItemInfo initialData) {

        this.data = initialData;
        this.next = null;
        this.prev = null;

    }

    /**
     * Setter method to set the info of the node.
     * @param info ItemInfo type information.
     */
    public void setInfo(ItemInfo info) {
        this.data = info;
    }

    /**
     * getter method to get the info.
     * @return ItemInfo type returned.
     */
    public ItemInfo getInfo() {
        return data;
    }

    /**
     * Setter method to set the next node link.
     * @param node the node to be set to next.
     */
    public void setNext(ItemInfoNode node) {
        this.next = node;
    }

    /**
     * setter method to set the previous node link.
     * @param node the node to be set to previous.
     */
    public void setPrev(ItemInfoNode node) {
        this.prev = node;
    }

    /**
     * Helper method to check if the linked list has a next value after the cursor.
     * @return True if the next is not null false otherwise.
     */
    public boolean hasNext() {
        if (next == null) {
            return false;
        } else return true;
    }

    /**
     * Helper method to check if the linked list has a previous value before the cursor.
     * @return True if the previous is not null false otherwise.
     */
    public boolean hasPrev() {
        if (prev == null) {
            return false;
        } else return true;
    }


    /**
     * getter method to get the next node.
     * @return returns next node.
     */
    public ItemInfoNode getNext() {
        return next;
    }

    /**
     * getter method to get the previous node.
     * @return returns previous node.
     */
    public ItemInfoNode getPrev() {
        return prev;
    }


}
