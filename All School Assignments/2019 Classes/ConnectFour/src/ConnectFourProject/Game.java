package ConnectFourProject;

import java.util.*;
import java.util.Scanner;

public class Game {
    final static int boardrow = 6;
    final static int boardcolumn = 7;
    static char[][] board = new char[boardrow][boardcolumn];


    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        CreateBoard();


        int counter = 1;


        while (true) {
            if (emptySpace(board) == 0) {
                System.out.println("The game has ended in a draw.");
                break;
            }
            counter = Round(counter);
            char result = checkVertical(board);
            if (result != 'f') {
                if (result == 'R') {
                    System.out.println("Red player has won the game.");
                } else {
                    System.out.println("Yellow player has won the game.");
                }
                DisplayBoard();
                break;
            }

            char result2 = checkHorizontal(board);
            if (result2 != 'f') {
                if (result2 == 'R') {
                    System.out.println("Red player has won the game.");
                } else {
                    System.out.println("Yellow player has won the game.");
                }
                DisplayBoard();
                break;
            }

            char result3 = checkDiagonalRight(board);
            if (result3 != 'f') {
                if (result3 == 'R') {
                    System.out.println("Red player has won the game.");
                } else {
                    System.out.println("Yellow player has won the game.");
                }
                DisplayBoard();
                break;
            }


            char result4 = checkDiagonalLeft(board);
            if (result4 != 'f') {
                if (result4 == 'R') {
                    System.out.println("Red player has won the game.");
                } else {
                    System.out.println("Yellow player has won the game.");
                }
                DisplayBoard();
                break;
            }


        }


    }


    public static void CreateBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }

    }


    public static void DisplayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j == 6) {
                    System.out.print("|" + board[i][j] + "|");
                } else System.out.print("|" + board[i][j]);

            }
            System.out.println("");
        }

        System.out.println("...............");
    }


    public static void dropToken(char token, int inputColumn) {
        if (inputColumn < 0 || inputColumn > 6) {
            throw new ArrayIndexOutOfBoundsException("Input is outside the column size. Please try again.");


        }

        int bottom = 5;
        while (bottom >= 0) {
            if (board[bottom][inputColumn] == ' ') {
                board[bottom][inputColumn] = token;
                break;
            } else if (board[bottom][inputColumn] != ' ') {

                bottom--;

            }

            if (board[0][inputColumn] != ' ') {
                throw new ArrayIndexOutOfBoundsException("You cannot place a game piece there, the column is full");

            }
        }

    }


    public static char checkVertical(char[][] input) {


        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] != ' ') {
                    if (i + 1 > 5 || i + 2 > 5 || i + 3 > 5)
                        return 'f';

                    else if (input[i][j] != ' ' && input[i][j] == input[i + 1][j] && input[i][j] == input[i + 2][j] && input[i][j] == input[i + 3][j])
                        return input[i][j];

                }


            }
        }
        return 'f';
    }


    public static char checkHorizontal(char[][] input) {

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] != ' ') {
                    if (j + 1 > 6 || j + 2 > 6 || j + 3 > 6)
                        return 'f';

                    else if (input[i][j] != ' ' && input[i][j] == input[i][j + 1] && input[i][j] == input[i][j + 2] && input[i][j] == input[i][j + 3])
                        return input[i][j];


                }

            }
        }
        return 'f';
    }


    public static char checkDiagonalRight(char[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] != ' ') {
                    if (j + 1 > 6 || j + 2 > 6 || j + 3 > 6 || i + 1 > 5 || i + 2 > 5 || i + 3 > 5) {
                        return 'f';
                    } else if (input[i][j] == input[i + 1][j + 1] && input[i][j] == input[i + 2][j + 2] && input[i][j] == input[i + 3][j + 3]) {
                        return input[i][j];
                    }


                }

            }
        }

        return 'f';
    }


    public static char checkDiagonalLeft(char[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] != ' ') {
                    if (j - 1 < 0 || j - 2 < 0 || j - 3 < 0 || i + 1 > 5 || i + 2 > 5 || i + 3 > 5) {
                        return 'f';
                    } else if (input[i][j] == input[i + 1][j - 1] && input[i][j] == input[i + 2][j - 2] && input[i][j] == input[i + 3][j - 3]) {
                        return input[i][j];
                    }


                }

            }
        }

        return 'f';
    }


    public static int Round(int counter) {
        Scanner stdin = new Scanner(System.in);
        DisplayBoard();


        if (counter % 2 == 0) {
            System.out.println("Drop a yellow disk at column (0-6): ");
            try {
                int columnInput = stdin.nextInt();
                dropToken('Y', columnInput);
            } catch (InputMismatchException ex) {
                System.out.println("Your input was not a valid integer.");
                stdin.nextLine();
                return counter;
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
                stdin.nextLine();
                return counter;
            }


        } else {
            System.out.println("Drop a red disk at column (0-6): ");
            try {
                int columnInput = stdin.nextInt();
                dropToken('R', columnInput);
            } catch (InputMismatchException ex) {
                System.out.println("Your input was not a valid integer.");
                stdin.nextLine();
                return counter;
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
                stdin.nextLine();
                return counter;
            }


        }
        counter++;
        return counter;
    }


    public static int emptySpace(char[][] input) {
        int counter = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == ' ')
                    counter++;
            }
        }
        return counter;
    }


}