package modele.environnement.Legume.varietes;

import modele.SimulateurTemps;
import modele.environnement.Legume.EtatLegume;
import modele.outils.Compteur;

import java.time.LocalTime;

public abstract class Legume {

    public void setVitesseCroisssance(float vitesseCroisssance) {
        this.vitesseCroisssance = vitesseCroisssance;
    }

    private float vitesseCroisssance; //pas utilisé encore
    private int tempsDeVieMax;
    private int tempsDeVieActuel;
    private EtatLegume etatLegume; //pas utilisé encore
    private int Naissance; //nb de secondes à laquelle le légume est créer

    public float getVitesseCroisssance() {
        return vitesseCroisssance;
    }

    public int getNaissance() {
        return Naissance;
    }

    public Legume() {

        this.Naissance = SimulateurTemps.getSimuTemps().getS();
        this.tempsDeVieMax = 25;
        this.tempsDeVieActuel = 0;
        this.etatLegume = EtatLegume.germe;
        this.vitesseCroisssance = 1;

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

    public void nextStep() {
        croissance();
    }

    protected abstract void croissance(); // définir selon les conditions
}
