package modele.environnement.Legume.varietes;

import modele.environnement.Legume.EtatLegume;

public abstract class Legume {

    protected float vitesseCroisssance;
    protected int tempsDeVie;
    public void vieillir (int d){
        tempsDeVie += d;
    }
    public EtatLegume getEtatLegume() {
        return etatLegume;
    }
    public void setEtatLegume(EtatLegume etatLegume) {
        this.etatLegume = etatLegume;
    }
    private EtatLegume etatLegume;


    protected Legume() {
        this.etatLegume = EtatLegume.germe;
    }

    public abstract Varietes getVariete();
    public void nextStep() {
        croissance();
    }


    protected abstract void croissance(); // définir selon les conditions
}
