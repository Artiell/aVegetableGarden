package modele.environnement.Case;

import modele.SimulateurOutil;
import modele.SimulateurGraines;
import modele.SimulateurPotager;

public class CaseMur extends CaseNonCultivable{
    public CaseMur (SimulateurPotager _simulateurPotager, SimulateurGraines _simulateurGraines, SimulateurOutil _simOutil) {
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
