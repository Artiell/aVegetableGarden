package modele;
import modele.environnement.Button.Button;
import modele.environnement.Button.ButtonGraine;
import modele.environnement.Case.Case;
import modele.environnement.Legume.varietes.Varietes;

public class SimulateurGraines {
    public static final int NB_VARIETE_MAX = 3;
    private ButtonGraine[][] grilleDesGraines = new ButtonGraine[1][NB_VARIETE_MAX]; //Va permettre de savoir si on a activé le bouton pour planter le légume ou pas

    public SimulateurGraines() {
        initialisationDesGraines();
    }

    private void initialisationDesGraines() {
        grilleDesGraines[0][0] = new ButtonGraine(Varietes.salade);
        grilleDesGraines[0][1] = new ButtonGraine(Varietes.carrotte);
        grilleDesGraines[0][2] = new ButtonGraine(Varietes.salade);
    }

    public Button[][] getGrilleDesGraines() {
        return grilleDesGraines;
    }

    public void actionUtilisateur(int y, int x) {

        if (grilleDesGraines[y][x] != null) {
            grilleDesGraines[y][x].actionUtilisateur();
            // On met tous les autres boutons à false
            for (int _y=0; _y<1; _y++) {
                for (int _x = 0; _x < x; _x++) {
                    grilleDesGraines[_y][_x].setActivite(false);
                }
            }
            for (int _y=0; _y<1; _y++) {
                for (int _x = x+1; _x < NB_VARIETE_MAX; _x++) {
                    grilleDesGraines[_y][_x].setActivite(false);
                }
            }
        }
    }
}
