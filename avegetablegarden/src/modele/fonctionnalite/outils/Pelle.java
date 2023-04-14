package modele.fonctionnalite.outils;

import modele.SimulateurPotager;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.Legume;

public class Pelle extends Outil {

    public Pelle (){
        super();
    }
    public Legume actionOutil(Legume legume, SimulateurPotager simulateurPotager){

            if (legume != null && legume.getEtatLegume() == EtatLegume.mature){
                switch (legume.getVariete()) {
                    case salade -> {
                        simulateurPotager.incrTabInventaireLegume(0);
                        legume = null;
                    }
                    case carotte -> {
                        simulateurPotager.incrTabInventaireLegume(1);
                        legume = null;
                    }
                    case tomate -> {
                        simulateurPotager.incrTabInventaireLegume(2);
                        legume = null;
                    }
                }
            }

        return legume;
    }
}
