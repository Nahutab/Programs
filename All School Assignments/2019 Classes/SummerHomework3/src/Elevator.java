/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */


public class Elevator {

    private int currentFloor;
    private int elevatorState;
    private Request request;

    final int IDLE = 0;
    final int TO_SOURCE = -1;
    final int TO_DESTINATION = 1;


    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }


    /**
     * The state of what the elevator is doing.
     * IDLE = 0.
     * TO_SOURCE = -1.
     * TO_DESTINATION = 1.
     *
     * @param elevatorState integer of the state of the elevator whether its idle going to destination or source.
     */
    public void setElevatorState(int elevatorState) {
        this.elevatorState = elevatorState;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public int getElevatorState() {
        return elevatorState;
    }

    public Request getRequest() {
        return request;
    }

    public String stateString() {
        if (getElevatorState() == IDLE) {
            return "IDLE";
        } else if (getElevatorState() == TO_SOURCE) {
            return "TO_SOURCE";
        } else if (getElevatorState() == TO_DESTINATION) {
            return "TO_DESTINATION";
        }
        return null;
    }


    public Elevator() {
        setRequest(null);
        setElevatorState(IDLE);
        setCurrentFloor(1);
    }


}

