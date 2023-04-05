package modele.outils;

import java.time.LocalTime;

public class Compteur {
    private int seconde;
    private boolean estActif;
    private int valMax;
    public Compteur(int max){
        valMax = max;
        seconde = 0;
        estActif = false;
    }

    public int getSeconde (){ return seconde; }
    public void setEstActif (boolean a){
        estActif = a;
    }

    public void demarre (){
        estActif = true;
    }
    public void incremente (int cpt){
        if (estActif) {
            if (seconde <= valMax) {
                LocalTime time2 = java.time.LocalTime.now();
                if (time2.getSecond() != cpt) {
                    seconde++;
                    cpt++;
                }
            } else {
                estActif = false;
            }
        }
    }
}
