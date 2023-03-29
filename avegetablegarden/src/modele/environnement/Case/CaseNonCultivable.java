package modele.environnement.Case;

import modele.SimulateurGraines;
import modele.SimulateurOutil;
import modele.SimulateurPotager;

public abstract class CaseNonCultivable extends Case {
    public CaseNonCultivable(SimulateurPotager _simulateurPotager, SimulateurGraines _simulateurGraines, SimulateurOutil _simOutil) {
        super(_simulateurPotager, _simulateurGraines, _simOutil);
    }

    public abstract void actionUtilisateur();
}
