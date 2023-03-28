package modele.environnement.Case;

import modele.SimulateurGraines;
import modele.SimulateurOutil;
import modele.SimulateurPotager;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.varietes.Carrotte;
import modele.environnement.Legume.varietes.Legume;
import modele.environnement.Legume.varietes.Salade;

public class CaseCultivable extends Case {

    private Legume legume;
    public CaseCultivable(SimulateurPotager _simulateurPotager, SimulateurGraines _simulateurGraines, SimulateurOutil _simOutils) {
        super(_simulateurPotager, _simulateurGraines, _simOutils);
    }

    @Override
    public void actionUtilisateur() {
        if (simulateurOutil.getGrilleDesOutils()[0][0].getActivite()){
            System.out.println("On a la pelle et normalement on détruit le légume");
            if (legume != null){
                legume = null;
            }
        }
        // On vérifie qu'on a selectionné la bonne graine et que la pelle n'est pas sélectionnée
        if (simulateurGraines.getGrilleDesGraines()[0][0].getActivite() && !simulateurOutil.getGrilleDesOutils()[0][0].getActivite()){
            if (legume == null) {
                legume = new Salade();

            } else {
                //System.out.println(legume.getCompteur().getSeconde());
                switch (legume.getEtatLegume()){/* permet de faire changer d'état le légume en cliquant
                    case germe : legume.setEtatLegume(EtatLegume.mature); break;
                    case mature: legume.setEtatLegume(EtatLegume.pourri); break;
                    case pourri: legume = null;*/
                }
            }
        }
        if (simulateurGraines.getGrilleDesGraines()[0][1].getActivite()){
            if (legume == null) {
                legume = new Carrotte();
                System.out.println("On plante une nouvelle carotte");

            } else {
                // A coder
            }
        }
        if (simulateurGraines.getGrilleDesGraines()[0][2].getActivite()){
            if (legume == null) {
                // A coder
                System.out.println("On plante une nouvelle fleur");

            } else {
                // A coder
            }
        }
        else {
            System.out.println("pas appuyer sur le bouton avant");
        }

    }

    public Legume getLegume() {
        return legume;
    }

    @Override
    public void run() {
        if (legume != null) {
            legume.nextStep();
            legume.vieillir();
        }
    }
}
