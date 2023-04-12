/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import modele.environnement.Case.*;
import modele.environnement.Legume.varietes.Salade;
import modele.environnement.Legume.varietes.Varietes;
import modele.outils.*;

import java.awt.Point;
import java.util.Random;


public class SimulateurPotager implements Runnable{
    Fonctionnalite fonctionnalite;

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 15;

    private SimulateurMeteo simulateurMeteo;
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
        fonctionnalite = null;

        simulateurMeteo = new SimulateurMeteo();

        Ordonnanceur.getOrdonnanceur().add(simulateurMeteo);

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
    public Fonctionnalite getFonctionnalite (){
        return fonctionnalite;
    }
    public void setFonctionnalite(Fonctionnalite f){
        fonctionnalite = f;
    }
    public SimulateurMeteo getSimulateurMeteo() { return simulateurMeteo; }
    public Case[][] getPlateau() {
        return grilleCases;
    }

    private void initialisationDesEntites() {

        // murs extérieurs horizontaux
        for (int x = 1; x < SIZE_X-1; x++) {
            Case cc = new CaseMur(TypeMur.haut);
            Case cc1 = new CaseMur(TypeMur.bas);
            addEntite(cc, x, 0);
            addEntite(cc1, x, SIZE_Y - 1);
            Ordonnanceur.getOrdonnanceur().add(cc);
            Ordonnanceur.getOrdonnanceur().add(cc1);
        }

        for (int y = 1; y < SIZE_Y-1; y++) {
            Case cc = new CaseMur(TypeMur.gauche);
            Case cc1 = new CaseMur(TypeMur.droit);
            addEntite(cc, 0, y);
            addEntite(cc1, SIZE_X-1, y);
            Ordonnanceur.getOrdonnanceur().add(cc);
            Ordonnanceur.getOrdonnanceur().add(cc1);
        }
        //Mur des coins
        Case cc1 = new CaseMur(TypeMur.tournantHautGauche);
        Case cc2 = new CaseMur(TypeMur.tournantHautDroit);
        Case cc3 = new CaseMur(TypeMur.tournantBasGauche);
        Case cc4 = new CaseMur(TypeMur.tournantBasDroit);
        addEntite(cc1, 0, 0);
        addEntite(cc2, SIZE_X-1, 0);
        addEntite(cc3,0, SIZE_Y - 1);
        addEntite(cc4, SIZE_X-1, SIZE_Y - 1);

        for (int x = 0; x < 20; x++) {
            int xRnd = (int) (Math.random() * (SIZE_X-2) + 1);
            int yRnd = (int) (Math.random() * (SIZE_Y-2) + 1);
            if (grilleCases[xRnd][yRnd] == null){
                Case cc = new CaseNonRatisser();
                addEntite(cc, xRnd, yRnd);
                Ordonnanceur.getOrdonnanceur().add(cc);
            }
        }



        // PLUS DE MUR VERTICAUX
        // murs extérieurs verticaux

        for(int i=1; i<SIZE_X-1; i++){
            for(int j=1; j<SIZE_Y-1; j++){
                if(grilleCases[i][j] == null){
                    CaseCultivable cc = new CaseCultivable(this);
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
        if (fonctionnalite instanceof Rateau){
            Rateau r = (Rateau) fonctionnalite;
            r.actionUtilisateur(grilleCases[x][y], x, y,this);
        }

    }

    public void addEntite(Case e, int x, int y) {
        grilleCases[x][y] = e;
        //map.put(e, new Point(x, y));
    }

    public void updateSol (){
        if (simulateurMeteo.getHumidite() > 75){
            sol = TypeSol.humide;
        } else if (simulateurMeteo.getHumidite() < 25){
            sol = TypeSol.sec;
        }else {
            sol = TypeSol.normal;
        }
    }


    private Case objetALaPosition(Point p) {
        Case retour = null;
        return grilleCases[p.x][p.y];
    }

    public void run (){
        updateSol();
    }
}
