/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

import java.util.Scanner;

public class Analyzer {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        System.out.print("Welcome to the Elevator simulator!\n");

        System.out.print("Please enter the probability of arrival for Requests: ");
        double prob = stdin.nextDouble();
        System.out.print("Please enter the number of floors: ");
        int numFloors = stdin.nextInt();
        System.out.print("Please enter the number of elevators: ");
        int numElevators = stdin.nextInt();
        System.out.print("Please enter the length of the simulation (in time units): ");
        int simTimeLength = stdin.nextInt();



        try {
            Simulator.simulate(prob, numFloors, numElevators, simTimeLength);
        } catch (InvalidProbabilityException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }


    }
}
