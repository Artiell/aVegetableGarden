package modele.environnement.Legume.varietes;

import modele.TypeSol;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.Legume;

public class Carotte extends Legume {

    @Override
    public Varietes getVariete() {
        return Varietes.carotte;
    }

    @Override
    protected void croissance(TypeSol sol) {

        switch (sol) {
            case normal -> {
                if (this.getTempsDeVieActuel() >= 30) {
                    this.setEtatLegume(EtatLegume.jeune);
                }
                if (this.getTempsDeVieActuel() >= 70) {
                    this.setEtatLegume(EtatLegume.mature);
                }
                if (this.getTempsDeVieActuel() >= 120) {
                    this.setEtatLegume(EtatLegume.pourri);
                }
            }
            case humide -> {
                if (this.getTempsDeVieActuel() >= 4) {
                    this.setEtatLegume(EtatLegume.jeune);
                }
                if (this.getTempsDeVieActuel() >= 6) {
                    this.setEtatLegume(EtatLegume.mature);
                }
                if (this.getTempsDeVieActuel() >= 20) {
                    this.setEtatLegume(EtatLegume.pourri);
                }
            }
            // A changer
            case sec -> {
                if (this.getTempsDeVieActuel() >= 3) {
                    this.setEtatLegume(EtatLegume.jeune);
                }
                if (this.getTempsDeVieActuel() >= 13) {
                    this.setEtatLegume(EtatLegume.mature);
                }
                if (this.getTempsDeVieActuel() >= 20) {
                    this.setEtatLegume(EtatLegume.pourri);
                }
            }
        }
        this.updatePourri();

    }
}
