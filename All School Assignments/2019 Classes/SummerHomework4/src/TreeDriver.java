import java.util.Scanner;

public class TreeDriver {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        stdin.useDelimiter("\n");


        System.out.print("L - Load a Tree.\n" +
                "H - Begin a Help Session.\n" +
                "T - Traverse the Tree in preorder.\n" +
                "Q - Quit\n" +
                "Choice>");

        String input = stdin.next();


        if (input.equalsIgnoreCase("L")) {
            System.out.print("Enter the file name>");

            Tree newTree = new Tree();

            newTree.readFile("hw5SampleText2.txt");





        }


    }


}
