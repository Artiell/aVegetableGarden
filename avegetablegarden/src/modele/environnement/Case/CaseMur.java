package modele.environnement.Case;

public class CaseMur extends CaseNonCultivable{
    private final TypeMur typeMur;
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
