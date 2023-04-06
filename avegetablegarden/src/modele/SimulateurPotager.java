/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import modele.environnement.Case.*;
import modele.environnement.Legume.varietes.Salade;
import modele.environnement.Legume.varietes.Varietes;

import java.awt.Point;
import java.util.Random;


public class SimulateurPotager {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 15;

    private SimulateurGraines simulateurGraines;
    private SimulateurOutil simulateurOutil;
    private SimulateurMeteo2 simulateurMeteo2;
    TypeSol sol;

    public int[] getTabInventaireLegume() {
        return tabInventaireLegume;
    }
    public void incrTabInventaireLegume(int x) {
        tabInventaireLegume[x]++;
    }

    private int[] tabInventaireLegume;

    // private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une entité à partir de sa référence
    private Case[][] grilleCases = new Case[SIZE_X][SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées
    public SimulateurPotager() {
        simulateurGraines = new SimulateurGraines();
        simulateurOutil = new SimulateurOutil();
        simulateurMeteo2 = new SimulateurMeteo2();
        Ordonnanceur.getOrdonnanceur().add(simulateurMeteo2);
        initialisationDesEntites();
        sol = TypeSol.normal;

        //initialisation de l'inventaire
        this.tabInventaireLegume = new int[Varietes.values().length];
        //on initialise toutes les cases à 0
        for(int i=0; i<Varietes.values().length; i++){
            tabInventaireLegume[i] = 0;
        }

    }
    public TypeSol getSol (){
        return sol;
    }

    public SimulateurGraines getSimulateurGraines(){
        return simulateurGraines;
    }

    public SimulateurOutil getSimulateurOutil(){
        return simulateurOutil;
    }
    public SimulateurMeteo2 getSimulateurMeteo2() { return simulateurMeteo2; }
    public Case[][] getPlateau() {
        return grilleCases;
    }

    private void initialisationDesEntites() {

        // murs extérieurs horizontaux
        for (int x = 0; x < 20; x++) {
            Case cc = new CaseMur(this,simulateurGraines, simulateurOutil);
            Case cc1 = new CaseMur(this,simulateurGraines, simulateurOutil);
            addEntite(cc, x, 0);
            addEntite(cc1, x, SIZE_Y - 1);
            Ordonnanceur.getOrdonnanceur().add(cc);
            Ordonnanceur.getOrdonnanceur().add(cc1);
        }

        for (int x = 0; x < 20; x++) {
            Case cc = new CaseNonRatisser(this,simulateurGraines, simulateurOutil);
            Case cc1 = new CaseNonRatisser(this,simulateurGraines, simulateurOutil);
            addEntite(cc, x, 1);
            addEntite(cc1, x, SIZE_Y - 2);
            Ordonnanceur.getOrdonnanceur().add(cc);
            Ordonnanceur.getOrdonnanceur().add(cc1);
        }



        // PLUS DE MUR VERTICAUX
        // murs extérieurs verticaux

        for(int i=0; i<SIZE_X; i++){
            for(int j=0; j<SIZE_Y; j++){
                if(grilleCases[i][j] == null){
                    CaseCultivable cc = new CaseCultivable(this,simulateurGraines, simulateurOutil);
                    addEntite(cc, i, j);
                    Ordonnanceur.getOrdonnanceur().add(cc);
                }
            }
        }

    }

    public void actionUtilisateur(int x, int y) {
        if (grilleCases[x][y] != null) {
            grilleCases[x][y].actionUtilisateur();
        }
        //Gère l'outil le rateau
        if (simulateurOutil.getGrilleDesOutils()[0][1].getActivite()){
            if (grilleCases[x][y] instanceof CaseNonRatisser){
                grilleCases[x][y] = null;
                CaseCultivable cc = new CaseCultivable(this,simulateurGraines, simulateurOutil);
                addEntite(cc, x, y);
                Ordonnanceur.getOrdonnanceur().add(cc);
            }
        }
    }

    private void addEntite(Case e, int x, int y) {
        grilleCases[x][y] = e;
        //map.put(e, new Point(x, y));
    }

    public void updateSol (){
        if (simulateurMeteo2.getHumidite() > 75){
            sol = TypeSol.humide;
        } else if (simulateurMeteo2.getHumidite() < 25){
            sol = TypeSol.sec;
        }else {
            sol = TypeSol.normal;
        }
    }


    private Case objetALaPosition(Point p) {
        Case retour = null;
        return grilleCases[p.x][p.y];
    }

}
