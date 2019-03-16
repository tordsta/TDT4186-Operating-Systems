/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

    WaitingArea waitingArea;
    Customer customerServed;

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
    public synchronized void run() {
        // TODO Implement required functionality

        while(true){
            if(!waitingArea.getCustomerQueue().isEmpty()){
                this.customerServed = waitingArea.next();

                System.out.println("Customer " + customerServed.getCustomerID() + " is now eating.");
                // TODO implement waitress order time
                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
                // TODO implement customer eat time
                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Customer " + customerServed.getCustomerID() + " is now leaving.");

            } else {
                System.out.println("the waitress is waiting");
                try {
                    wait();
                } catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}

