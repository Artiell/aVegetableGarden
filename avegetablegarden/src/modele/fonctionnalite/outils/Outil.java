package modele.fonctionnalite.outils;

import modele.SimulateurPotager;
import modele.environnement.Legume.Legume;
import modele.fonctionnalite.Fonctionnalite;

public abstract class Outil extends Fonctionnalite {
    public Outil(){
        super();
    }
    public Fonctionnalite actionUtilisateur (int xx){
        switch (xx) {
            case 0 : return new Pelle();
            case 1 : return new Rateau();
            case 2 : return new Botte();
            case 3 : return new Poubelle();
            default : return null;
        }
    }
    public abstract Legume actionOutil(Legume legume, SimulateurPotager simulateurPotager);
}
