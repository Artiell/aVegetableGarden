package modele.environnement.Legume;

import modele.Ordonnanceur;
import modele.SimulateurTemps;
import modele.TypeSol;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.varietes.Varietes;

public abstract class Legume {
    private int tempsDeVieActuel;
    private EtatLegume etatLegume;

    private int dureePourri; // durée durant laquelle le légume est pourri
    public int getDureePourri() {
        return dureePourri;
    }

    private int tPourri; // moment t de l'ordonnanceur à laquelle le légume est pourri

    private int naissance; //nb de secondes à laquelle le légume est créer
    public int getNaissance() {
        return naissance;
    }

    public Legume() {

        this.naissance = SimulateurTemps.getSimuTemps().getS();
        this.tempsDeVieActuel = 0;
        this.etatLegume = EtatLegume.germe;
        this.tPourri = 0;
        this.dureePourri = 0;
    }
    public void vieillir (){
        this.tempsDeVieActuel = SimulateurTemps.getSimuTemps().getS() - this.naissance;
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

    public void updatePourri(){
        if(this.tPourri == 0 && this.getEtatLegume() == EtatLegume.pourri){
            this.tPourri = SimulateurTemps.getSimuTemps().getS();
        }
        if (this.getEtatLegume() == EtatLegume.pourri){
            this.dureePourri = SimulateurTemps.getSimuTemps().getS() - this.tPourri;
        }
    }
}
