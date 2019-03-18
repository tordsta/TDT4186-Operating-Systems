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

        //TODO move to SushiBar class, and make it run after all threads are closed. this only works with one waitress.
        SushiBar.write(" ***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
        SushiBar.write("Number of customers: " + SushiBar.customerCounter.get());
        SushiBar.write("Number of takeaway orders: " + SushiBar.takeawayOrders.get());
        SushiBar.write("Number of orders served in bar: " + SushiBar.servedOrders.get());
        SushiBar.write("Total number of orders: " + SushiBar.totalOrders.get());

    }


}

