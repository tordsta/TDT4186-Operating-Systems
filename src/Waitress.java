/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

    private final WaitingArea waitingArea;
    private Customer customerServed;

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        while(SushiBar.isOpen || !waitingArea.getCustomerQueue().isEmpty()){ //runs while sushibar is open and while there are customers left
            synchronized (waitingArea) {
                try{
                    this.customerServed = waitingArea.next();
                    waitingArea.notify();

                    //Timings for customers
                    System.out.println("Customer " + customerServed.getCustomerID() + " is now ordering.");
                    // TODO implement waitress order time
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Customer " + customerServed.getCustomerID() + " is now eating.");
                    // TODO implement customer eat time
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Customer " + customerServed.getCustomerID() + " is now leaving.");

                } catch (Exception e) {
                    try{
                        System.out.println("Waitress waiting, no more customers in queue");
                        waitingArea.wait();
                    } catch (InterruptedException error){
                        e.printStackTrace();
                    }
                }


            }
        }
    }


}

