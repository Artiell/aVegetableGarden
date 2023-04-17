package modele.environnement.Legume;

import modele.SimulateurTemps;
import modele.TypeSol;
import modele.environnement.Legume.varietes.Varietes;

public abstract class Legume {
    private int tempsDeVieActuel;
    private EtatLegume etatLegume;

    private int dureePourri; // durée durant laquelle le légume est pourri
    private int tPourri; // moment t des secondes écoulés dans le simmulateurTemps à laquelle le légume est pourri

    private final int naissance; //nb de secondes à laquelle le légume est créer

    public Legume() {

        this.naissance = SimulateurTemps.getSimuTemps().getS();
        this.tempsDeVieActuel = 0;
        this.etatLegume = EtatLegume.germe;
        this.tPourri = 0;
        this.dureePourri = 0;
    }

    public int getDureePourri() {
        return dureePourri;
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

    public void nextStep(TypeSol sol) {
        croissance(sol);
    }

    public void updatePourri(){
        if(this.tPourri == 0 && this.getEtatLegume() == EtatLegume.pourri){
            this.tPourri = SimulateurTemps.getSimuTemps().getS();
        }
        if (this.getEtatLegume() == EtatLegume.pourri){
            this.dureePourri = SimulateurTemps.getSimuTemps().getS() - this.tPourri;
        }
    }

    public abstract Varietes getVariete();

    protected abstract void croissance(TypeSol sol); // définir selon les conditions
}
