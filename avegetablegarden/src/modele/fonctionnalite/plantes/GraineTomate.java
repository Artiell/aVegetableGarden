package modele.fonctionnalite.plantes;

import modele.environnement.Legume.Legume;
import modele.environnement.Legume.varietes.Tomate;

public class GraineTomate extends Plante{
    public GraineTomate(){
        super();
    }
    public Legume action (){
        return new Tomate();
    }
}
