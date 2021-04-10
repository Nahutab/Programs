import java.util.Scanner;
import java.io.*;


public class Tree {

    private TreeNode root;
    private TreeNode cursor;

    public Tree() {
        cursor = root;
    }


    public void readFile(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                String line = scan.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Tree(String fileName){
        File file = new File(fileName)



    }









    public boolean addNode(String label, String prompt, String message, String parentLabel) {

        if (findNode(parentLabel, root) == null) {
            return false;
        } else {

            TreeNode parentNode = findNode(parentLabel, root);
            TreeNode newNode = new TreeNode(label, message, prompt);


            if (parentNode.hasLeft() && parentNode.hasMiddle() && parentNode.hasRight()) {
                return false;
            } else if (!parentNode.hasLeft()) {
                newNode.setParent(parentNode);
                parentNode.setLeft(newNode);
                return true;
            } else if (!parentNode.hasMiddle()) {
                newNode.setParent(parentNode);
                parentNode.setMiddle(newNode);
                return true;
            } else if (!parentNode.hasRight()) {
                newNode.setParent(parentNode);
                parentNode.setRight(newNode);
                return true;
            }


        }

        return false;
    }

    public TreeNode getNodeReference(String label) {
        return findNode(label, root);
    }


    public void preOrder() {
        iterate(root);
    }


    public void iterate(TreeNode node) {

        if (node == null) {
            return;
        }
        System.out.printf("%s ", node.getMessage());
        iterate(node.getLeft());
        iterate(node.getMiddle());
        iterate(node.getRight());
    }


    public TreeNode findNode(String parentLabel, TreeNode tempNode) {
        if (tempNode != null) {
            if (tempNode.getLabel().equals(parentLabel)) {
                return tempNode;
            } else {
                TreeNode newNode = findNode(parentLabel, tempNode.getLeft());
                if (newNode == null) {
                    newNode = findNode(parentLabel, tempNode.getMiddle());
                }

                if (newNode == null) {
                    newNode = findNode(parentLabel, tempNode.getRight());
                }

                return newNode;
            }
        } else {
            return null;
        }
    }

    public void beginSession() throws TreeDoesNotExistException {
        Scanner stdin = new Scanner(System.in);

        if (root == null) {
            throw new TreeDoesNotExistException("Tree has not been setup yet");
        }


        boolean flag = true;
        cursor = root;
        int input = -1;
        while (flag) {

            if(cursor.hasLeft() || cursor.hasMiddle() || cursor.hasRight()){
                System.out.print(cursor.getMessage());
            }


            System.out.print(inputMessages());
            input = stdin.nextInt();

            if (input == 1) {
                cursor = cursor.getLeft();
            } else if (input == 2) {
                cursor = cursor.getMiddle();
            } else if (input == 3) {
                cursor = cursor.getRight();
            } else if (input == 0) {
                flag = false;
            }


            if (!cursor.hasLeft() && !cursor.hasMiddle() && !cursor.hasRight()) {
                System.out.print(cursor.getMessage());
            }


        }

    }

    public String inputMessages() {

        String output = new String("");

        if (cursor.hasLeft()) {
            output += ("(1) " + cursor.getLeft().getPrompt() + "\n");
        }
        if (cursor.hasMiddle()) {
            output += ("(2) " + cursor.getMiddle().getPrompt() + "\n");
        }
        if (cursor.hasRight()) {
            output += ("(3) " + cursor.getRight().getPrompt() + "\n");
        }

        output += ("(0) Exit session \n");

        return output;

    }






    public void goBack() {
        cursor = cursor.getParent();
    }

    public void moveLeft() {
        cursor = cursor.getLeft();
    }

    public void moveMiddle() {
        cursor = cursor.getMiddle();
    }

    public void moveRight() {
        cursor = cursor.getRight();
    }


}
