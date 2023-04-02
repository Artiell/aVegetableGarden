package modele.environnement.Legume.varietes;

import modele.environnement.Legume.EtatLegume;

public class Carotte extends Legume{

    @Override
    public Varietes getVariete() {
        return Varietes.carotte;
    }

    @Override
    protected void croissance() {
        if (compteur.getSeconde() == 3){
            etatLegume = EtatLegume.jeune;
        }
        if (compteur.getSeconde() == 6){
            etatLegume = EtatLegume.mature;
        }
        if (compteur.getSeconde() == 20){
            etatLegume = EtatLegume.pourri;
        }
    }
}
