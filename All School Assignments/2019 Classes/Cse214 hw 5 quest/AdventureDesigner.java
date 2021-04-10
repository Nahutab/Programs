/**
 * Name:Batuhan Aykac
 * SBU ID: 112813167
 * Recitation: No.3
 */
import java.util.Scanner;

/**
 * This class is to guide the user to make and play the game.
 */
public class AdventureDesigner {
    /**
     * This is the main method to create and then play the game.
     * @param args
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        SceneTree tree = new SceneTree();

        boolean flag = false;
        String input = new String("");

        String titleTemp = new String("");
        String sceneTemp = new String("");
        System.out.println("Creating a story...");

        System.out.println("Please enter a title: ");
        titleTemp = stdin.nextLine();
        System.out.println("Please enter a scene:");
        sceneTemp = stdin.nextLine();

        try {
            tree.addNewNode(titleTemp,sceneTemp);
            System.out.println("Scene #" + SceneNode.numScenes + " added.");
        } catch (FullSceneException e) {
            e.printStackTrace();
        }


        while (!flag) {

            mainMenu();



            input = stdin.nextLine();

            if (input.equalsIgnoreCase("Q")) {
                flag = true;
            } else if (input.equalsIgnoreCase("A")) {

                String title = new String("");
                System.out.println("Please enter a title");
                title = stdin.nextLine();
                String scene = new String("");
                System.out.println("Please enter a scene:");
                scene = stdin.nextLine();
                try {
                    tree.addNewNode(title, scene);
                    System.out.println("Scene #" + SceneNode.numScenes + " added.");
                } catch (FullSceneException e) {
                    e.printStackTrace();
                }



            } else if (input.equalsIgnoreCase("R")) {
                String option = new String("");
                System.out.println("Please enter an option:");
                option = stdin.nextLine();

                try {
                    tree.removeScene(option);
                    if (option.equalsIgnoreCase("A")) {
                        System.out.println("The first path has been removed.");
                    }

                    if ((option.equalsIgnoreCase("B"))) {
                        System.out.println("The second path has been removed.");
                    }

                    if ((option.equalsIgnoreCase("C"))) {
                        System.out.println("The third path has been removed.");
                    }
                } catch (NoSuchNodeException e) {
                    e.printStackTrace();
                }


            } else if (input.equalsIgnoreCase("S")) {
                tree.getCursor().displayFullScene();
            } else if (input.equalsIgnoreCase("P")) {
                System.out.print(tree.toString()) ;
                System.out.print("\n");
            } else if (input.equalsIgnoreCase("B")) {

                try {
                    tree.moveCursorBackwards();
                    System.out.println("Successfully moved to " + tree.getCursor().getTitle());
                } catch (NoSuchNodeException e) {
                    e.printStackTrace();
                }


            } else if (input.equalsIgnoreCase("F")) {
                String option = new String("");
                System.out.println("Which option do you wish to go to: ");
                option = stdin.nextLine();
                try {
                    tree.moveCursorForward(option);
                    System.out.println("Successfully moved to " + tree.getCursor().getTitle());
                } catch (NoSuchNodeException e) {
                    e.printStackTrace();
                }

            } else if (input.equalsIgnoreCase("N")) {
                System.out.println(tree.getPathFromRoot());
            } else if (input.equalsIgnoreCase("M")) {
                int temp = 0;
                System.out.println("Move current scene to: ");
                temp = stdin.nextInt();

                try {
                    tree.moveScene(temp);
                    System.out.println("Successfully moved scene.");
                } catch (NoSuchNodeException e) {
                    e.printStackTrace();
                } catch (FullSceneException e) {
                    e.printStackTrace();
                }
            } else if (input.equalsIgnoreCase("G")) {
                System.out.println("Now beginning game...");
                playGame(tree.getRoot());
                System.out.println();
                System.out.println("The End");
                System.out.println("Returning back to creation mode...");


            }

        }


    }


    /**
     * This is the method to have user input and play the game that has been created
     * @param root SceneNode object which is the root of the tree.
     */
    public static void playGame(SceneNode root) {
        Scanner stdin = new Scanner(System.in);
        SceneNode temp = new SceneNode();
        temp = root;
        String option = new String("");


        while (!temp.isEnding()) {
            System.out.println(temp.getTitle() + "\n" +
                    temp.getSceneDescription() + "\n" +
                    "\n");

            if(temp.getLeft() != null){
               System.out.println("A) " + temp.getLeft().getTitle() + "\n");
            }
             if(temp.getMiddle() != null){
                 System.out.print("B) " + temp.getMiddle().getTitle() + "\n");
             }

             if(temp.getRight() != null){
                 System.out.print("C)" + temp.getRight().getTitle() + "\n");
             }



            System.out.println("\n"+"Please enter an option");
            option = stdin.next();



            if (option.equalsIgnoreCase("A")) {
                temp = temp.getLeft();
            }

            if (option.equalsIgnoreCase("B")) {
                temp = temp.getMiddle();
            }

            if (option.equalsIgnoreCase("C")) {
                temp = temp.getRight();
            }




        }

        System.out.println(temp.getTitle() + "\n" + temp.getSceneDescription());

    }

    /**
     * Helper method to print out the main menu.
     */
    public static void mainMenu(){
        System.out.println("A) Add Scene\n" +
                "R) Remove Scene\n" +
                "S) Show Current Scene\n" +
                "P) Print Adventure Tree\n" +
                "B) Go Back A Scene\n" +
                "F) Go Forward A Scene\n" +
                "G) Play Game\n" +
                "N) Print Path To Cursor\n" +
                "M) Move scene\n" +
                "Q) Quit\n" +
                "\n" +
                "Please enter a selection:");
    }



}
