/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation No. 3
 */
import java.util.LinkedList;

public class Restaurant extends LinkedList<Customer> {

    int counter =0;


    public Restaurant() {
        super();
    }

    public void enqueue(Customer c) {
        super.addLast(c);
        counter++;
    }

    public Customer dequeue() {
        counter--;
        return super.removeFirst();
    }

    public Customer peek() {
      return super.peek();
    }

    public int size(){
        return super.size();
    }

    public boolean isEmpty(){
        return super.isEmpty();
    }

    public String toString(){
        String output  = new String("{");
        for(int i =0; i < counter; i++){
          output =  output + super.get(i).toString();
        }
        return output +"}";
    }

}
