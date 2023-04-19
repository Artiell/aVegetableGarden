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
                int i =-1;
                switch (legume.getVariete()) {
                    case salade :
                        i=0;
                        legume = null;
                        break;
                    case carotte :
                        i=1;
                        legume = null;
                        break;
                    case tomate :
                        i=2;
                        legume = null;
                        break;
                }
                simulateurPotager.incrTabInventaireLegume(i);
                simulateurPotager.getMagasin().incrNbPiece(i);
            }

        return legume;
    }
}
