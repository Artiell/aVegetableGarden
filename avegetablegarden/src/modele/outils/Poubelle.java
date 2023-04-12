package modele.outils;

import modele.SimulateurPotager;
import modele.environnement.Button.ButtonOutil;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.varietes.Legume;

public class Poubelle extends Outil{
    public Poubelle (){
        super();
    }
    public Legume actionOutil(Legume legume, SimulateurPotager simulateurPotager){
        if (actif){
            System.out.println("Poubelle");
            if(legume != null && legume.getEtatLegume() == EtatLegume.pourri){
                legume = null;
            }
        }
        return legume;
    }
}
