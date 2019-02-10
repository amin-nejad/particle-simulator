package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    private final ParticlesModel          model;
    private final ParticlesView           screen;
    private MinPriorityQueue<Event> queue;
    
    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
        // TODO implement constructor
    }

    /**
     * Runs the simulation.
     */
    @Override
    public void run() {
        try {
            SwingUtilities.invokeAndWait(screen);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // TODO complete implementing this method
    }

    // not sure about this
    public static void main(String[] args) {
        ParticleSimulation simulation = new ParticleSimulation();
        simulation.run();
    }

}
