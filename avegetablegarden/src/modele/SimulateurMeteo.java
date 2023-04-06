package modele;

import modele.Ordonnanceur;
import modele.SimulateurPotager;
import modele.environnement.Button.Button;
import modele.environnement.Button.ButtonOutil;
import modele.outils.TypeOutil;

public class SimulateurMeteo implements Runnable {
    private SimulateurPotager simPot;
    public SimulateurMeteo(SimulateurPotager _simPot) {
        Ordonnanceur.getOrdonnanceur().add(this);
        simPot = _simPot;

    }
    @Override
    public void run(){
    }
}
