package modele.outils;

import java.time.LocalTime;

public class Compteur {
    private int seconde;
    private boolean estActif;
    private int valMax;
    public Compteur(int max){
        System.out.println("Le compteur est créer");
        valMax = max;
        seconde = 0;
        estActif = false;
    }

    public int getSeconde (){ return seconde; }
    public void setEstActif (boolean a){
        estActif = a;
    }

    public void demarre (){
        System.out.println("Le compteur démarre");
        estActif = true;
    }
    public void incremente (int cpt){
        if (estActif) {
            if (seconde <= valMax) {
                LocalTime time2 = java.time.LocalTime.now();
                if (time2.getSecond() != cpt) {
                    seconde++;
                    cpt++;
                    //System.out.println(seconde);
                }
            } else {
                System.out.println("Le compteur est arrivé au max ");
                estActif = false;
            }
        }
    }
    /*
    public void arret(){
        estActif =false;
    }*/
}
