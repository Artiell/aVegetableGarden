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

import modele.SimulateurGraines;
import modele.SimulateurOutil;
import modele.SimulateurPotager;
import modele.environnement.Button.ButtonOutil;
import modele.environnement.Case.CaseCultivable;
import modele.environnement.Button.ButtonGraine;
import modele.environnement.Case.CaseMur;
import modele.environnement.Case.CaseNonCultivable;
import modele.environnement.Case.CaseNonRatisser;
import modele.environnement.Legume.varietes.Legume;
import modele.environnement.Legume.varietes.Varietes;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle
 *
 */
public class VueControleurPotager extends JFrame implements Observer {
    private SimulateurPotager simulateurPotager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private SimulateurGraines simulateurGraines;
    private SimulateurOutil simulateurOutil;

    private int sizeX; // taille de la grille affichée
    private int sizeY;
    private int NbVariete;
    private int NbOutils;

    // icones affichées dans la grille
    private ImageIcon icoJeuneSalade;
    private ImageIcon icoSalade;
    private ImageIcon icoSaladePourri;
    private ImageIcon icoJeuneCarotte;
    private ImageIcon icoCarotte;
    private ImageIcon icoCarottePourri;
    private ImageIcon icoTerre;
    private ImageIcon icoVide;
    private ImageIcon icoMur;
    private ImageIcon icoGerme;
    //private ImageIcon icoPelle;
    private ImageIcon gardenFence;
    private ImageIcon icoBuisson;
    private ImageIcon icoBoutonSalade;
    private ImageIcon icoBoutonAppuyerSalade;
    private ImageIcon icoBoutonAppuyerCarotte;
    private ImageIcon icoBoutonCarotte;
    private ImageIcon icoBoutonPelle;
    private ImageIcon icoBoutonAppuyerPelle;
    private ImageIcon icoBoutonRateau;
    private ImageIcon icoBoutonAppuyerRateau;
    private ImageIcon icoBoutonBotte;
    private ImageIcon icoBoutonAppuyerBotte;
    private ImageIcon icoBoutonPoubelle;
    private ImageIcon icoBoutonAppuyerPoubelle;
    private ImageIcon icoPauseButton;
    private ImageIcon icoPlayButton;
    private ImageIcon icoLeftArrowButton;
    private ImageIcon icoRightArrowButton;


    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)
    private JLabel[][] tabOutils;
    private JLabel[][] tabInventaire;
    private JLabel[][] tabGraines;

    public VueControleurPotager(SimulateurPotager _simulateurPotager) {
        sizeX = simulateurPotager.SIZE_X;
        sizeY = _simulateurPotager.SIZE_Y;

        simulateurPotager = _simulateurPotager;
        simulateurGraines = simulateurPotager.getSimulateurGraines();
        simulateurOutil = simulateurPotager.getSimulateurOutil();

        NbVariete = simulateurGraines.NB_VARIETE_MAX;
        NbOutils = simulateurOutil.NB_OUTIL_MAX;

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
    
        // Il faut rajouter SaladePourri
        icoJeuneSalade = chargerIcone("Images/spriteTerrain/Salade/dirtCenterPousse.png", 0, 0, 50, 50);//chargerIcone("Images/Pacman.png");
        icoSalade = chargerIcone("Images/spriteTerrain/Salade/dirtCenterSalade.png", 0, 0, 50, 50);//chargerIcone("Images/Pacman.png");
        icoSaladePourri = chargerIcone("Images/spriteTerrain/Salade/SaladePourri.png");
        icoGerme = chargerIcone("Images/spriteTerrain/dirtCenterGerme.png", 0, 0, 50, 50);//chargerIcone("Images/Pacman.png");
        icoVide = chargerIcone("Images/Vide.png");
        icoMur = chargerIcone("Images/Mur.png");
        icoTerre = chargerIcone("Images/spriteTerrain/dirtCenter.png", 0, 0, 50, 50);
        icoJeuneCarotte = chargerIcone("Images/spriteTerrain/Carotte/dirtCenterPousseCarotte.png", 0, 0, 50, 50);//chargerIcone("Images/Pacman.png");
        icoCarotte = chargerIcone("Images/spriteTerrain/Carotte/dirtCenterCarotte.png", 0, 0, 50, 50);//chargerIcone("Images/Pacman.png");
        icoCarottePourri = chargerIcone("Images/spriteTerrain/Carotte/dirtCenterCarottePourri.png");

        //icoPelle = chargerIcone("Images/spriteTerrain/shovel.png", 0, 0, 50,50);
        //icoSaladeSansFond = chargerIcone("Images/saladeSansFond.png");
        icoBoutonSalade = chargerIcone("Images/Bouton/BouttonSalade.png");
        icoBoutonAppuyerSalade = chargerIcone("Images/BoutonAppuyer/BouttonAppuyerSalade.png");
        icoBoutonCarotte = chargerIcone("Images/Bouton/BoutonCarotte.png");
        icoBoutonAppuyerCarotte = chargerIcone("Images/BoutonAppuyer/BoutonAppuyerCarotte.png");

        this.gardenFence = this.chargerIcone("Images/spriteTerrain/gardenFence.png");

        this.icoLeftArrowButton = this.chargerIcone("Images/leftArrowButton.png");
        this.icoRightArrowButton = this.chargerIcone("Images/rightArrowButton.png");
        this.icoPauseButton = this.chargerIcone("Images/pauseButton.png");
        this.icoPlayButton = this.chargerIcone("Images/playButton.png");
        icoBoutonPelle = chargerIcone("Images/Bouton/BoutonPelle.png");
        icoBoutonAppuyerPelle = chargerIcone("Images/BoutonAppuyer/BoutonAppuyerPelle.png");
        icoBoutonRateau = chargerIcone("Images/Bouton/BoutonRateau.png");
        icoBoutonAppuyerRateau = chargerIcone("Images/BoutonAppuyer/BoutonAppuyerRateau.png");
        icoBoutonBotte = chargerIcone("Images/Bouton/BoutonBotte.png");
        icoBoutonAppuyerBotte = chargerIcone("Images/BoutonAppuyer/BoutonAppuyerBotte.png");
        icoBoutonPoubelle = chargerIcone("Images/Bouton/BoutonPoubelle.png");
        icoBoutonAppuyerPoubelle = chargerIcone("Images/BoutonAppuyer/BoutonAppuyerPoubelle.png");
        icoBuisson = chargerIcone("Images/spriteTerrain/bush1.png");

    }

    private void placerLesComposantsGraphiques() {
        setTitle("A vegetable garden");
        setSize(1365, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        //Permet l'affichage de la partie de droite
        JLabel[][] infosDroite = new JLabel[3][1];
        JComponent grilleInfo = new JPanel(new GridLayout(4, 1));

       // Correspond à l'affichage d'info diverses
        infosDroite[0][0] = new JLabel();
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
        JLabel[][] infosGauche = new JLabel[3][1];
        JComponent grilleInfosGauche = new JPanel(new GridLayout(4, 1));

        // Correspond à l'affichage d'info diverses
        infosGauche[0][0] = new JLabel();
        JTextField jtf3 = new JTextField("Choix des graines"); // TODO inclure dans mettreAJourAffichage ...
        jtf3.setHorizontalAlignment(JTextField.CENTER);
        jtf3.setEditable(false);
        grilleInfosGauche.add(jtf3);

        // Permet l'affichage de la grille d'outils à droite
        int size_x_graine = simulateurGraines.NB_VARIETE_MAX;
        int size_y_graine = 1;
        JComponent grilleGraine = new JPanel(new GridLayout(size_y_graine, size_x_graine));
        tabGraines = new JLabel[size_y_graine][size_x_graine];

        for (int i=0; i<size_y_graine; i++){
            for (int j= 0; j<size_x_graine; j++){
                tabGraines[i][j] = new JLabel();
                grilleGraine.add(tabGraines[i][j]);
            }
        }
        grilleInfosGauche.add(grilleGraine);
        add(grilleInfosGauche, BorderLayout.WEST);

    // Affichage Partie du milieu
        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();

                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels, BorderLayout.CENTER);

        // écouter les évènements

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                tabJLabel[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        simulateurPotager.actionUtilisateur(xx, yy);
                    }
                });
            }
        }
        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < NbVariete; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                tabGraines[y][x].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        simulateurOutil.updateOutilActif();

                        if(simulateurOutil.isOutilActif()){
                            simulateurOutil.setAllOutilFalse();

                        }
                        simulateurGraines.actionUtilisateur(yy, xx);
                    }
                });
            }
        }

        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < NbOutils; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                tabOutils[y][x].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        simulateurGraines.updateGraineActif();

                        if(simulateurGraines.isGraineActif()){
                            simulateurGraines.setGraineAllFalse();
                        }
                        simulateurOutil.actionUtilisateur(yy, xx);
                    }
                });
            }
        }

        // Affichage de la partie du bas concernant la vitesse d'éxécution de la simulation

        JComponent speedGrille = new JPanel(new GridBagLayout());
        JTextField jtf4 = new JTextField("Vitesse de jeu :" + "ici sera présent la vitesse d'éxécution du jeu");
        jtf4.setOpaque(false);
        jtf4.setEditable(false);
        jtf4.setHorizontalAlignment(JTextField.CENTER);
        jtf4.setBorder(null);

        speedGrille.add(jtf4);
        JButton playButton = new JButton(this.icoPlayButton);
        JButton pauseButton = new JButton(this.icoPauseButton);
        JButton leftArrowButton = new JButton(this.icoLeftArrowButton);
        JButton rightArrowButton = new JButton(this.icoRightArrowButton);

        playButton.setContentAreaFilled(false);
        pauseButton.setContentAreaFilled(false);
        leftArrowButton.setContentAreaFilled(false);
        rightArrowButton.setContentAreaFilled(false);

        playButton.setBorderPainted(false);
        pauseButton.setBorderPainted(false);
        leftArrowButton.setBorderPainted(false);
        rightArrowButton.setBorderPainted(false);

        speedGrille.add(playButton);
        speedGrille.add(pauseButton);
        speedGrille.add(leftArrowButton);
        speedGrille.add(rightArrowButton);

        this.add(speedGrille, BorderLayout.SOUTH);


    }

    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {

        tabInventaire[0][0].setIcon(icoBoutonSalade) ;
        tabInventaire [1][0].setIcon(icoBoutonCarotte);
        tabInventaire[2][0].setIcon(icoMur);
        tabInventaire[3][0].setIcon(icoMur);
        tabInventaire[0][1].setText(String.valueOf(simulateurPotager.getTabInventaireLegume()[0]));
        tabInventaire[1][1].setText(String.valueOf(simulateurPotager.getTabInventaireLegume()[1]));
        //tabInventaire[0][3].setText(String.valueOf(simulateurPotager.getTabInventaireLegume()[2]));

        for (int y=0; y<1; y++){
            for (int x=0; x<NbVariete; x++){
                ButtonGraine graine = (ButtonGraine) simulateurGraines.getGrilleDesGraines()[y][x];
                switch (graine.getVariete()){
                    case salade :
                        if (graine.getActivite()){
                            tabGraines[y][x].setIcon(icoBoutonAppuyerSalade);break;
                        }else{
                            tabGraines[y][x].setIcon(icoBoutonSalade);break;
                        }
                    case carotte:
                        if (graine.getActivite()){
                            tabGraines[y][x].setIcon(icoBoutonAppuyerCarotte);break;
                        }else{
                            tabGraines[y][x].setIcon(icoBoutonCarotte);break;
                        }
                }
            }
        }

        for (int y=0; y<1; y++){
            for (int x=0; x<NbOutils; x++){
                ButtonOutil outil = (ButtonOutil) simulateurOutil.getGrilleDesOutils()[y][x];
                switch (outil.getTypeOutil()){
                    case pelle:
                        if (outil.getActivite()){
                            tabOutils[y][x].setIcon(icoBoutonAppuyerPelle);break;
                        }else{
                            tabOutils[y][x].setIcon(icoBoutonPelle);break;
                        }
                    case rateau:
                        if (outil.getActivite()){
                            tabOutils[y][x].setIcon(icoBoutonAppuyerRateau);break;
                        }else{
                            tabOutils[y][x].setIcon(icoBoutonRateau);break;
                        }
                    case botte:
                        if (outil.getActivite()){
                            tabOutils[y][x].setIcon(icoBoutonAppuyerBotte);break;
                        }else{
                            tabOutils[y][x].setIcon(icoBoutonBotte);break;
                        }
                    case poubelle:
                        if (outil.getActivite()){
                            tabOutils[y][x].setIcon(icoBoutonAppuyerPoubelle);break;
                        }else{
                            tabOutils[y][x].setIcon(icoBoutonPoubelle);break;
                        }
                }
            }
        }

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (simulateurPotager.getPlateau()[x][y] instanceof CaseCultivable) { // si la grille du modèle contient un Pacman, on associe l'icône Pacman du côté de la vue

                    Legume legume = ((CaseCultivable) simulateurPotager.getPlateau()[x][y]).getLegume();

                    if (legume != null) {

                        switch (legume.getVariete()) {
                            case salade: switch (legume.getEtatLegume()){
                                case germe : tabJLabel[x][y].setIcon(icoGerme); break;
                                case jeune : tabJLabel[x][y].setIcon(icoJeuneSalade); break;
                                case mature: tabJLabel[x][y].setIcon(icoSalade); break;
                                case pourri: tabJLabel[x][y].setIcon(icoSaladePourri); break;
                            } break;
                            case carotte: switch (legume.getEtatLegume()){
                                case germe : tabJLabel[x][y].setIcon(icoGerme); break;
                                case jeune : tabJLabel[x][y].setIcon(icoJeuneCarotte); break;
                                case mature: tabJLabel[x][y].setIcon(icoCarotte); break;
                                case pourri: tabJLabel[x][y].setIcon(icoCarottePourri); break;
                            } break;
                        }

                    } else {
                        tabJLabel[x][y].setIcon(icoTerre);
                    }

                    // si transparence : images avec canal alpha + dessins manuels (voir ci-dessous + créer composant qui redéfinie paint(Graphics g)), se documenter
                    //BufferedImage bi = getImage("Images/smick.png", 0, 0, 20, 20);
                    //tabJLabel[x][y].getGraphics().drawImage(bi, 0, 0, null);
                } else if (simulateurPotager.getPlateau()[x][y] instanceof CaseMur) {
                    tabJLabel[x][y].setIcon(gardenFence);
                } else if (simulateurPotager.getPlateau()[x][y] instanceof CaseNonRatisser) {
                    tabJLabel[x][y].setIcon(icoBuisson);
                }else {
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
