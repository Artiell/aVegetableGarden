package modele.environnement.Case;

import modele.SimulateurPotager;
import modele.environnement.Legume.varietes.Legume;
import modele.environnement.Legume.varietes.Varietes;

public class CaseGraine extends Case {
    private final Varietes typeLegumes;
    private boolean estActif;
    public CaseGraine(SimulateurPotager _simulateurPotager, Varietes _legume) {
        super(_simulateurPotager);
        typeLegumes = _legume;
        estActif = false;
    }

    @Override
    public void run() {

    }
    public Varietes getVariete (){
        return typeLegumes;
    }
    public boolean getActivite (){
        return estActif;
    }
    public void setActivite (boolean b){
        estActif = b;
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
