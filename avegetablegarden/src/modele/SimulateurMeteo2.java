package modele;

import modele.Ordonnanceur;
import modele.SimulateurPotager;
import modele.environnement.Button.Button;
import modele.environnement.Button.ButtonMeteo;
import modele.environnement.Button.ButtonOutil;
import modele.outils.TypeOutil;

public class SimulateurMeteo2 {

    public static final int NB_METEO_MAX = TypeMeteo.values().length;
    private ButtonMeteo[] grilleDeMeteo = new ButtonMeteo[NB_METEO_MAX]; //Va permettre de savoir si on a activé le bouton pour planter le légume ou pas

    public SimulateurMeteo2() {
        initialisationDeMeteo();
        grilleDeMeteo[0].setActivite(true);
    }


    private void initialisationDeMeteo() {

        grilleDeMeteo[0] = new ButtonMeteo(TypeMeteo.soleil);
        grilleDeMeteo[1] = new ButtonMeteo(TypeMeteo.nuage);
        grilleDeMeteo[2] = new ButtonMeteo(TypeMeteo.pluie);
    }

    public Button[] getGrilleDeMeteo() {
        return grilleDeMeteo;
    }

    public void actionUtilisateur(int y, int x) {

        if (grilleDeMeteo[x] != null) {
            grilleDeMeteo[x].actionUtilisateur();
            // On met tous les autres boutons à false
            for (int _x = 0; _x < x; _x++) {
                grilleDeMeteo[_x].setActivite(false);
            }

            for (int _x = x+1; _x < NB_METEO_MAX; _x++) {
                grilleDeMeteo[_x].setActivite(false);
            }

        }
    }
}

