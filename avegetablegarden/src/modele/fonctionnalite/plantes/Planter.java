package modele.fonctionnalite.plantes;

import modele.Magasin;
import modele.environnement.Legume.Legume;
import modele.fonctionnalite.Fonctionnalite;

public abstract class Planter extends Fonctionnalite {
    public Planter (){
        super();
    }
    public Fonctionnalite actionUtilisateur (int xx){
        return switch (xx) {
            case 0 -> new PlanterGraineSalade();
            case 1 -> new PlanterGraineCarotte();
            case 2 -> new PlanterGraineTomate();
            default -> null;
        };
    }
    public abstract Legume action(Magasin magasin);
}
