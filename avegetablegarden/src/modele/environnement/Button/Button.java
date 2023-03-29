package modele.environnement.Button;

public abstract class Button{
    protected boolean estActif;
    public Button (){
        estActif = false;
    }
    public boolean getActivite (){
        return estActif;
    }
    public void setActivite (boolean b){
        estActif = b;
    }
    public abstract void actionUtilisateur();
}
