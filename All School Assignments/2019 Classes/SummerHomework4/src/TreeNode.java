public class TreeNode {

    private TreeNode left;
    private TreeNode middle;
    private TreeNode right;
    private TreeNode parent;

    private String label;
    private String message;
    private String prompt;

    public TreeNode(String label, String message, String prompt) {
        this.label = label;
        this.message = message;
        this.prompt = prompt;
    }

    public String getLabel() {
        return label;
    }

    public String getMessage() {
        return message;
    }

    public String getPrompt() {
        return prompt;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getMiddle() {
        return middle;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMiddle(TreeNode middle) {
        this.middle = middle;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }



    public boolean hasLeft() {
        if (getLeft() != null) {
            return true;
        } else return false;
    }

    public boolean hasMiddle() {
        if (getMiddle() != null) {
            return true;
        } else return false;
    }

    public boolean hasRight() {
        if (getRight() != null) {
            return true;
        } else return false;
    }


}
