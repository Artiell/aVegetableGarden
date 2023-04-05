package modele.environnement.Legume.varietes;

import modele.environnement.Legume.EtatLegume;

public class Tomate extends Legume{
    @Override
    public Varietes getVariete() {
        return Varietes.tomate;
    }

    @Override
    protected void croissance() {
        if (this.getTempsDeVieActuel() == 3){
            this.setEtatLegume(EtatLegume.jeune);
        }
        if (this.getTempsDeVieActuel() == 6){
            this.setEtatLegume(EtatLegume.mature);
        }
        if (this.getTempsDeVieActuel() == 20){
            this.setEtatLegume(EtatLegume.pourri);
        }
    }
}
