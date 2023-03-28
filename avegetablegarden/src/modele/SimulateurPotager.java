/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import modele.environnement.Case.Case;
import modele.environnement.Case.CaseCultivable;
import modele.environnement.Case.CaseNonCultivable;
import modele.environnement.Legume.varietes.Salade;
import modele.environnement.Legume.varietes.Varietes;

import java.awt.Point;
import java.util.Random;


public class SimulateurPotager {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;

    private SimulateurMeteo simMet;
    private SimulateurGraines simulateurGraines;
    private SimulateurOutil simulateurOutil;

    // private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une entité à partir de sa référence
    private Case[][] grilleCases = new Case[SIZE_X][SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées
    public SimulateurPotager(SimulateurGraines _simulateurGraines, SimulateurOutil _simOutils) {
        simulateurGraines = _simulateurGraines;
        simulateurOutil = _simOutils;
        initialisationDesEntites();
        simMet = new SimulateurMeteo(this);

    }


    
    public Case[][] getPlateau() {
        return grilleCases;
    }

    private void initialisationDesEntites() {

        // murs extérieurs horizontaux
        for (int x = 0; x < 20; x++) {
            addEntite(new CaseNonCultivable(this, simulateurGraines, simulateurOutil), x, 0);
            addEntite(new CaseNonCultivable(this, simulateurGraines, simulateurOutil), x, SIZE_Y - 1);
        }



        // PLUS DE MUR VERTICAUX
        // murs extérieurs verticaux
//        for (int y = 1; y < 9; y++) {
//            addEntite(new CaseNonCultivable(this), 0, y);
//            addEntite(new CaseNonCultivable(this), 19, y);
//        }

        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++){
                if(grilleCases[i][j] == null){
                    addEntite(new CaseCultivable(this,simulateurGraines, simulateurOutil), i, j);
                }
            }
        }


        Random rnd = new Random();

        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 1; y < SIZE_Y-1; y++) {
                CaseCultivable cc = new CaseCultivable(this, simulateurGraines, simulateurOutil);
                addEntite(cc , x, y);

                /*Permet d'afficher les germes dès le lancer de l'application sans cette parti on part seulement avec de la terre

                if (rnd.nextBoolean()) {
                    cc.actionUtilisateur();
                }*/

                Ordonnanceur.getOrdonnanceur().add(cc);

            }
        }

    }

    public void actionUtilisateur(int x, int y) {
        if (grilleCases[x][y] != null) {
            grilleCases[x][y].actionUtilisateur();
        }
    }

    private void addEntite(Case e, int x, int y) {
        grilleCases[x][y] = e;
        //map.put(e, new Point(x, y));
    }


    private Case objetALaPosition(Point p) {
        Case retour = null;
        return grilleCases[p.x][p.y];
    }

}
