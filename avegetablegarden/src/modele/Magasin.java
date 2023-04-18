package modele;

import modele.environnement.Legume.varietes.Varietes;

public class Magasin {
    private int[] tabPrix;
    private int[] tabRecompense;
    private int[] tabMalus;
    private int nbPiece;

    public Magasin (){
        tabPrix = new int[Varietes.values().length];
        initialisationPrix();
        initialisationRecompense();
        initialisationMalus();
        nbPiece = 15;

    }
    public void resetNbPiece (){
        nbPiece=15;
    }
    private void initialisationPrix(){
        tabPrix[0] = 3; //prix salade
        tabPrix[1] = 5; //prix carotte
        tabPrix[2] = 8; //prix tomate
    }
    private void initialisationRecompense(){
        tabRecompense = new int[Varietes.values().length];
        tabRecompense[0] = 6;
        tabRecompense[1] = 9;
        tabRecompense[2] = 12;
    }
    private void initialisationMalus(){
        tabMalus = new int[Varietes.values().length];
        tabMalus[0] = 1;
        tabMalus[1] = 2;
        tabMalus[2] = 4;
    }
    public int[] getTabPrix() {
        return tabPrix;
    }

    public int getNbPiece() {
        return nbPiece;
    }

    public void incrNbPiece (int ind){
        nbPiece+= tabRecompense[ind];
        System.out.println(" + "+String.valueOf(tabRecompense[ind]));
    }
    public boolean decrNbPiece (int ind){
        if (nbPiece-tabPrix[ind] < 0){
            return false;
        }else {
            nbPiece-= tabPrix[ind];
            return true;
        }
    }
    public void updateMalus(int ind){
        nbPiece -= tabMalus[ind];
        System.out.println(" MALUS : - "+String.valueOf(tabMalus[ind]));
    }
}
