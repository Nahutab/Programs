/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

import java.util.ArrayList;

public class RequestQueue extends ArrayList {


    ArrayList<Request> requestList = new ArrayList<Request>();


    RequestQueue() {

    }

    public void enqueue(Request insert) {


        requestList.add(insert);

    }

    public void dequeue() throws EmptyQueueException {


        if (requestList.size() == 0) {
            throw new EmptyQueueException("Queue is empty.");
        } else {
            requestList.remove(0);
        }

    }

    public int size() {
        return requestList.size();
    }

    public boolean isEmpty() {
        return requestList.isEmpty();
    }


}
