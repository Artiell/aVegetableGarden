package modele.fonctionnalite.plantes;

import modele.Magasin;
import modele.environnement.Legume.Legume;
import modele.environnement.Legume.varietes.Tomate;

public class PlanterGraineTomate extends Planter{
    public PlanterGraineTomate(){
        super();
    }
    public Legume action (Magasin magasin){
        if (magasin.decrNbPiece(2)){
            return new Tomate();
        }else {
            System.out.println("Pas assez de piece");
            return null;
        }
    }
}
