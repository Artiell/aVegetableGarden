package modele.environnement.Button;

import modele.SimulateurPotager;
import modele.environnement.Legume.varietes.Varietes;

public class ButtonGraine extends Button {
    private Varietes typeLegumes;
    public ButtonGraine(Varietes _legume) {
        super();
        typeLegumes = _legume;
    }

    public Varietes getVariete (){
        return typeLegumes;
    }
    @Override
    public void actionUtilisateur(){
        if (estActif){
            estActif = false;
        }else {
            estActif = true;
        }
    }
}
