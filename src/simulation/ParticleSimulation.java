package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler {

    private static final long FRAME_INTERVAL_MILLIS = 40;

    private final ParticlesModel model;
    private final ParticlesView screen;
    private MinPriorityQueue<Event> queue;
    private double clock = 1;

    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
        this.model = m;
        this.screen = new ParticlesView(name, m);
        this.queue = new MinPriorityQueue<>(new Tick(clock));

        for (Collision collision : model.predictAllCollisions(clock)) {
            queue.add(collision);
        }
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

        Event e = queue.remove();

        if (e.isValid()) {
            double tempTime = e.time();
            model.moveParticles(tempTime - clock);
            clock = tempTime;
            e.happen(this);
        }
    }

    @Override
    public void reactTo(Tick tick) {
        try {
            Thread.sleep(FRAME_INTERVAL_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        screen.update();
        queue.add(new Tick(tick.time() + 1));
    }

    @Override
    public void reactTo(Collision c) {
        queue.add(c);
    }
}
