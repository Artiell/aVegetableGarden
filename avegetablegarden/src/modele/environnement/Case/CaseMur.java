package modele.environnement.Case;

import modele.SimulateurPotager;

public class CaseMur extends CaseNonCultivable{
    private TypeMur typeMur;
    public CaseMur (TypeMur _typeMur) {
        super();
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
