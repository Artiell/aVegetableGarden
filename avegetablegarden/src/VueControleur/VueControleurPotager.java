package VueControleur;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import modele.*;
import modele.environnement.Button.ButtonMeteo;
import modele.environnement.Case.CaseCultivable;
import modele.environnement.Case.CaseMur;
import modele.environnement.Case.CaseNonRatisser;
import modele.environnement.Legume.Legume;
import modele.environnement.Legume.varietes.Varietes;
import modele.fonctionnalite.outils.*;
import modele.fonctionnalite.plantes.PlanterGraineCarotte;
import modele.fonctionnalite.plantes.PlanterGraineSalade;
import modele.fonctionnalite.plantes.PlanterGraineTomate;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle
 *
 */
public class VueControleurPotager extends JFrame implements Observer {
    private SimulateurPotager simulateurPotager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    private final int NbVariete;
    private final int NbOutils;

    // icones affichées dans la grille
    private ImageIcon[][] icoSalade;
    private ImageIcon[][] icoCarotte;
    private ImageIcon[][] icoTomate;
    private ImageIcon[] icoTerre;
    private ImageIcon[][] icoOutils;
    private ImageIcon[][] icoGraine;
    private ImageIcon[][] icoMeteo;
    private ImageIcon icoVide;
    private ImageIcon[][] gardenFence;
    private ImageIcon[] icoBuisson;
    private ImageIcon icoPauseButton;
    private ImageIcon icoPlayButton;
    private ImageIcon icoResetButton;
    private ImageIcon icoLeftArrowButton;
    private ImageIcon icoRightArrowButton;
    private ImageIcon icoPiece;


    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)
    private JLabel[][] tabOutils;
    private JLabel[][] tabInventaire;
    private JLabel[] tabMeteo;
    private JLabel[][] tabGraines;
    private JLabel[] nbPiece;
    JTextField vitesseDuJeu;
    JTextField tempsDuJeu;
    JTextField pourcentageHumidite;
    JTextField message;

    public VueControleurPotager(SimulateurPotager _simulateurPotager) {
        simulateurPotager = _simulateurPotager;

        NbVariete = Varietes.values().length;
        NbOutils = TypeOutil.values().length;

        chargerLesIcones();
        placerLesComposantsGraphiques();
        //ajouterEcouteurClavier(); // si besoin
    }
