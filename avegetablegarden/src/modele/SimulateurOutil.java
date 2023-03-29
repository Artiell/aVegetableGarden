package modele;

import modele.environnement.Button.Button;
import modele.environnement.Button.ButtonGraine;
import modele.environnement.Button.ButtonOutil;
import modele.environnement.Legume.varietes.Varietes;
import modele.outils.Outil;
import modele.outils.TypeOutil;

import java.lang.reflect.Type;

public class SimulateurOutil {
    public static final int NB_OUTIL_MAX = TypeOutil.values().length;
    private ButtonOutil[][] grilleDesOutils = new ButtonOutil[1][NB_OUTIL_MAX]; //Va permettre de savoir si on a activé le bouton pour planter le légume ou pas

    public SimulateurOutil() {
        initialisationDesOutils();
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

