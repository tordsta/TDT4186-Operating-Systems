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
                } catch (Exception e) {
                    try{
//                        System.out.println("Waitress waiting, no more customers in queue");
                        waitingArea.wait();
                    } catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
            }

            //Timings for customers
            try {
                SushiBar.write("Customer " + customerServed.getCustomerID() + " is now ordering.");
                Thread.sleep(SushiBar.waitressWait); //TODO implement multiple orders feature
                SushiBar.totalOrders.increment();
            } catch (Exception orderException) {
                orderException.printStackTrace();
            }
            try {
                SushiBar.write("Customer " + customerServed.getCustomerID() + " is now eating.");
                Thread.sleep(SushiBar.customerWait);
                SushiBar.write("Customer " + customerServed.getCustomerID() + " is now leaving.");
            } catch (Exception eatingException) {
                eatingException.printStackTrace();
            }
        }
    }


}

