package modele.fonctionnalite.outils;

import modele.Ordonnanceur;
import modele.SimulateurPotager;
import modele.environnement.Case.Case;
import modele.environnement.Case.CaseCultivable;
import modele.environnement.Case.CaseNonRatisser;
import modele.environnement.Legume.Legume;
import modele.fonctionnalite.Fonctionnalite;

public class Rateau extends Outil {
    public Rateau (){
       super();
    }
    public Fonctionnalite actionUtilisateur (int xx){return null;}
    public void actionUtilisateur(Case cases,int x, int y, SimulateurPotager simulateurPotager){

        if (cases instanceof CaseNonRatisser){
            cases = null;
            CaseCultivable cc = new CaseCultivable(simulateurPotager);
            simulateurPotager.addEntite(cc, x, y);
            Ordonnanceur.getOrdonnanceur().add(cc);
        }
    }
    public Legume actionOutil (Legume legume, SimulateurPotager simulateurPotager){
        return legume;
    }
}
