/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

public class Request {

    private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public void setSourceFloor(int sourceFloor) {
        this.sourceFloor = sourceFloor;
    }

    public void setTimeEntered(int timeEntered) {
        this.timeEntered = timeEntered;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getTimeEntered() {
        return timeEntered;
    }

    public Request(int numberOfFloors) {

        this.sourceFloor = (int) ((Math.random() * numberOfFloors) + 1);
        this.destinationFloor = (int) ((Math.random() * numberOfFloors) + 1);

    }


    public String toString() {

        return new String("(" + getSourceFloor() + ", " +
                getDestinationFloor() + ", " + getTimeEntered());

    }


}
