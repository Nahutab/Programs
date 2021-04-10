/**
 * Name:Batuhan Aykac
 * SBU ID: 112813167
 * Recitation: No.3
 */
import java.util.ArrayList;

/**
 * SceneTree class represents a collection of SceneNode objects arranged as a ternary tree.
 */
public class SceneTree {

    private SceneNode root;
    private SceneNode cursor;

    /**
     *
     * This is an empty constructor that sets the cursor equal to the root when a Tree is created
     */
    public SceneTree() {
        cursor = root;
    }

    /**
     * This is a method that moves the cursor backwards
     * @throws NoSuchNodeException Throws this exception when the cursor can't move back
     * because a node doesn't exist there.
     */
    public void moveCursorBackwards() throws NoSuchNodeException {

        if (cursor.getParent() == null) {
            throw new NoSuchNodeException("the current node does not have a parent.");
        } else {
            cursor = cursor.getParent();
        }
    }

    /**
     * This is a method that moves the cursor forwards
     * @param option Option is either A,B, or C, which determines which child it moves to
     * @throws NoSuchNodeException Throws this exception when the option selected has no child to move forward to.
     */
    public void moveCursorForward(String option) throws NoSuchNodeException {

        if (option.equals("A")) {
            if (cursor.getLeft() == null) {
                throw new NoSuchNodeException("the current node does not have a left child");
            } else cursor = cursor.getLeft();
        } else if (option.equals("B")) {
            if (cursor.getMiddle() == null) {
                throw new NoSuchNodeException("the current node does not have a middle child");
            } else cursor = cursor.getMiddle();
        } else if (option.equals("C")) {
            if (cursor.getRight() == null) {
                throw new NoSuchNodeException("the current node does not have a right child");
            } else cursor = cursor.getRight();
        }


    }

    /**
     * This is a method to add a new node to the tree.
     * @param title this is an input parameter for the title of the node
     * @param sceneDescription this is an input parameter for the scene description of a node.
     * @throws FullSceneException Throws this exception when there is no more space to put a
     * node as a child of a parent.
     */
    public void addNewNode(String title, String sceneDescription) throws FullSceneException {


        SceneNode n = new SceneNode();

        n.setTitle(title);
        n.setSceneDescription(sceneDescription);

        if (cursor == null) {
            cursor = n;
            root = n;
            return;
        }


        if (cursor.getMiddle() != null && cursor.getLeft() != null && cursor.getRight() != null) {
            SceneNode.setNumScenes(SceneNode.getNumScenes() - 1);
            throw new FullSceneException("the current node does not have any available child positions.");
        }

        if (cursor.getLeft() == null) {
            cursor.setLeft(n);
            n.setParent(cursor);
        } else if (cursor.getMiddle() == null) {
            cursor.setMiddle(n);
            n.setParent(cursor);
        } else if (cursor.getRight() == null) {
            cursor.setRight(n);
            n.setParent(cursor);
        }


    }

    /**
     * This is a method to remove a scene
     * @param option Option is either A,B, or C which correlates with either the left, middle, or right child to remove.
     * @throws NoSuchNodeException Throws this exception when there exists no child that was selected to be removed.
     */
    public void removeScene(String option) throws NoSuchNodeException {

        if (option.equals("A")) {
            if (cursor.getLeft() == null) {
                throw new NoSuchNodeException("the current node does not have a left child");
            } else {
                cursor.setLeft(null);
                cursor.setLeft(cursor.getMiddle());
                cursor.setMiddle(cursor.getRight());
                cursor.setRight(null);
            }
        } else if (option.equals("B")) {
            if (cursor.getMiddle() == null) {
                throw new NoSuchNodeException("the current node does not have a middle child");
            } else {
                cursor.setMiddle(null);
                cursor.setMiddle(cursor.getRight());
                cursor.setRight(null);
            }
        } else if (option.equals("C")) {
            if (cursor.getRight() == null) {
                throw new NoSuchNodeException("the current node does not have a right child");
            } else cursor.setRight(null);
        }
    }


