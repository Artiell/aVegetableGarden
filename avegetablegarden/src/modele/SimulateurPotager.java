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


public class SimulateurPotager implements Runnable{

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 15;

    private SimulateurGraines simulateurGraines;
    private SimulateurOutil simulateurOutil;
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
        simulateurGraines = new SimulateurGraines();
        simulateurOutil = new SimulateurOutil();
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

    public SimulateurGraines getSimulateurGraines(){
        return simulateurGraines;
    }

    public SimulateurOutil getSimulateurOutil(){
        return simulateurOutil;
    }
    public SimulateurMeteo getSimulateurMeteo() { return simulateurMeteo; }
    public Case[][] getPlateau() {
        return grilleCases;
    }

    private void initialisationDesEntites() {

        // murs extérieurs horizontaux
        for (int x = 1; x < SIZE_X-1; x++) {
            Case cc = new CaseMur(this,simulateurGraines, simulateurOutil, TypeMur.haut);
            Case cc1 = new CaseMur(this,simulateurGraines, simulateurOutil, TypeMur.bas);
            addEntite(cc, x, 0);
            addEntite(cc1, x, SIZE_Y - 1);
            Ordonnanceur.getOrdonnanceur().add(cc);
            Ordonnanceur.getOrdonnanceur().add(cc1);
        }

        for (int y = 1; y < SIZE_Y-1; y++) {
            Case cc = new CaseMur(this,simulateurGraines, simulateurOutil, TypeMur.gauche);
            Case cc1 = new CaseMur(this,simulateurGraines, simulateurOutil, TypeMur.droit);
            addEntite(cc, 0, y);
            addEntite(cc1, SIZE_X-1, y);
            Ordonnanceur.getOrdonnanceur().add(cc);
            Ordonnanceur.getOrdonnanceur().add(cc1);
        }
        //Mur des coins
        Case cc1 = new CaseMur(this,simulateurGraines, simulateurOutil, TypeMur.tournantHautGauche);
        Case cc2 = new CaseMur(this,simulateurGraines, simulateurOutil, TypeMur.tournantHautDroit);
        Case cc3 = new CaseMur(this,simulateurGraines, simulateurOutil, TypeMur.tournantBasGauche);
        Case cc4 = new CaseMur(this,simulateurGraines, simulateurOutil, TypeMur.tournantBasDroit);
        addEntite(cc1, 0, 0);
        addEntite(cc2, SIZE_X-1, 0);
        addEntite(cc3,0, SIZE_Y - 1);
        addEntite(cc4, SIZE_X-1, SIZE_Y - 1);

        for (int x = 0; x < 20; x++) {
            int xRnd = (int) (Math.random() * (SIZE_X-2) + 1);
            int yRnd = (int) (Math.random() * (SIZE_Y-2) + 1);
            if (grilleCases[xRnd][yRnd] == null){
                Case cc = new CaseNonRatisser(this,simulateurGraines, simulateurOutil);
                addEntite(cc, xRnd, yRnd);
                Ordonnanceur.getOrdonnanceur().add(cc);
            }
        }



        // PLUS DE MUR VERTICAUX
        // murs extérieurs verticaux

        for(int i=1; i<SIZE_X-1; i++){
            for(int j=1; j<SIZE_Y-1; j++){
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

    public synchronized void updateBush(){
        for(int i=0; i<SIZE_X; i++){
            for(int j=0; j<SIZE_Y; j++){

                if(grilleCases[i][j] instanceof CaseCultivable){
                    if(((CaseCultivable) grilleCases[i][j]).getLegume() != null){
                        System.out.println(((CaseCultivable) grilleCases[i][j]).getLegume().getTempsPourri());
                        if(((CaseCultivable) grilleCases[i][j]).getLegume().getTempsPourri() >= 20){

                            //on supprime premièrement la case du run de l'ordo pour eviter un nullpointer exception sur la methode run d'une case qui n'est plus dans le vecteur
                            Ordonnanceur.getOrdonnanceur().remove(grilleCases[i][j]);
                            //on passe la case en question a null
                            grilleCases[i][j] = null;

                            //on crée une nouvelle case et on l'ajoute au tableau et a l'ordo
                            CaseNonRatisser cnt = new CaseNonRatisser(this, this.simulateurGraines, this.simulateurOutil);
                            this.addEntite(cnt, i, j);
                            System.out.println("case non ratisser");
                            Ordonnanceur.getOrdonnanceur().add(cnt);

                        }
                    }

                }
            }
        }
    }

    @Override
    public void run() {
        this.updateBush();
    }
}
