package modele.fonctionnalite.plantes;

import modele.Magasin;
import modele.environnement.Legume.varietes.Carotte;
import modele.environnement.Legume.Legume;

public class PlanterGraineCarotte extends Planter{
    public PlanterGraineCarotte(){
        super();
    }
    public Legume action (Magasin magasin){
        if (magasin.decrNbPiece(1)){
            return new Carotte();
        }else {
            System.out.println("Pas assez de piece");
            return null;
        }
    }
}
