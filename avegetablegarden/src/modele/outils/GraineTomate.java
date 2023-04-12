package modele.outils;

import modele.environnement.Legume.varietes.Carotte;
import modele.environnement.Legume.varietes.Legume;
import modele.environnement.Legume.varietes.Tomate;

public class GraineTomate extends Plante{
    public GraineTomate(){
        super();
    }
    public Legume action (){
        return new Tomate();
    }
}
