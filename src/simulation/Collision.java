package simulation;

public abstract class Collision extends AbstractEvent {

    private Particle[] particles;
    private int[] initialCollisions;

    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) {
        super(t);
        this.particles = ps;
        this.initialCollisions = new int[ps.length];

        for (int i = 0; i < particles.length; i++) {
            initialCollisions[i] = particles[i].collisions();
        }
    }

    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
    public boolean isValid() {

        for (int i = 0; i < particles.length; i++) {
            if (particles[i].collisions() != initialCollisions[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return particles;
    }
}
