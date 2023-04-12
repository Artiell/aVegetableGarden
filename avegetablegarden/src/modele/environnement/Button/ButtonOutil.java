package modele.environnement.Button;

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
    public void actionUtilisateur() {
        if (estActif) {
            estActif = false;
        } else {
            estActif = true;
        }
    }
}
