package modele.environnement.Case;

import modele.SimulateurGraines;
import modele.SimulateurOutil;
import modele.SimulateurPotager;

public class CaseNonCultivable extends Case {
    public CaseNonCultivable(SimulateurPotager _simulateurPotager, SimulateurGraines _simulateurGraines, SimulateurOutil _simOutil) {
        super(_simulateurPotager, _simulateurGraines, _simOutil);
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
