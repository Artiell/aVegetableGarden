package modele.fonctionnalite.plantes;

import modele.Magasin;
import modele.environnement.Legume.Legume;
import modele.fonctionnalite.Fonctionnalite;

public abstract class Planter extends Fonctionnalite {
    public Planter (){
        super();
    }
    public Fonctionnalite actionUtilisateur (int xx){
        switch (xx) {
            case 0 : return new PlanterGraineSalade();
            case 1 : return new PlanterGraineCarotte();
            case 2 : return new PlanterGraineTomate();
            default : return null;
        }
    }
    public abstract Legume action(Magasin magasin);
}