    public void moveScene(int sceneIDToMoveTo) throws NoSuchNodeException, FullSceneException {
        SceneNode result = findNode(sceneIDToMoveTo, root);

        if (result == null) {
            throw new NoSuchNodeException("This node does not exist");
        }

        if(cursor.getParent().getLeft()==cursor){
            cursor.getParent().setLeft(null);
        }

        if(cursor.getParent().getMiddle()==cursor){
            cursor.getParent().setMiddle(null);
        }

        if(cursor.getParent().getRight()==cursor){
            cursor.getParent().setRight(null);
        }

        if (result.getLeft() != null && result.getMiddle() != null && result.getRight() != null) {
            throw new FullSceneException("The scene has no available child positions.");
        }

       else if (result.getLeft() == null) {
            result.setLeft(cursor);
            cursor.setParent(result);
        }
        else if (result.getMiddle() == null) {
            result.setMiddle(cursor);
            cursor.setParent(result);
        }
        else if (result.getRight() == null) {
            result.setRight(cursor);
            cursor.setParent(result);
        }
    }

    /**
     * This is a method that find a node in the tree based on the id
     * @param id id of the scene
     * @param currentNode the current node that is selected
     * @return returns the object, SceneNode that we were trying to find else it returns null.
     */
    public SceneNode findNode(int id, SceneNode currentNode) {
        SceneNode result = null;


        if (currentNode.getSceneID() == id) {
            return currentNode;
        }
        if (currentNode.getLeft() != null) {
            result = findNode(id, currentNode.getLeft());
        }
        if (result == null && currentNode.getMiddle() != null) {
            result = findNode(id, currentNode.getMiddle());
        }
        if (result == null && currentNode.getRight() != null) {
            result = findNode(id, currentNode.getRight());
        }
        return result;
    }

    /**
     * This is a method to create a string to show the path from the root of the tree
     * to the currently selected SceneNode.
     * @return String representation of the path.
     */
    public String getPathFromRoot() {
        ArrayList<String> tempArray = new ArrayList<>();
        SceneNode tempScene = cursor.getParent();

        tempArray.add(cursor.getTitle());


        while (tempScene != root) {
            tempArray.add(tempScene.getTitle());
            tempScene = tempScene.getParent();
        }
        tempArray.add(tempScene.getTitle());


        String output = new String("");

        for (int i = tempArray.size() - 1; i >= 0; i--) {
            output += tempArray.get(i) + ",";
        }


        return output;


    }

    /**
     * This is a method to construct a string representation of the tree.
     * @return string representation of the tree.
     */
    public String toString() {
        return printTree(cursor.getSceneID(), root, 0);
    }

    /**
     * Helper method for the toString method.
     * @param cursorID ID of the current node
     * @param currentNode current node
     * @param tabNum helper to determine amount of tabs we need when printing.
     * @return String representation of the tree.
     */
    public String printTree(int cursorID, SceneNode currentNode, int tabNum) {
        String result = new String("");

        if (currentNode.getSceneID() == cursorID) {
            result = currentNode.toString() + "*";
        } else {
            result = currentNode.toString();
        }

        if (currentNode.getLeft() != null) {
            result += "\n";
            for (int i = 0; i <= tabNum; i++) {
                result += "\t";
            }
            result += "A) " + printTree(cursorID, currentNode.getLeft(), tabNum + 1);
        }

        if (currentNode.getMiddle() != null) {
            result += "\n";
            for (int i = 0; i <= tabNum; i++) {
                result += "\t";
            }
            result += "B) " + printTree(cursorID, currentNode.getMiddle(), tabNum + 1);
        }

        if (currentNode.getRight() != null) {
            result += "\n";
            for (int i = 0; i <= tabNum; i++) {
                result += "\t";
            }
            result += "C) " + printTree(cursorID, currentNode.getRight(), tabNum + 1);
        }
        return result;
    }

    /**
     * Getter method to get the cursor
     * @return cursor SceneNode object
     */
    public SceneNode getCursor() {
        return cursor;
    }

    /**
     * getter method to get the root
     * @return root SceneNode object
     */
    public SceneNode getRoot() {
        return root;
    }
}
