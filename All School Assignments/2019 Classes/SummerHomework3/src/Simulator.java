/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */


public class Simulator {


    int totalWait = 0;
    int totalRequests = 0;


    public static void simulate(double probability, int numFloors, int numElevators, int time) throws
            InvalidProbabilityException, InvalidArgumentException {
        int timeCounter = 1;

        if (probability < 0 || probability > 1) {

            throw new InvalidProbabilityException("Invalid probability input.");

        }

        if (numFloors <= 1) {
            throw new InvalidArgumentException("Invalid number of floors.");
        }

        if (numElevators <= 0) {
            throw new InvalidArgumentException("Invalid number of elevators.");
        }

        if (time <= 0) {
            throw new InvalidArgumentException("Invalid time length for simulation.");
        }


        BooleanSource bSource = null;
        bSource = new BooleanSource(probability);

        Elevator[] elevators = new Elevator[numElevators];


        for (int i = 0; i <= numElevators-1; i++) {
            elevators[i] = new Elevator();
        }


        RequestQueue queue = new RequestQueue();


        while (timeCounter != time + 1) {

            for (int i = 0; i < elevators.length; i++) {


                if (elevators[i].getElevatorState() == 1) {
                    if (elevators[i].getCurrentFloor() < elevators[i].getRequest().getDestinationFloor()) {
                        elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
                    } else if (elevators[i].getCurrentFloor() > elevators[i].getRequest().getDestinationFloor()) {
                        elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
                    }
                }

                if (elevators[i].getElevatorState() == -1) {

                    if (elevators[i].getCurrentFloor() < elevators[i].getRequest().getSourceFloor()) {
                        elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
                    } else if (elevators[i].getCurrentFloor() > elevators[i].getRequest().getSourceFloor()) {
                        elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
                    }

                }

                if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getSourceFloor()) {
                    elevators[i].setElevatorState(1);
                }

                if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getDestinationFloor()) {
                    elevators[i].setElevatorState(0);

                }
            }

                System.out.print("Step " + timeCounter + ":");

                if (bSource.requestArrived()) {
                    Request newRequest = new Request(numFloors);
                    newRequest.setTimeEntered(timeCounter);
                    System.out.print("A request arrives from Floor " + newRequest.getSourceFloor() + " to Floor " +
                            newRequest.getDestinationFloor());

                    queue.enqueue(newRequest);

                    for (int x = 0; x < elevators.length; x++) {
                        if (elevators[x].getElevatorState() == 0 && queue.size() != 0) {
                            elevators[x].setRequest(newRequest);
                            try {
                                queue.dequeue();
                            } catch (EmptyQueueException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    System.out.print("Requests: ");
                    if (queue.size() != 0) {
                        for (int z = 0; z < queue.size(); z++) {
                            System.out.print(queue.get(z).toString());
                        }
                    }


                    for (int a = 0; a < elevators.length; a++) {

                        if (elevators[a].getElevatorState() == 0) {
                            System.out.print("[" + elevators[a].getCurrentFloor() + ", --- " +
                                    ", " + elevators[a].getRequest().toString());
                        } else {
                            System.out.print("[" + elevators[a].getCurrentFloor() + ", " + elevators[a].stateString() +
                                    ", " + elevators[a].getRequest().toString());
                        }
                    }


                } else {
                    System.out.print("Nothing arrives");


                }

                timeCounter++;


            }
        }


    }


