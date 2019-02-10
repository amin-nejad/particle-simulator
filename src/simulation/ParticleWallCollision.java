package simulation;

public class ParticleWallCollision extends Collision {

    private Wall wall;

    public ParticleWallCollision(Particle p, Wall w, double t) {
        super(t, new Particle[]{p});
        this.wall = w;
    }

    public void happen(ParticleEventHandler h) {
        Particle.collide(this.getParticles()[0], wall);
        h.reactTo(this);
    }

}
