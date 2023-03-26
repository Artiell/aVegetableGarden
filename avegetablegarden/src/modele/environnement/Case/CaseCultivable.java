package modele.environnement.Case;

import modele.SimulateurGraines;
import modele.SimulateurPotager;
import modele.environnement.Legume.EtatLegume;
import modele.environnement.Legume.varietes.Legume;
import modele.environnement.Legume.varietes.Salade;

public class CaseCultivable extends Case {

    private Legume legume;
    public CaseCultivable(SimulateurPotager _simulateurPotager, SimulateurGraines _simulateurGraines) {
        super(_simulateurPotager, _simulateurGraines);
    }

    @Override
    public void actionUtilisateur() {
        if (legume == null) {
            legume = new Salade();

        } else {
            //System.out.println(legume.getCompteur().getSeconde());
            switch (legume.getEtatLegume()){
                case germe : legume.setEtatLegume(EtatLegume.mature); break;
                case mature: legume.setEtatLegume(EtatLegume.pourri); break;
                case pourri: legume = null;
            }
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
