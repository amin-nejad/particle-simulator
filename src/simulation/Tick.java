package simulation;

public class Tick extends AbstractEvent {

    public Tick(double time) {
        super(time);
    }

    public void happen(ParticleEventHandler h) {
        h.reactTo(this);
    }

    public boolean isValid() {
        return true;
    }

}
