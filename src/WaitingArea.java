import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    private int capacity;
    private Queue<Customer> customerQueue = new LinkedList<>();

    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        this.capacity = size;
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        if(customerQueue.size() > capacity){
            throw new IllegalStateException();
        } else {
            customerQueue.add(customer);
            //TODO notify waitress that customer is added
//            notifyAll();
//            System.out.println("New customer in waiting area: " + customer.getCustomerID());
        }
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        if(customerQueue.isEmpty()){
            throw new IllegalStateException();
        } else {
//            System.out.println("Customer exit waiting area: " + customerQueue.peek().getCustomerID());
            //TODO notify Door that customers leaving
            return customerQueue.remove();
        }

    }

    public synchronized Queue<Customer> getCustomerQueue(){
        return this.customerQueue;
    }

    public synchronized int getCapacity(){
        return this.capacity;
    }

    // Add more methods as you see fit
}
