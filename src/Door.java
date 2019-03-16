import javax.management.monitor.Monitor;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {

    private WaitingArea waitingArea;

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
    public synchronized void run() {
        while(true) {
            Customer customer = new Customer();

            //customer enters waiting area
            if(waitingArea.getCustomerQueue().size() < waitingArea.getCapacity()){
                waitingArea.enter(customer);
                System.out.println("Customer " + customer.getCustomerID() + " is now waiting.");
            } else {
                try {
                    System.out.println("wait block start");
                    wait();
                    System.out.println("wait block end");
                } catch (InterruptedException e) {
                    System.out.println("the wait failed");
                    e.printStackTrace();
                }
            }


            // TODO make random and cleanup. move up in other try catch
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //TimeUnit.SECONDS.sleep(1);
        }


    }

    // Add more methods as you see fit
}
