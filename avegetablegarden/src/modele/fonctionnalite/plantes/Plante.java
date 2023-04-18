package modele.fonctionnalite.plantes;

import modele.Magasin;
import modele.environnement.Legume.Legume;
import modele.fonctionnalite.Fonctionnalite;

public abstract class Plante extends Fonctionnalite {
    public Plante (){
        super();
    }
    public Fonctionnalite actionUtilisateur (int xx){
        return switch (xx) {
            case 0 -> new GraineSalade();
            case 1 -> new GraineCarotte();
            case 2 -> new GraineTomate();
            default -> null;
        };
    }
    public abstract Legume action (Magasin magasin);
}
