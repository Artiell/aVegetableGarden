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

        //si le premier outil est activé (la pelle)
        if (simulateurOutil.getGrilleDesOutils()[0][0].getActivite()){
            if (legume != null && legume.getEtatLegume() == EtatLegume.mature){
                switch (legume.getVariete()){

                    case salade : this.simulateurPotager.getTabInventaireLegume()[0] = this.simulateurPotager.getTabInventaireLegume()[0]++;
                        legume = null;
                        break;
                    case carrotte: this.simulateurPotager.getTabInventaireLegume()[0] = this.simulateurPotager.getTabInventaireLegume()[1]++;
                        legume = null;
                        break;
                    case betrave: this.simulateurPotager.getTabInventaireLegume()[0] = this.simulateurPotager.getTabInventaireLegume()[2]++;
                        legume = null;
                        break;
                    case tomate: this.simulateurPotager.getTabInventaireLegume()[0] = this.simulateurPotager.getTabInventaireLegume()[3]++;
                        legume = null;
                        break;
                    case courgette: this.simulateurPotager.getTabInventaireLegume()[0] = this.simulateurPotager.getTabInventaireLegume()[4]++;
                        legume = null;
                }
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
