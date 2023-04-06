package modele.environnement.Legume.varietes;

import modele.TypeSol;
import modele.environnement.Legume.EtatLegume;

public class Salade extends Legume {
    @Override
    public Varietes getVariete() {
        return Varietes.salade;
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
                if (this.getTempsDeVieActuel() == 2){
                    this.setEtatLegume(EtatLegume.jeune);
                }
                if (this.getTempsDeVieActuel() == 8){
                    this.setEtatLegume(EtatLegume.pourri);
                }
                break;
                // A changer
            case sec:
                if (this.getTempsDeVieActuel() == 10){
                    this.setEtatLegume(EtatLegume.jeune);
                }
                if (this.getTempsDeVieActuel() == 20){
                    this.setEtatLegume(EtatLegume.mature);
                }
                if (this.getTempsDeVieActuel() == 25){
                    this.setEtatLegume(EtatLegume.pourri);
                }
                break;
        }
    }
}
