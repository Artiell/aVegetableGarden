package modele.fonctionnalite.plantes;

import modele.Magasin;
import modele.environnement.Legume.Legume;
import modele.environnement.Legume.varietes.Salade;

public class GraineSalade extends Plante{
    public GraineSalade(){
        super();
    }
    public Legume action (Magasin magasin){
        if (magasin.decrNbPiece(0)){
            return new Salade();
        }else {
            System.out.println("Pas assez de piece");
            return null;
        }
    }
}
