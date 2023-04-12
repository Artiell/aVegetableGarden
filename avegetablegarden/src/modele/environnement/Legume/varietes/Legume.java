package modele.environnement.Legume.varietes;

import modele.SimulateurTemps;
import modele.TypeSol;
import modele.environnement.Legume.EtatLegume;

import java.time.LocalTime;

public abstract class Legume {

    private int tempsDeVieActuel;
    private EtatLegume etatLegume; //pas utilisé encore
    private int Naissance; //nb de secondes à laquelle le légume est créer

    public void setTempsPourri(int tempsPourri) {
        this.tempsPourri = tempsPourri;
    }

    private int tempsPourri;

    public int getTempsPourri() {
        return tempsPourri;
    }

    public int getNaissance() {
        return Naissance;
    }

    public Legume() {

        this.Naissance = SimulateurTemps.getSimuTemps().getS();
        this.tempsDeVieActuel = 0;
        this.etatLegume = EtatLegume.germe;
        this.tempsPourri = 0;

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