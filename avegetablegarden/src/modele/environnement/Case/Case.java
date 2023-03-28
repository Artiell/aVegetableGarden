/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.environnement.Case;

import modele.SimulateurGraines;
import modele.SimulateurOutil;
import modele.SimulateurPotager;

public abstract class Case implements Runnable {
    protected SimulateurPotager simulateurPotager;
    protected SimulateurGraines simulateurGraines;
    protected SimulateurOutil simulateurOutil;

    private int précipitations; // TODO : mis à jour par le simulateur de météo pour chaque case ()
    private int ensolleillement;

    
    public Case(SimulateurPotager _simulateurPotager, SimulateurGraines _simulateurGraines, SimulateurOutil _simulateurOutils) {
        simulateurPotager = _simulateurPotager;
        simulateurGraines = _simulateurGraines;
        simulateurOutil = _simulateurOutils;
    }

    public abstract void actionUtilisateur();


  }
