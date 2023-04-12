package modele.fonctionnalite.plantes;

import modele.environnement.Legume.Legume;
import modele.environnement.Legume.varietes.Salade;

public class GraineSalade extends Plante{
    public GraineSalade(){
        super();
    }
    public Legume action (){
        return new Salade();
    }
}
