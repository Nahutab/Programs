/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

public class BooleanSource {

    private double probability;

    public BooleanSource(double p) throws InvalidProbabilityException {
        if (p < 0.0 || p > 1.0){
            throw new InvalidProbabilityException("Invalid probability.");

        } this.probability = p;
    }


    public boolean requestArrived(){
        return (Math.random() < probability);
    }



}
