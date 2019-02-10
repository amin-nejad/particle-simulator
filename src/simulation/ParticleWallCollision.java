package simulation;

public class ParticleWallCollision extends Collision {

    private Wall wall;

    public ParticleWallCollision(Particle p, Wall w, double t) {
        super(t, new Particle[]{p});
        this.wall = w;
    }

    public void happen(ParticleEventHandler h) {
        h.reactTo(this);
    }

    public boolean isValid() {

    }
}
