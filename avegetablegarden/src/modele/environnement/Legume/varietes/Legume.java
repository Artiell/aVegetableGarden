package modele.environnement.Legume.varietes;

import modele.SimulateurTemps;
import modele.TypeSol;
import modele.environnement.Legume.EtatLegume;

public abstract class Legume {
    private int tempsDeVieActuel;
    private EtatLegume etatLegume;
    private int Naissance; //nb de secondes à laquelle le légume est créer

    public Legume() {

        this.Naissance = SimulateurTemps.getSimuTemps().getS();
        this.tempsDeVieActuel = 0;
        this.etatLegume = EtatLegume.germe;

    }
    public void vieillir (){
        this.tempsDeVieActuel = SimulateurTemps.getSimuTemps().getS() - Naissance;
    }
    public EtatLegume getEtatLegume() {
        return etatLegume;
    }

    public void setEtatLegume(EtatLegume etatLegume) {
        this.etatLegume = etatLegume;
    }

    public int getTempsDeVieActuel() {
        return tempsDeVieActuel;
    }

    public abstract Varietes getVariete();

    public void nextStep(TypeSol sol) {
        croissance(sol);
    }

    protected abstract void croissance(TypeSol sol); // définir selon les conditions
}
