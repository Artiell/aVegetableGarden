package modele;

import modele.environnement.Legume.varietes.Varietes;

public class Magasin {
    private final int[] tabPrix;
    private int[] tabRecompense;
    private int[] tabMalus;
    private int nbPiece;
    private String message;

    public Magasin (){
        tabPrix = new int[Varietes.values().length];
        tabMalus = new int[Varietes.values().length+1];
        initialisationPrix();
        initialisationRecompense();
        initialisationMalus();
        nbPiece = 15;
        message = null;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg){
        message = msg;
    }
    public void resetMagasin (){
        nbPiece=15;
        message = null;
    }
    private void initialisationPrix(){
        tabPrix[0] = 3; //prix salade
        tabPrix[1] = 5; //prix carotte
        tabPrix[2] = 8; //prix tomate
    }
    private void initialisationMalus(){
        tabMalus[0] = 3; //malus salade
        tabMalus[1] = 5; //malus carotte
        tabMalus[2] = 8; //malus tomate
        tabMalus[3] = 2; //malus suppression buisson
    }
    private void initialisationRecompense(){
        tabRecompense = new int[Varietes.values().length];
        tabRecompense[0] = 6;
        tabRecompense[1] = 9;
        tabRecompense[2] = 12;
    }
    public int[] getTabPrix() {
        return tabPrix;
    }

    public int getNbPiece() {
        return nbPiece;
    }

    public void incrNbPiece (int ind){
        nbPiece+= tabRecompense[ind];
        message = " + "+tabRecompense[ind];
    }
    public boolean decrNbPiece (int ind){
        if (nbPiece-tabPrix[ind] < 0){
            message = "Pas assez de pièces";
            return false;
        }else {
            message = " - "+tabPrix[ind];
            nbPiece-= tabPrix[ind];
            return true;
        }
    }
    public void updateMalus(int ind){
        nbPiece -= tabMalus[ind];
        message = " MALUS : - "+ tabMalus[ind];
    }
}
