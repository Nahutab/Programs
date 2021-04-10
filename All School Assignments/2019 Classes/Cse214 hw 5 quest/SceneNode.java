/**
 * Name:Batuhan Aykac
 * SBU ID: 112813167
 * Recitation: No.3
 */


/**
 * SceneNode class is a class that represents a specific scene.
 */
public class SceneNode {
    private String title;
    private String sceneDescription;
    private int sceneID;
    private SceneNode left;
    private SceneNode middle;
    private SceneNode right;
    static int numScenes;
    private SceneNode parent;


    /**
     * This an empty constructor that also increments sceneID by the variable numScenes every time a scene node is
     * created.
     */
    public SceneNode(){
        setSceneID(++numScenes);
    }


    /**
     * Method to add a Scene node
     * @param scene scene is a type of scene node. It is the one we are trying to add.
     * @throws FullSceneException this is the exception for when there are 3 children so there's no space to add.
     */
    public void addSceneNode(SceneNode scene) throws FullSceneException{
        if(left == null){
            left = scene;
        }
        else if (middle == null){
            middle = scene;
        }

        else if (right == null){
            right = scene;
        }
        else throw new FullSceneException("The current node does not have any empty child nodes.");
    }


    /**
     * This is a method to figure out if the node has children
     * @return Returns true if it is ending false otherwise.
     */
    public boolean isEnding(){
        return left == null && middle == null && right == null;
    }

    /**
     * Prints the info about the scene
     */
    public void displayScene(){
        System.out.println(title);
        System.out.println(sceneDescription);

        System.out.println("A)"+left.title);
        System.out.println("B)" + middle.title);
        System.out.println("C)"+ right.title);
    }

    /**
     * Displays the scene information more than displayScene method by including the children and sceneID.
     */
    public void displayFullScene(){
        System.out.println("Scene ID #"+sceneID);
        System.out.println("Title: " +title);
        System.out.println("Scene: " + sceneDescription);
        if(isEnding()){
            System.out.println("Leads to: NONE");
        }
        else {
            String output = new String("");



            if(left != null){
                output += "'"+left.title +"'  (#" + left.sceneID+"),";
            }

            if(middle != null){
                output += "'"+middle.title +"'  (#" + middle.sceneID+"),";
            }

            if(right != null){
                output += "'"+right.title +"'  (#" + right.sceneID+")";
            }
            System.out.println("Leads to: " + output);
        }
}

    /**
     * Method to create a string representing a brief summary of the node.
     * @return the string summary of the node.
     */
    public String toString(){
        String output = new String("");
       output += title + " (#" + sceneID +")";
       return output;
}

    /**
     * This is a getter method to get the parent
     * @return parent node
     */
    public SceneNode getParent() {
        return parent;
    }

    /**
     * This is a getter method to get the left node
     * @return left node
     */
    public SceneNode getLeft() {
        return left;
    }

    /**
     * This is a getter method to get the middle node
     * @return middle node
     */
    public SceneNode getMiddle() {
        return middle;
    }

    /**
     * This is a getter method to get the right node
     * @return right node
     */
    public SceneNode getRight() {
        return right;
    }

    /**
     * This is a setter method to set the parent node
     * @param parent SceneNode type
     */
    public void setParent(SceneNode parent) {
        this.parent = parent;
    }

    /**
     * THis is a setter method to set the left node
     * @param left SceneNode type
     */
    public void setLeft(SceneNode left) {
        this.left = left;
    }

    /**
     * This is a setter method to set the middle node
     * @param middle SceneNode type
     */
    public void setMiddle(SceneNode middle) {
        this.middle = middle;
    }

    /**
     * This is a setter method to set the right node
     * @param right SceneNode type
     */
    public void setRight(SceneNode right) {
        this.right = right;
    }

    /**
     * This is a setter method for the title
     * @param title String type
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This is a setter method for the scene description
     * @param sceneDescription String type
     */
    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    /**
     * this is a getter method to get the Scene Id
     * @return int value sceneID
     */
    public int getSceneID() {
        return sceneID;
    }

    /**
     * This is the getter method to get the Title
     * @return Title which is a string
     */
    public String getTitle() {
        return title;
    }

    /**
     * This is a setter method to set the Scene ID
     * @param sceneID int type
     */
    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    /**
     * This is a getter method to get the Scene's Description
     * @return String type
     */
    public String getSceneDescription() {
        return sceneDescription;
    }

    /**
     * This is a setter method to set the number of scenes when we increment to get scene id
     * @param numScenes int type
     */
    public static void setNumScenes(int numScenes) {
        SceneNode.numScenes = numScenes;
    }

    /**
     * This is a getter method to get the number of scenes
     * @return int type
     */
    public static int getNumScenes() {
        return numScenes;
    }


}
