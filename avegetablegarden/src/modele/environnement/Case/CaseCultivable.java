package modele.environnement.Case;

import modele.SimulateurPotager;
import modele.environnement.Legume.varietes.Legume;
import modele.environnement.Legume.varietes.Salade;

public class CaseCultivable extends Case {

    private Legume legume;
    public CaseCultivable(SimulateurPotager _simulateurPotager) {
        super(_simulateurPotager);
    }

    @Override
    public void actionUtilisateur() {
        if (legume == null) {
            legume = new Salade();

        } else {
            System.out.println(legume.getCompteur().getSeconde());
            legume = null;
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
