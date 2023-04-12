package modele.outils;

import modele.environnement.Legume.varietes.Carotte;
import modele.environnement.Legume.varietes.Legume;
import modele.environnement.Legume.varietes.Salade;

public class GraineSalade extends Plante{
    public GraineSalade(){
        super();
    }
    public Legume action (){
        return new Salade();
    }
}
