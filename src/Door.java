import javax.management.monitor.Monitor;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {

    private final WaitingArea waitingArea;

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the waiting area
     */
    @Override
    public void run() {
        while(SushiBar.isOpen) { //handles opening and closing times
            Customer customer = new Customer();
            //customer enters waiting area
            synchronized (waitingArea){
                try{
                    waitingArea.enter(customer);
                    waitingArea.notify();
                    System.out.println("Customer " + customer.getCustomerID() + " is now waiting.");
                } catch (Exception e){
                    try{
                        System.out.println("Door closed, no more space in waiting room");
                        waitingArea.wait();
                    } catch (InterruptedException ie){
                        ie.printStackTrace();
                    }

                }
            }
        }
    }
    // Add more methods as you see fit
}
