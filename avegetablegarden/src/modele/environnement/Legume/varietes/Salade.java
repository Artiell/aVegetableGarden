package modele.environnement.Legume.varietes;

import modele.environnement.Legume.EtatLegume;

public class Salade extends Legume {
    @Override
    public Varietes getVariete() {
        return Varietes.salade;
    }

    @Override
    protected void croissance() {
        if (compteur.getSeconde() == 3){
            etatLegume = EtatLegume.mature;
        }
        if (compteur.getSeconde() == 6){
            etatLegume = EtatLegume.pourri;
        }
        System.out.println(compteur.getSeconde());
    }
}
