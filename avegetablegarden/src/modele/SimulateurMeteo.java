package modele;

import modele.environnement.Button.Button;
import modele.environnement.Button.ButtonMeteo;

public class SimulateurMeteo implements Runnable {
    private float humidite;
    private int tempsAppuyer;
    private int debutBouton;
    private int tempsAvantUpdateMeteo;
    private int update;
    public static final int NB_METEO_MAX = TypeMeteo.values().length;
    private final ButtonMeteo[] grilleDeMeteo = new ButtonMeteo[NB_METEO_MAX]; //Va permettre de savoir si on a activé le bouton pour planter le légume ou pas

    public SimulateurMeteo() {
        tempsAppuyer = 0;
        debutBouton = 0;
        update = 0;
        tempsAvantUpdateMeteo = 0;
        initialisationDeMeteo();
        humidite = 50;
        grilleDeMeteo[1].setActivite(true);
    }
    public void resetMeteo(){
        humidite = 50;
        grilleDeMeteo[0].setActivite(false);
        grilleDeMeteo[2].setActivite(false);
        tempsAppuyer = 0;
        debutBouton = 0;
        update = 0;
        tempsAvantUpdateMeteo = 0;
        grilleDeMeteo[1].setActivite(true);
    }
    public float getHumidite (){
        return humidite;
    }

    private void initialisationDeMeteo() {

        grilleDeMeteo[0] = new ButtonMeteo(TypeMeteo.soleil);
        grilleDeMeteo[1] = new ButtonMeteo(TypeMeteo.nuage);
        grilleDeMeteo[2] = new ButtonMeteo(TypeMeteo.pluie);
    }
    private void incrTemps () {
        tempsAppuyer = SimulateurTemps.getSimuTemps().getS() - debutBouton;
    }
    public Button[] getGrilleDeMeteo() {
        return grilleDeMeteo;
    }

    public void actionUtilisateur(int x) {

        if (grilleDeMeteo[x] != null) {
            if (!grilleDeMeteo[x].getActivite()) {
                debutBouton = SimulateurTemps.getSimuTemps().getS();
                tempsAvantUpdateMeteo = 0;
                grilleDeMeteo[x].actionUtilisateur();
                // On met tous les autres boutons à false
                for (int _x = 0; _x < x; _x++) {
                    grilleDeMeteo[_x].setActivite(false);
                }

                for (int _x = x + 1; _x < NB_METEO_MAX; _x++) {
                    grilleDeMeteo[_x].setActivite(false);
                }
            }

        }
    }

    public void updateHumidite (){
        int i = SimulateurTemps.getSimuTemps().getVitesseSimulation();
        if (i != 0 && grilleDeMeteo[0].getActivite()  && tempsAvantUpdateMeteo+i <= tempsAppuyer && humidite>0){
            humidite-=i*0.1;
            if (humidite <0){
                humidite = 0; 
            }
            tempsAvantUpdateMeteo = tempsAppuyer;
        }
        if (grilleDeMeteo[2].getActivite() && tempsAvantUpdateMeteo+i <= tempsAppuyer && tempsAppuyer>0 && humidite<100){
            humidite+=i*0.1;
            if (humidite >100){
                humidite = 100;
            }
            tempsAvantUpdateMeteo = tempsAppuyer;
        }
    }

    @Override
    public void run(){
        incrTemps();
        update ++;
        if (update%4 == 0){ // Permet de faire l'update seulement toute les secondes
            updateHumidite();
            //System.out.println(humidite);
        }

    }
}
