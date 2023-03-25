package modele.outils;

public abstract class Outil implements Runnable {
    public boolean estUtilise;
    public Outil (){
        estUtilise = false;
    }
    public abstract void actionUtilisateur();
}
