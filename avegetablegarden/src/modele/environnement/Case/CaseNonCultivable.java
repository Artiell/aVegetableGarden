package modele.environnement.Case;

import modele.SimulateurGraines;
import modele.SimulateurPotager;

public class CaseNonCultivable extends Case {
    public CaseNonCultivable(SimulateurPotager _simulateurPotager, SimulateurGraines _simulateurGraines) {
        super(_simulateurPotager, _simulateurGraines);
    }

    @Override
    public void actionUtilisateur() {
        // TODO
    }

    @Override
    public void run() {
        // TODO
    }
}
