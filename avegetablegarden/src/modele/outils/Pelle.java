package modele.outils;

import modele.SimulateurPotager;
import modele.environnement.Button.ButtonOutil;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.varietes.Legume;

public class Pelle extends Outil {

    public Pelle (){
        super();
        actif = true;
    }
    public Legume actionOutil(Legume legume, SimulateurPotager simulateurPotager){
        if (actif){
            System.out.println("PELLLE");
            if (legume != null && legume.getEtatLegume() == EtatLegume.mature){
                switch (legume.getVariete()){

                    case salade : simulateurPotager.incrTabInventaireLegume(0);
                        legume = null;
                        break;
                    case carotte: simulateurPotager.incrTabInventaireLegume(1);
                        legume = null;
                        break;
                    case tomate: simulateurPotager.incrTabInventaireLegume(2);
                        legume = null;
                        break;

                }
            }
        }
        return legume;
    }
}
