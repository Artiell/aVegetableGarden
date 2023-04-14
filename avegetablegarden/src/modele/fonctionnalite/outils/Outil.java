package modele.fonctionnalite.outils;

import modele.SimulateurPotager;
import modele.environnement.Legume.Legume;
import modele.fonctionnalite.Fonctionnalite;

public abstract class Outil extends Fonctionnalite {
    public Outil(){
        super();
    }
    public Fonctionnalite actionUtilisateur (int xx){
        return switch (xx) {
            case 0 -> new Pelle();
            case 1 -> new Rateau();
            case 2 -> new Botte();
            case 3 -> new Poubelle();
            default -> null;
        };
    }
    public abstract Legume actionOutil(Legume legume, SimulateurPotager simulateurPotager);
}
