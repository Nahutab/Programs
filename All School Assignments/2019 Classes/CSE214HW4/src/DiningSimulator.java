/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation No. 3
 */
import java.util.Collection;

import java.util.Scanner;

public class DiningSimulator {

    Collection<Restaurant> restaurants;
    private int chefs = 0;
    private int duration = 0;
    private double arrivalProb = 0.0;
    private int maxCustomerSize = 0;
    private int numRestaurants = 0;
    private int customersLost = 0;
    private int totalServiceTime = 0;
    private int customersServed = 0;
    private int profit = 0;

    public void setChefs(int chefs) {
        this.chefs = chefs;
    }

    public int getChefs() {
        return chefs;
    }

    public int getNumRestaurants() {
        return numRestaurants;
    }

    public void setArrivalProb(double arrivalProb) {
        this.arrivalProb = arrivalProb;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCustomersLost(int customersLost) {
        this.customersLost = customersLost;
    }

    public void setCustomersServed(int customersServed) {
        this.customersServed = customersServed;
    }

    public void setMaxCustomerSize(int maxCustomerSize) {
        this.maxCustomerSize = maxCustomerSize;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setTotalServiceTime(int totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }

    public double getArrivalProb() {
        return arrivalProb;
    }

    public int getCustomersLost() {
        return customersLost;
    }

    public int getCustomersServed() {
        return customersServed;
    }

    public int getDuration() {
        return duration;
    }

    public int getMaxCustomerSize() {
        return maxCustomerSize;
    }

    public int getProfit() {
        return profit;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }
    public String foodRandomizer(){

        int num = randInt(0,100);

        if(num <= 20){
            return "CB";
        }
        else if (num > 20 && num <= 40){
            return "S";
        }
        else if(num>40 && num <= 60){
            return "GC";
        }
        else if(num>60 && num<= 80){
            return "CT";
        }
        else return "CW";
    }


    public void setNumRestaurants(int numRestaurants) {
        this.numRestaurants = numRestaurants;
    }

    public static void main(String[] args){

        DiningSimulator ds = new DiningSimulator();
        Scanner stdin = new Scanner(System.in);

        int input1 = 0;
        int input2 = 0;
        double input3 = 0;
        int input4 = 0;
        int input5 = 0;

        System.out.println("Starting Simulator...");
        System.out.println("Enter the number of restaurants: ");
        input1 = stdin.nextInt();
        System.out.println("Enter the maximum number of customers a restaurant can serve: ");
        input2 = stdin.nextInt();
        System.out.println("Enter the arrival probability of a customer: ");
        input3 = stdin.nextDouble();
        if(input3 > 1){
            try {
                throw new ProbabilityException("Invalid Input");
            } catch (ProbabilityException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Enter the number of chefs: ");
        input4 = stdin.nextInt();
        System.out.println("Enter the number of simulation units: ");
        input5 = stdin.nextInt();

        ds.setNumRestaurants(input1);
        ds.setMaxCustomerSize(input2);
        ds.setArrivalProb(input3);
        ds.setChefs(input4);
        ds.setDuration(input5);

        int simTime = ds.getDuration();
        Restaurant[] restaurants = new Restaurant[ds.getNumRestaurants()];
        Restaurant tempEntry = new Restaurant();





        for(int x = 0; x < ds.getNumRestaurants(); x++){
            restaurants[x] = new Restaurant();
        }


        for(int i = 1; i <= simTime; i++){
            System.out.println("Time: " + i);

            for(int j =0; j < ds.getNumRestaurants(); j++) {
                for(int z = 0; z < 3; z++){
                    if (ds.randInt(0, 100) <= (ds.getArrivalProb() * 100)) {
                        Customer.setTotalCustomers(Customer.getTotalCustomers()+1);
                        Customer newCustomer = new Customer(ds.foodRandomizer(), Customer.getTotalCustomers());
                        System.out.println("Customer #" + newCustomer.getOrderNumber() + " has entered Restaurant " + (j+1));
                        tempEntry.enqueue(newCustomer);



                    }


                }

            }

            for(int x = 0; x < ds.getNumRestaurants(); x++) {
                if(!tempEntry.isEmpty()){
                    if(restaurants[x].size() < ds.maxCustomerSize){
                   Customer dequedCustomer = tempEntry.dequeue();
                    restaurants[x].enqueue(dequedCustomer);
                    System.out.println("Customer: " + dequedCustomer.getOrderNumber() +" has been seated.");
                    }
                    else{
                        System.out.println("Customer #" + tempEntry.dequeue().getOrderNumber() +
                                "  cannot be seated! They have left the restaurant." );
                        ds.setCustomersLost(ds.getCustomersLost()+1);

                    }
                }

            }

            for(int c = 0; c < ds.getNumRestaurants(); c++){
                System.out.println("R" + (c+1) + ":" +restaurants[c].toString());
            }

        for(int a = 0; a < ds.getNumRestaurants(); a++){

            for(Customer c: restaurants[a]){

                if(c.getTimeToServe() == 0){
                    if(c.getFood().equals("S")){
                        ds.profit += 25;
                    }
                    else if(c.getFood().equals("CW")){
                        ds.profit += 30;
                    }

                    else if(c.getFood().equals("CB")){
                        ds.profit += 15;
                    }

                    else if(c.getFood().equals("CT")){
                        ds.profit += 10;
                    }

                    else if(c.getFood().equals("GC")){
                        ds.profit += 10;
                    }


                }
                else{
                    c.setTimeToServe(c.getTimeToServe() - 5);
                }
            }




            }
        }







        System.out.println("The total profit was: $" + ds.profit);


               }







    public double simulate(){






return 0;

    }


    private int randInt(int minVal, int maxVal){
        int range = maxVal -minVal + 1;
        return (int)(Math.random() * range) + minVal;
    }


}



