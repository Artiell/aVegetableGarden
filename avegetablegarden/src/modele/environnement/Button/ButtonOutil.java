package modele.environnement.Button;

import modele.SimulateurPotager;
import modele.outils.Outil;
import modele.outils.TypeOutil;

public class ButtonOutil extends Button{
    private TypeOutil typeOutil;
    public ButtonOutil(TypeOutil type) {
        super();
        typeOutil = type;
    }
    public TypeOutil getTypeOutil (){
        return typeOutil;
    }
    @Override
    public void actionUtilisateur(){
        System.out.println("Je viens de cliquer sur une case de mes outils");
        if (estActif){
            estActif = false;
        }else {
            estActif = true;
        }
    }
    @Override
    public void run() {

    }
}
