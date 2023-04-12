package modele.environnement.Case;
import modele.SimulateurPotager;

public abstract class CaseNonCultivable extends Case {
    public CaseNonCultivable() {
        super();
    }

    public abstract void actionUtilisateur();
}
