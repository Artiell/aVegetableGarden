package modele;

import modele.environnement.Button.Button;
import modele.environnement.Button.ButtonOutil;
import modele.outils.TypeOutil;

public class SimulateurOutil {

    private boolean outilActif = false;

    public static final int NB_OUTIL_MAX = TypeOutil.values().length;
    private ButtonOutil[][] grilleDesOutils = new ButtonOutil[1][NB_OUTIL_MAX]; //Va permettre de savoir si on a activé le bouton pour planter le légume ou pas

    public SimulateurOutil() {
        initialisationDesOutils();
    }

    public boolean isOutilActif() {
        return outilActif;
    }
    public void updateOutilActif(){
        outilActif = false;
        for (int _y=0; _y<1; _y++) {
            for (int _x = 0; _x < NB_OUTIL_MAX; _x++) {
                if(grilleDesOutils[_y][_x].getActivite()){
                    outilActif = true;
                }
            }
        }
    }

    public void setAllOutilFalse() {
        for(int i = 0; i < NB_OUTIL_MAX; i++) {
            grilleDesOutils[0][i].setActivite(false);
        }
    }

    private void initialisationDesOutils() {

        grilleDesOutils[0][0] = new ButtonOutil(TypeOutil.pelle);
        grilleDesOutils[0][1] = new ButtonOutil(TypeOutil.rateau);
        grilleDesOutils[0][2] = new ButtonOutil(TypeOutil.botte);
        grilleDesOutils[0][3] = new ButtonOutil(TypeOutil.poubelle);
    }

    public Button[][] getGrilleDesOutils() {
        return grilleDesOutils;
    }

    public void actionUtilisateur(int y, int x) {

        this.updateOutilActif();

        if (grilleDesOutils[y][x] != null) {
            grilleDesOutils[y][x].actionUtilisateur();
            // On met tous les autres boutons à false
            for (int _y=0; _y<1; _y++) {
                for (int _x = 0; _x < x; _x++) {
                    grilleDesOutils[_y][_x].setActivite(false);
                }
            }
            for (int _y=0; _y<1; _y++) {
                for (int _x = x+1; _x < NB_OUTIL_MAX; _x++) {
                    grilleDesOutils[_y][_x].setActivite(false);
                }
            }
        }
    }
}

