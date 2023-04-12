package modele.outils;

import modele.environnement.Legume.varietes.*;

public abstract class Plante extends Fonctionnalite {
    public Plante (){
        super();
    }
    public abstract Legume action ();
}
