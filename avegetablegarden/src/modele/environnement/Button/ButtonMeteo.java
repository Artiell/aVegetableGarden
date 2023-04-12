package modele.environnement.Button;

import modele.TypeMeteo;

public class ButtonMeteo extends Button{
    private TypeMeteo typeMeteo;
    public ButtonMeteo(TypeMeteo type) {
        super();
        typeMeteo = type;
    }
    public TypeMeteo getTypeMeteo (){
        return typeMeteo;
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
