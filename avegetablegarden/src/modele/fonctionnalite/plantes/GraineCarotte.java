package modele.fonctionnalite.plantes;

import modele.environnement.Legume.varietes.Carotte;
import modele.environnement.Legume.Legume;

public class GraineCarotte extends Plante{
    public GraineCarotte(){
        super();
    }
    public Legume action (){
        return new Carotte();
    }
}
