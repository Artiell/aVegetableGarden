package modele.environnement.Legume.varietes;

public abstract class Legume {
    public abstract Varietes getVariete();
    public void nextStep() {
        croissance();
    }

    protected abstract void croissance(); // définir selon les conditions
}
