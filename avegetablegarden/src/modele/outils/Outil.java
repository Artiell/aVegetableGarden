package modele.outils;

import modele.SimulateurPotager;
import modele.environnement.Legume.varietes.Legume;

public abstract class Outil extends Fonctionnalite{
    public Outil(){
        super();
    }
    public abstract Legume actionOutil(Legume legume, SimulateurPotager simulateurPotager);
}
