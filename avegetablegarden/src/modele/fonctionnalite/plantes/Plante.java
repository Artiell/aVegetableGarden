package modele.fonctionnalite.plantes;

import modele.environnement.Legume.Legume;
import modele.fonctionnalite.Fonctionnalite;

public abstract class Plante extends Fonctionnalite {
    public Plante (){
        super();
    }
    public Fonctionnalite actionUtilisateur (int xx){
        switch (xx){
            case 0: return new GraineSalade();
            case 1: return new GraineCarotte();
            case 2: return new GraineTomate();
            default: return null;
        }
    }
    public abstract Legume action ();
}