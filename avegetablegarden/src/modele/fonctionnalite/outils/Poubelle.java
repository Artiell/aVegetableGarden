package modele.fonctionnalite.outils;

import modele.SimulateurPotager;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.Legume;

public class Poubelle extends Outil{
    public Poubelle (){
        super();
    }
    public Legume actionOutil(Legume legume, SimulateurPotager simulateurPotager){
            System.out.println("Poubelle");
            if(legume != null && legume.getEtatLegume() == EtatLegume.pourri){
                legume = null;
            }
        return legume;
    }
}
