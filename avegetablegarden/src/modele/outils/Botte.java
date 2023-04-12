package modele.outils;

import modele.SimulateurPotager;
import modele.environnement.Button.ButtonOutil;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.varietes.Legume;

public class Botte extends Outil{
    public Botte (){
        super();
    }
    public Legume actionOutil(Legume legume, SimulateurPotager simulateurPotager){

            if(legume != null && legume.getEtatLegume() != EtatLegume.pourri){
                legume = null;
            }

        return legume;
    }
}
