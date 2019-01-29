package simulation;

public abstract class AbstractEvent implements Event {

    /**
     * Constructor for AbstractEvent.
     */
    public AbstractEvent(double time) {
        // TODO implement the constructor 
    }

    /**
     * Returns the time (in ticks) at which this event will occur.
     */
    @Override
    public double time() {
        // TODO implement this method
        return 0;
    }

    /**
     * Compares this object with the specified Event.
     */
    @Override
    public int compareTo(Event that) {
        // TODO implement this method
        return 0;
    }

}
