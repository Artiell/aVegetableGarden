package modele.outils;

public abstract class Fonctionnalite {
    protected boolean actif;
    public Fonctionnalite (){
        actif = false;
    }
    public boolean getActif(){
        return actif;
    }
    public void activer (){
        actif = true;
    }
    public void desactiver(){
        actif = false;
    }

}
