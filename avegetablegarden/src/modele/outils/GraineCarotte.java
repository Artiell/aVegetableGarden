package modele.outils;

import modele.environnement.Legume.varietes.Carotte;
import modele.environnement.Legume.varietes.Legume;

public class GraineCarotte extends Plante{
    public GraineCarotte(){
        super();
    }
    public Legume action (){
        return new Carotte();
    }
}
