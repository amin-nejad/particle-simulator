package simulation;

public class TwoParticleCollision extends Collision {

    public TwoParticleCollision(Particle p1, Particle p2, double t) {
        super(t, new Particle[]{p1, p2});
    }

    public void happen(ParticleEventHandler h) {
        Particle.collide(getParticles()[0], getParticles()[1]);
        h.reactTo(this);
    }

}