/*
    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT : Controle4Directions.getInstance().setDirectionCourante(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : Controle4Directions.getInstance().setDirectionCourante(Direction.droite); break;
                    case KeyEvent.VK_DOWN : Controle4Directions.getInstance().setDirectionCourante(Direction.bas); break;
                    case KeyEvent.VK_UP : Controle4Directions.getInstance().setDirectionCourante(Direction.haut); break;
                }
            }
        });
    }
*/

    private void chargerLesIcones() {
        // image libre de droits utilisée pour les légumes : https://www.vecteezy.com/vector-art/2559196-bundle-of-fruits-and-vegetables-icons

        icoSalade = new ImageIcon[4][3];
        icoCarotte = new ImageIcon[4][3];
        icoTomate = new ImageIcon[4][3];
        for (int j = 0; j< 4; j++) {
            for (int i = 0; i < 3; i++) {
                icoSalade[j][i] = new ImageIcon();
                icoCarotte[j][i] = new ImageIcon();
                icoTomate[j][i] = new ImageIcon();
                icoSalade[j][i] = chargerIcone("src/Images/spriteTerrain/Salade/" + i + "dirtCenterSalade" + j + ".png");
                icoCarotte[j][i] = chargerIcone("src/Images/spriteTerrain/Carotte/" + i + "dirtCenterCarotte" + j + ".png");
                icoTomate[j][i] = chargerIcone("src/Images/spriteTerrain/Tomate/" + i + "dirtCenterTomate" + j + ".png");
            }
        }
        icoTerre = new ImageIcon[3];
        icoBuisson = new ImageIcon[3];
        for (int i = 0; i < 3; i++) {
            icoTerre[i] = new ImageIcon();
            icoBuisson[i] = new ImageIcon();
            icoTerre[i] = chargerIcone("src/Images/spriteTerrain/dirtCenter"+i+".png");
            icoBuisson[i] = chargerIcone("src/Images/spriteTerrain/"+i+"buisson.png");
        }

        icoOutils = new ImageIcon[4][2];
        for (int j = 0; j< 4; j++) {
            for (int i = 0; i < 2; i++) {
                icoOutils[j][i] = new ImageIcon();
                icoOutils[j][i] = chargerIcone("src/Images/Outils/Outils"+j+"Etat"+ i + ".png");
            }
        }

        gardenFence = new ImageIcon[3][8];
        for (int j = 0; j< 8; j++) {
            for (int i = 0; i < 3; i++) {
                gardenFence[i][j] = new ImageIcon();
                gardenFence[i][j] = chargerIcone("src/Images/spriteTerrain/"+i+"fence"+ j + ".png");
            }
        }

        icoGraine = new ImageIcon[NbVariete][2];
        for (int j = 0; j< 3; j++) {
            for (int i = 0; i < 2; i++) {
                icoGraine[j][i] = new ImageIcon();
                icoGraine[j][i] = chargerIcone("src/Images/Graines/Graine"+j+"Etat"+ i + ".png");
            }
        }

        icoMeteo = new ImageIcon[3][2];
        for (int j = 0; j< 3; j++) {
            for (int i = 0; i < 2; i++) {
                icoMeteo[j][i] = new ImageIcon();
                icoMeteo[j][i] = chargerIcone("src/Images/Meteo/Meteo"+j+"Etat"+ i + ".png");
            }
        }

        icoVide = chargerIcone("src/Images/Vide.png");

        this.icoLeftArrowButton = this.chargerIcone("src/Images/leftArrowButton.png");
        this.icoRightArrowButton = this.chargerIcone("src/Images/rightArrowButton.png");
        this.icoPauseButton = this.chargerIcone("src/Images/pauseButton.png");
        this.icoPlayButton = this.chargerIcone("src/Images/playButton.png");
        icoResetButton = chargerIcone("src/Images/resetbutton.png");
        icoPiece = chargerIcone("src/Images/icoPiece.png");
    }

    private void placerLesComposantsGraphiques() {
        setTitle("A vegetable garden");
        setSize(1365, 890);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        //Permet l'affichage de la partie de droite
        JComponent grilleInfo = new JPanel(new GridLayout(4, 1));

       // Correspond à l'affichage d'info diverses
        JTextField jtf = new JTextField("infos diverses"); // TODO inclure dans mettreAJourAffichage ...
        jtf.setHorizontalAlignment(JTextField.CENTER);
        jtf.setEditable(false);
        grilleInfo.add(jtf);

        // Permet l'affichage de la grille d'outils à droite
        int _x = NbOutils;
        int _y = 1;
        JComponent grilleOutils = new JPanel(new GridLayout(_y, _x));
        tabOutils = new JLabel[_y][_x];

        for (int i=0; i<_y; i++){
            for (int j= 0; j<_x; j++){
                tabOutils[i][j] = new JLabel();
                grilleOutils.add(tabOutils[i][j]);
            }
        }
        grilleInfo.add(grilleOutils);

        // Affichage de l'inventaire
        JTextField jtf2 = new JTextField("Inventaires des récoltes"); // TODO inclure dans mettreAJourAffichage ...
        jtf2.setHorizontalAlignment(JTextField.CENTER);
        jtf2.setEditable(false);
        grilleInfo.add(jtf2);

        int _x1 = 2;
        int _y1 = 4;
        JComponent grilleInventaire = new JPanel(new GridLayout(_y1, _x1));
        tabInventaire = new JLabel[_y1][_x1];

        for (int i=0; i<_y1; i++){
            for (int j= 0; j<_x1; j++){
                tabInventaire[i][j] = new JLabel();
                grilleInventaire.add(tabInventaire[i][j]);
            }
        }
        grilleInfo.add(grilleInventaire);

        // met à droite notre grilleInfo
        add(grilleInfo, BorderLayout.EAST);


        //Permet l'affichage de la partie de gauche
        JComponent grilleInfosGauche = new JPanel(new GridLayout(5, 1));

        // Correspond à l'affichage d'info diverses
        JTextField jtf3 = new JTextField("Choix des graines"); // TODO inclure dans mettreAJourAffichage ...
        jtf3.setHorizontalAlignment(JTextField.CENTER);
        jtf3.setEditable(false);
        grilleInfosGauche.add(jtf3);

        // Permet l'affichage de la grille d'outils à droite
        int size_x_graine = NbVariete;
        int size_y_graine = 3;
        JComponent grilleGraine = new JPanel(new GridLayout(size_x_graine, size_y_graine));
        tabGraines = new JLabel[size_x_graine][size_y_graine];

        for (int i=0; i<size_x_graine; i++){
            for (int j= 0; j<size_y_graine; j++){
                tabGraines[i][j] = new JLabel();
                grilleGraine.add(tabGraines[i][j]);
            }
        }
        // On met les prix qu'on a définis dans le magasin
        for (int i = 0; i<NbVariete; i++){
            tabGraines[i][1].setText("         "+simulateurPotager.getMagasin().getTabPrix()[i]);
        }
        for (int x = 0; x < NbVariete; x++) {
            tabGraines[x][2].setIcon(icoPiece);
        }

        grilleInfosGauche.add(grilleGraine);

        JTextField jtf4 = new JTextField("Nombre de pièces"); // TODO inclure dans mettreAJourAffichage ...
        jtf4.setHorizontalAlignment(JTextField.CENTER);
        jtf4.setEditable(false);
        grilleInfosGauche.add(jtf4);

        JComponent grillePiece = new JPanel();
        nbPiece = new JLabel[2];
        for (int i=0; i<2; i++) {
            nbPiece[i] = new JLabel();
            grillePiece.add(nbPiece[i]);
        }
        nbPiece[1].setIcon(icoPiece);
        grilleInfosGauche.add(grillePiece);

        message=new JTextField();
        message.setBorder(null);
        message.setBackground(null);
        message.setHorizontalAlignment(JTextField.CENTER);
        grilleInfosGauche.add(message);

        add(grilleInfosGauche, BorderLayout.WEST);

    // Affichage Partie du milieu
        JComponent grilleJLabels = new JPanel(new GridLayout(simulateurPotager.getSIZE_Y(), simulateurPotager.getSIZE_X())); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[simulateurPotager.getSIZE_X()][simulateurPotager.getSIZE_Y()];

        for (int y = 0; y < simulateurPotager.getSIZE_Y(); y++) {
            for (int x = 0; x < simulateurPotager.getSIZE_X(); x++) {
                JLabel jlab = new JLabel();

                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels, BorderLayout.CENTER);

        // écouter les évènements

        for (int y = 0; y < simulateurPotager.getSIZE_Y(); y++) {
            for (int x = 0; x < simulateurPotager.getSIZE_X(); x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                tabJLabel[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!simulateurPotager.getFinPartie() && SimulateurTemps.getSimuTemps().getVitesseSimulation() != 0){
                            simulateurPotager.actionUtilisateur(xx, yy);
                        }
                    }
                });
            }
        }
        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < NbVariete; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                tabGraines[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!simulateurPotager.getFinPartie() && SimulateurTemps.getSimuTemps().getVitesseSimulation() != 0){
                            simulateurPotager.actionUtilisateurGraines(xx);
                        }

                    }
                });
            }
        }

        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < NbOutils; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                tabOutils[y][x].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!simulateurPotager.getFinPartie() && SimulateurTemps.getSimuTemps().getVitesseSimulation() != 0){
                            simulateurPotager.actionUtilisateurOutils(xx);
                        }
                    }
                });
            }
        }

        // Affichage de la partie du bas concernant la vitesse d'éxécution de la simulation

        JComponent speedGrille = new JPanel(new GridBagLayout());
        vitesseDuJeu = new JTextField();
        vitesseDuJeu.setOpaque(false);
        vitesseDuJeu.setEditable(false);
        vitesseDuJeu.setHorizontalAlignment(JTextField.CENTER);
        vitesseDuJeu.setBorder(null);

        speedGrille.add(vitesseDuJeu);
        JButton resetButton = new JButton(this.icoResetButton);
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                simulateurPotager.reset();
                //System.out.println("le systeme est en x" + SimulateurTemps.getSimuTemps().getVitesseSimulation());
            }
        });

        JButton playButton = new JButton(this.icoPlayButton);
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!simulateurPotager.getFinPartie()){
                    SimulateurTemps.getSimuTemps().play();
                }
                //System.out.println("le systeme est en x" + SimulateurTemps.getSimuTemps().getVitesseSimulation());
            }
        });

        JButton pauseButton = new JButton(this.icoPauseButton);
        pauseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!simulateurPotager.getFinPartie()){
                    SimulateurTemps.getSimuTemps().stop();
                }
                //System.out.println("le systeme est en x" + SimulateurTemps.getSimuTemps().getVitesseSimulation());
            }
        });

        JButton leftArrowButton = new JButton(this.icoLeftArrowButton);
        leftArrowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!simulateurPotager.getFinPartie() && SimulateurTemps.getSimuTemps().getVitesseSimulation() != 0){
                    SimulateurTemps.getSimuTemps().decelerer();
                }
                //System.out.println("le systeme est en x" + SimulateurTemps.getSimuTemps().getVitesseSimulation());
            }
        });

        JButton rightArrowButton = new JButton(this.icoRightArrowButton);
        rightArrowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!simulateurPotager.getFinPartie() && SimulateurTemps.getSimuTemps().getVitesseSimulation() != 0){
                    SimulateurTemps.getSimuTemps().accelerer();
                }
                //System.out.println("le systeme est en x" + SimulateurTemps.getSimuTemps().getVitesseSimulation());
            }
        });

        resetButton.setContentAreaFilled(false);
        playButton.setContentAreaFilled(false);
        pauseButton.setContentAreaFilled(false);
        leftArrowButton.setContentAreaFilled(false);
        rightArrowButton.setContentAreaFilled(false);

        resetButton.setBorderPainted(false);
        playButton.setBorderPainted(false);
        pauseButton.setBorderPainted(false);
        leftArrowButton.setBorderPainted(false);
        rightArrowButton.setBorderPainted(false);

        speedGrille.add(resetButton);
        speedGrille.add(playButton);
        speedGrille.add(pauseButton);
        speedGrille.add(leftArrowButton);
        speedGrille.add(rightArrowButton);

        tempsDuJeu = new JTextField();
        tempsDuJeu.setOpaque(false);
        tempsDuJeu.setEditable(false);
        tempsDuJeu.setHorizontalAlignment(JTextField.CENTER);
        tempsDuJeu.setBorder(null);

        speedGrille.add(tempsDuJeu);

        this.add(speedGrille, BorderLayout.SOUTH);

        // Affichage Partie du Nord

        JComponent MeteoGrille = new JPanel(new GridLayout());

        int sizeMeteo = simulateurPotager.getSimulateurMeteo().getNB_METEO_MAX();
        JPanel panelMeteo = new JPanel();
        tabMeteo = new JLabel[sizeMeteo];

        for (int i = 0; i<sizeMeteo; i++){
            tabMeteo[i] = new JLabel();
            panelMeteo.add(tabMeteo[i]);
        }

        for (int x = 0; x <sizeMeteo; x++) {
            final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
            tabMeteo[x].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!simulateurPotager.getFinPartie() && SimulateurTemps.getSimuTemps().getVitesseSimulation() != 0){
                        simulateurPotager.getSimulateurMeteo().actionUtilisateur(xx);
                    }
                }
            });
        }

        MeteoGrille.add(panelMeteo);

        pourcentageHumidite = new JTextField();
        pourcentageHumidite.setOpaque(false);
        pourcentageHumidite.setEditable(false);
        pourcentageHumidite.setHorizontalAlignment(JTextField.CENTER);
        pourcentageHumidite.setBorder(null);
        MeteoGrille.add(pourcentageHumidite);

        add(MeteoGrille, BorderLayout.NORTH);

    }

    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {
        //Mise à jour de l'accélération et du temps
        vitesseDuJeu.setText("Vitesse de jeu : x" + SimulateurTemps.getSimuTemps().getVitesseSimulation() + "   ");
        tempsDuJeu.setText(SimulateurTemps.getSimuTemps().getHour()+ "  :  "+ SimulateurTemps.getSimuTemps().getMinu()%60+"  :  "
                + SimulateurTemps.getSimuTemps().getS()%60 + "       ");

        //Mise à jour du pourcentage d'humidite
        pourcentageHumidite.setText("Pourcentage d'humidité : "+ simulateurPotager.getSimulateurMeteo().getHumidite()+ "   ");

        // Affiche les boutons de la météo
        for (int x = 0; x < simulateurPotager.getSimulateurMeteo().getNB_METEO_MAX(); x++) {
            ButtonMeteo meteo = (ButtonMeteo) simulateurPotager.getSimulateurMeteo().getGrilleDeMeteo()[x];
            int i=0;
            int j = -1;
            switch (meteo.getTypeMeteo()) {
                case soleil : j=0;break;
                case nuage : j=1;break;
                case pluie : j=2;break;
            }
            if (meteo.getActivite()) {
                i=1;
            }
            tabMeteo[x].setIcon(icoMeteo[j][i]);
        }

        // Affiche l'inventaire
        for (int x = 0; x < NbVariete; x++) {
            tabInventaire[x][0].setIcon(icoGraine[x][0]);
            tabInventaire[x][1].setText(String.valueOf(simulateurPotager.getTabInventaireLegume()[x]));
        }

        // Affiche la partie sur les sprite des graine
        for (int x = 0; x < NbVariete; x++) {
            int j = 0;
            if (simulateurPotager.getFonctionnalite() instanceof PlanterGraineSalade && x == 0) j=1;
            if (simulateurPotager.getFonctionnalite() instanceof PlanterGraineCarotte && x == 1) j=1;
            if (simulateurPotager.getFonctionnalite() instanceof PlanterGraineTomate && x == 2) j=1;
            tabGraines[x][0].setIcon(icoGraine[x][j]);
        }

        nbPiece[0].setText("    "+ simulateurPotager.getMagasin().getNbPiece());

        message.setText(simulateurPotager.getMagasin().getMessage());

        // Affiche les sprites des outils
        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < NbOutils; x++) {
                int j=0;
                if (simulateurPotager.getFonctionnalite() instanceof Pelle && x==0) j= 1;
                if (simulateurPotager.getFonctionnalite() instanceof Rateau && x==1) j= 1;
                if (simulateurPotager.getFonctionnalite() instanceof Botte && x==2) j= 1;
                if (simulateurPotager.getFonctionnalite() instanceof Poubelle && x==3) j= 1;

                tabOutils[y][x].setIcon(icoOutils[x][j]);
            }
        }

        for (int x = 0; x < simulateurPotager.getSIZE_X(); x++) {
            for (int y = 0; y < simulateurPotager.getSIZE_Y(); y++) {
                int j = -1;
                switch (simulateurPotager.getSol()) {
                    case normal : j=0; break;
                    case sec : j=1;break;
                    case humide : j=2;break;
                }
                if (simulateurPotager.getPlateau()[x][y] instanceof CaseCultivable) { // si la grille du modèle contient un Pacman, on associe l'icône Pacman du côté de la vue

                    Legume legume = ((CaseCultivable) simulateurPotager.getPlateau()[x][y]).getLegume();
                    int i = -1;
                    if (legume != null) {
                        switch (legume.getEtatLegume()) {
                            case germe : i=0; break;
                            case jeune : i=1;break;
                            case mature : i=2;break;
                            case pourri : i=3;break;
                        }
                        switch (legume.getVariete()) {
                            case salade : tabJLabel[x][y].setIcon(icoSalade[i][j]);break;
                            case carotte : tabJLabel[x][y].setIcon(icoCarotte[i][j]);break;
                            case tomate : tabJLabel[x][y].setIcon(icoTomate[i][j]);break;
                        }

                    } else {
                        tabJLabel[x][y].setIcon(icoTerre[j]);
                    }

                    // si transparence : images avec canal alpha + dessins manuels (voir ci-dessous + créer composant qui redéfinie paint(Graphics g)), se documenter
                    //BufferedImage bi = getImage("Images/smick.png", 0, 0, 20, 20);
                    //tabJLabel[x][y].getGraphics().drawImage(bi, 0, 0, null)
                }else if (simulateurPotager.getPlateau()[x][y] instanceof CaseMur) {
                    int i = -1;
                    switch (((CaseMur) simulateurPotager.getPlateau()[x][y]).getTypeMur()) {
                        case tournantHautGauche : i=0;break;
                        case haut : i=1;break;
                        case tournantHautDroit : i=2;break;
                        case droit : i=3;break;
                        case tournantBasDroit : i=4;break;
                        case bas : i=5;break;
                        case tournantBasGauche : i=6;break;
                        case gauche : i=7;break;
                    };
                    tabJLabel[x][y].setIcon(gardenFence[j][i]);
                } else if (simulateurPotager.getPlateau()[x][y] instanceof CaseNonRatisser) {
                    tabJLabel[x][y].setIcon(icoBuisson[j]);
                } else {
                    tabJLabel[x][y].setIcon(icoVide);
                }
            }
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        mettreAJourAffichage();
        /*
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */

    }


    // chargement de l'image entière comme icone
    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            System.out.println(urlIcone);
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


        return new ImageIcon(image);
    }

    // chargement d'une sous partie de l'image
    private ImageIcon chargerIcone(String urlIcone, int x, int y, int w, int h) {
        // charger une sous partie de l'image à partir de ses coordonnées dans urlIcone
        BufferedImage bi = getSubImage(urlIcone, x, y, w, h);
        // adapter la taille de l'image a la taille du composant (ici : 20x20)
        return new ImageIcon(bi.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
    }

    private BufferedImage getSubImage(String urlIcone, int x, int y, int w, int h) {
        BufferedImage image = null;

        try {
            File f = new File(urlIcone);
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        BufferedImage bi = image.getSubimage(x, y, w, h);
        return bi;
    }

}
