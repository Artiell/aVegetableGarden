package modele.environnement.Legume.varietes;

import modele.environnement.Legume.EtatLegume;
import modele.outils.Compteur;

import java.time.LocalTime;

public abstract class Legume {

    protected float vitesseCroisssance; //pas utilisé encore
    protected int tempsDeVieMax;
    protected Compteur compteur;
    protected EtatLegume etatLegume; //pas utilisé encore

    private int Naissance; //nb de secondes à laquelle le légume est créer
    protected Legume() {
        LocalTime time = java.time.LocalTime.now();
        Naissance = time.getSecond();
        tempsDeVieMax = 10;
        compteur = new Compteur(tempsDeVieMax);
        compteur.demarre();
        this.etatLegume = EtatLegume.germe;
    }
    public void vieillir (){
        compteur.incremente(Naissance);
        //tempsDeVieMax += d;
    }
    public EtatLegume getEtatLegume() {
        return etatLegume;
    }

    public void setEtatLegume(EtatLegume etatLegume) {
        this.etatLegume = etatLegume;
    }

    public Compteur getCompteur (){ return compteur; }

    public abstract Varietes getVariete();
    public void nextStep() {
        croissance();
    }


    protected abstract void croissance(); // définir selon les conditions
}
