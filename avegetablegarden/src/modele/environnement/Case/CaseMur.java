package modele.environnement.Case;

import modele.SimulateurOutil;
import modele.SimulateurGraines;
import modele.SimulateurPotager;

public class CaseMur extends CaseNonCultivable{
    private TypeMur typeMur;
    public CaseMur (SimulateurPotager _simulateurPotager, SimulateurGraines _simulateurGraines, SimulateurOutil _simOutil, TypeMur _typeMur) {
        super(_simulateurPotager, _simulateurGraines, _simOutil);
        typeMur = _typeMur;
    }
    public TypeMur getTypeMur (){
        return typeMur;
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
