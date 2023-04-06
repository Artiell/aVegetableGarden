package modele.environnement.Legume.varietes;

import modele.TypeSol;
import modele.environnement.Legume.EtatLegume;

public class Tomate extends Legume{
    @Override
    public Varietes getVariete() {
        return Varietes.tomate;
    }

    @Override
    protected void croissance(TypeSol sol) {

        switch (sol){
            case normal :
                if (this.getTempsDeVieActuel() == 3){
                    this.setEtatLegume(EtatLegume.jeune);
                }
                if (this.getTempsDeVieActuel() == 6){
                    this.setEtatLegume(EtatLegume.mature);
                }
                if (this.getTempsDeVieActuel() == 20){
                    this.setEtatLegume(EtatLegume.pourri);
                }
                break;
            case humide:
                if (this.getTempsDeVieActuel() == 4){
                    this.setEtatLegume(EtatLegume.jeune);
                }
                if (this.getTempsDeVieActuel() == 6){
                    this.setEtatLegume(EtatLegume.mature);
                }
                if (this.getTempsDeVieActuel() == 20){
                    this.setEtatLegume(EtatLegume.pourri);
                }
                break;
            // A changer
            case sec:
                if (this.getTempsDeVieActuel() == 3){
                    this.setEtatLegume(EtatLegume.jeune);
                }
                if (this.getTempsDeVieActuel() == 13){
                    this.setEtatLegume(EtatLegume.mature);
                }
                if (this.getTempsDeVieActuel() == 20){
                    this.setEtatLegume(EtatLegume.pourri);
                }
                break;
        }
    }
}
