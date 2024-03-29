/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import modele.environnement.Case.*;
import modele.environnement.Legume.varietes.Varietes;
import modele.fonctionnalite.*;
import modele.fonctionnalite.outils.Botte;
import modele.fonctionnalite.outils.Pelle;
import modele.fonctionnalite.outils.Poubelle;
import modele.fonctionnalite.outils.Rateau;
import modele.fonctionnalite.plantes.PlanterGraineCarotte;
import modele.fonctionnalite.plantes.PlanterGraineSalade;
import modele.fonctionnalite.plantes.PlanterGraineTomate;


public class SimulateurPotager implements Runnable{
    private boolean finPartie;
    private Fonctionnalite fonctionnalite;
    private final int SIZE_X = 20;
    private final int SIZE_Y = 15; // enlever static
    private final SimulateurMeteo simulateurMeteo;
    private final Magasin magasin;
    TypeSol sol;
    private int[] tabInventaireLegume;
    private Case[][] grilleCases = new Case[SIZE_X][SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées


    public int getSIZE_X (){return SIZE_X;}
    public int getSIZE_Y (){return SIZE_Y;}
    public int[] getTabInventaireLegume() {
        return tabInventaireLegume;
    }

    public void incrTabInventaireLegume(int x) {
        tabInventaireLegume[x]++;
    }
    // private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une entité à partir de sa référence
    public SimulateurPotager() {
        finPartie = false;
        fonctionnalite = null;
        magasin = new Magasin();
        simulateurMeteo = new SimulateurMeteo();

        Ordonnanceur.getOrdonnanceur().add(simulateurMeteo);

        initialisationDesEntites();
        initialisationSol();

        //initialisation de l'inventaire
        this.tabInventaireLegume = new int[Varietes.values().length];
        initialisationInventaire();

    }
    public boolean getFinPartie(){
        return finPartie;
    }

    public void setFinPartie(boolean _finPartie) {
        this.finPartie = _finPartie;
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
    public Magasin getMagasin (){
        return magasin;
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
        Ordonnanceur.getOrdonnanceur().add(cc1);
        Ordonnanceur.getOrdonnanceur().add(cc2);
        Ordonnanceur.getOrdonnanceur().add(cc3);
        Ordonnanceur.getOrdonnanceur().add(cc4);




        // PLUS DE MUR VERTICAUX
        // murs extérieurs verticaux

        for(int i=1; i<SIZE_X-1; i++){
            for(int j=1; j<SIZE_Y-1; j++){
                if(grilleCases[i][j] != null) {
                    Ordonnanceur.getOrdonnanceur().remove(grilleCases[i][j]);
                    CaseCultivable cc = new CaseCultivable(this);
                    addEntite(cc, i, j);
                    Ordonnanceur.getOrdonnanceur().add(cc);
                }else {
                    CaseCultivable cc = new CaseCultivable(this);
                    addEntite(cc, i, j);
                    Ordonnanceur.getOrdonnanceur().add(cc);
                }
            }
        }

        for (int x = 0; x < 20; x++) {
            int xRnd = (int) (Math.random() * (SIZE_X-2) + 1);
            int yRnd = (int) (Math.random() * (SIZE_Y-2) + 1);
            if (grilleCases[xRnd][yRnd] != null){
                Ordonnanceur.getOrdonnanceur().remove(grilleCases[xRnd][yRnd]);
                Case cc = new CaseNonRatisser();
                addEntite(cc, xRnd, yRnd);
                Ordonnanceur.getOrdonnanceur().add(cc);
            }
        }
    }

    private void initialisationInventaire (){
        //on initialise toutes les cases à 0
        for(int i=0; i<Varietes.values().length; i++){
            tabInventaireLegume[i] = 0;
        }
    }

    private void initialisationSol () {
        sol = TypeSol.normal;
    }

    public void actionUtilisateur(int x, int y) {
        if (grilleCases[x][y] != null) {
            grilleCases[x][y].actionUtilisateur();
        }
        //Gère l'outil le rateau
        if (fonctionnalite instanceof Rateau){
            Rateau r = (Rateau) fonctionnalite;
            r.actionUtilisateur(grilleCases[x][y], x, y,this);
            magasin.updateMalus(3);
        }

    }
    public void actionUtilisateurOutils(int x) {
        switch (x) {
            case 0 :
                if (fonctionnalite instanceof Pelle) {
                    fonctionnalite = null;
                } else {
                    fonctionnalite = new Pelle();
                }
                break;
            case 1 :
                if (fonctionnalite instanceof Rateau) {
                    fonctionnalite = null;
                } else {
                    fonctionnalite = new Rateau();
                }
                break;
            case 2 :
                if (fonctionnalite instanceof Botte) {
                    fonctionnalite = null;
                } else {
                    fonctionnalite = new Botte();
                }
                break;
            case 3 :
                if (fonctionnalite instanceof Poubelle) {
                    fonctionnalite = null;
                } else {
                    fonctionnalite = new Poubelle();
                }
                break;
        }

    }

    public void actionUtilisateurGraines(int _x) {
        switch (_x) {
            case 0 :
                if (fonctionnalite instanceof PlanterGraineSalade) {
                    fonctionnalite = null;
                } else {
                    fonctionnalite = new PlanterGraineSalade();
                }
                break;
            case 1 :
                if (fonctionnalite instanceof PlanterGraineCarotte) {
                    fonctionnalite = null;
                } else {
                    fonctionnalite = new PlanterGraineCarotte();
                }
                break;
            case 2 :
                if (fonctionnalite instanceof PlanterGraineTomate) {
                    fonctionnalite = null;
                } else {
                    fonctionnalite = new PlanterGraineTomate();
                }
                break;
        }
    }

    public void addEntite(Case e, int x, int y) {
        grilleCases[x][y] = e;
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

    public void updateBush() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {

                if (grilleCases[i][j] instanceof CaseCultivable
                        && (((CaseCultivable) grilleCases[i][j]).getLegume() != null)
                        && (((CaseCultivable) grilleCases[i][j]).getLegume().getDureePourri() >= 30)) {

                    //mise à jour du malus
                    switch (((CaseCultivable) grilleCases[i][j]).getLegume().getVariete()){
                        case salade : magasin.updateMalus(0);break;
                        case carotte : magasin.updateMalus(1);break;
                        case tomate : magasin.updateMalus(2);break;
                    }

                    //on supprime premièrement la case du run de l'ordo pour eviter un nullpointer exception sur la methode run d'une case qui n'est plus dans le vecteur
                    Ordonnanceur.getOrdonnanceur().remove(grilleCases[i][j]);
                    //on passe la case en question a null
                    grilleCases[i][j] = null;

                    //on crée une nouvelle case et on l'ajoute au tableau et a l'ordo
                    CaseNonRatisser cnt = new CaseNonRatisser();
                    this.addEntite(cnt, i, j);
                    Ordonnanceur.getOrdonnanceur().add(cnt);

                }
            }
        }
    }
    private void suppressionEntite (){
        for (int i =0; i<SIZE_X; i++){
            for(int j=0; j<SIZE_Y; j++){
                Ordonnanceur.getOrdonnanceur().remove(grilleCases[i][j]);
            }
        }
    }
    public void reset (){
        suppressionEntite();
        System.out.println("on recommence à 0");
        fonctionnalite = null;
        initialisationDesEntites();
        initialisationInventaire();
        initialisationSol();
        magasin.resetMagasin();
        simulateurMeteo.resetMeteo();
        SimulateurTemps.getSimuTemps().resetTemps();
        finPartie = false;
    }
    public void verifFinPartie (){
        if (magasin.getNbPiece() < -10){
            finPartie = true;
            SimulateurTemps.getSimuTemps().setVitesseSimulation(0);
            magasin.setMessage("FIN DE LA PARTIE");
        }
    }

    public void run (){
        this.updateSol();
        this.updateBush();
        verifFinPartie();
    }
}
