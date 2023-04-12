package modele.outils;

import modele.Ordonnanceur;
import modele.SimulateurPotager;
import modele.environnement.Case.Case;
import modele.environnement.Case.CaseCultivable;
import modele.environnement.Case.CaseNonRatisser;

public class Rateau extends Fonctionnalite{
    public Rateau (){
       super();
    }
    public Fonctionnalite actionUtilisateur (int xx){return null;}
    public void actionUtilisateur(Case cases,int x, int y, SimulateurPotager simulateurPotager){

        System.out.println("Rateau");
        if (cases instanceof CaseNonRatisser){
            cases = null;
            CaseCultivable cc = new CaseCultivable(simulateurPotager);
            simulateurPotager.addEntite(cc, x, y);
            Ordonnanceur.getOrdonnanceur().add(cc);
        }
    }
}
