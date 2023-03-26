package modele;

import modele.environnement.Case.Case;
import modele.environnement.Case.CaseGraine;
import modele.environnement.Legume.varietes.Varietes;

public class SimulateurGraines {
    private SimulateurPotager simPotager; //En soit on en a pas besoin donc il faudrait pouvoir construire la classe sans
    public static final int NB_VARIETE_MAX = 3;
    private CaseGraine[][] grilleDesGraines = new CaseGraine[1][NB_VARIETE_MAX]; //Va permettre de savoir si on a activé le bouton pour planter le légume ou pas

    public SimulateurGraines() {
        simPotager = new SimulateurPotager();
        initialisationDesGraines();
    }

    private void initialisationDesGraines() {
        grilleDesGraines[0][0] = new CaseGraine(simPotager, Varietes.salade);
        grilleDesGraines[0][1] = new CaseGraine(simPotager, Varietes.carrotte);
        grilleDesGraines[0][2] = new CaseGraine(simPotager, Varietes.salade);
    }

    public Case[][] getGrilleDesGraines() {
        return grilleDesGraines;
    }

    public void actionUtilisateur(int y, int x) {
        System.out.println("Hey");
        grilleDesGraines[0][0].setActivite(false);
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
