package simulation;

public abstract class Collision extends AbstractEvent {

    private Particle[] particles;

    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) {
        super(t);
        this.particles = ps;
    }

    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
    public boolean isValid() {

        for (int i = 0; i < particles.length; i++) {
            if
        }
        // TODO implement his method
        return false;
    }

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return particles;
    }
}
