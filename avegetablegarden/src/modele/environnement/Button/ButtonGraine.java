package modele.environnement.Button;

import modele.SimulateurPotager;
import modele.environnement.Legume.varietes.Varietes;

public class ButtonGraine extends Button {
    private Varietes typeLegumes;
    public ButtonGraine(Varietes _legume) {
        super();
        typeLegumes = _legume;
    }

    @Override
    public void run() {

    }
    public Varietes getVariete (){
        return typeLegumes;
    }
    @Override
    public void actionUtilisateur(){
        System.out.println("Je viens de cliquer sur une case de mes graines");
        if (estActif){
            estActif = false;
        }else {
            estActif = true;
        }
    }
}
