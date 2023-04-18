package modele.environnement.Case;

import modele.SimulateurPotager;
import modele.environnement.Legume.Legume;
import modele.fonctionnalite.outils.Outil;
import modele.fonctionnalite.plantes.Plante;

public class CaseCultivable extends Case {

    private Legume legume;
    private final SimulateurPotager simulateurPotager;
    public CaseCultivable(SimulateurPotager _simulateurPotager) {
        super();
        simulateurPotager = _simulateurPotager;
    }

    @Override
    public void actionUtilisateur() {

        // gere la pelle la botte et la poubelle

        if (simulateurPotager.getFonctionnalite() instanceof Outil outil) {
            legume = outil.actionOutil(legume, simulateurPotager);
        } else if (simulateurPotager.getFonctionnalite() instanceof Plante plante) {
            legume = plante.action(simulateurPotager.getMagasin());
        }

    }

    public Legume getLegume() {
        return legume;
    }
    @Override
    public void run() {
        if (legume != null) {
            legume.nextStep(simulateurPotager.getSol());
            legume.vieillir();
        }
    }
}
