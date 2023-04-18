package modele;

public class SimulateurTemps {
    //singleton
    private static SimulateurTemps instance = null;
    private int vitesseAvantPause;
    private int vitesseSimulation = 1;
    private boolean isStop;
    private int ms;
    private int s;

    public int getVitesseSimulation() {
        return vitesseSimulation;
    }

    private void setVitesseSimulation(int vitesseSimulation) {
        this.vitesseSimulation = vitesseSimulation;
    }
    //Représente les ms, secondes, minutes, heurs, jours écoulés depuis le début de la simulation

    public int getMs() {
        return ms;
    }
    public int getS() {
        return s;
    }

    private SimulateurTemps() {
        this.ms = 0;
        this.s = 0;
        this.vitesseAvantPause = 0;
        this.vitesseSimulation = 1;
        this.isStop = false;
    }

    public static SimulateurTemps getSimuTemps() {
        if (instance == null) {
            instance = new SimulateurTemps();
        }
        return instance;
    }

    public void resetTemps (){
        this.ms = 0;
        this.s = 0;
        this.vitesseAvantPause = 0;
        this.vitesseSimulation = 1;
        this.isStop = false;
    }

    public void Incr(int time){
        this.ms += time;
        if (this.ms >= 1000) {
            this.ms = 0;
            this.s += vitesseSimulation;
        }
    }

    public int getMinu(){
        return this.s/60;
    }

    public int getHour(){
        return this.s/3600;
    }


    public void stop(){
        isStop = true;
        this.vitesseAvantPause = vitesseSimulation;
        vitesseSimulation = 0;
    }

    public void play(){
        if(this.isStop){
            this.vitesseSimulation = this.vitesseAvantPause;
        }
        isStop = false;
    }

    public void accelerer(){
        switch (vitesseSimulation) {
            case 1 -> this.setVitesseSimulation(2);
            case 2 -> this.setVitesseSimulation(5);
            case 5 -> this.setVitesseSimulation(10);
            case 10 -> this.setVitesseSimulation(50);
            default -> this.setVitesseSimulation(this.vitesseSimulation);
        }
    }
    public void decelerer(){
        switch (vitesseSimulation) {
            case 50 -> this.setVitesseSimulation(10);
            case 10 -> this.setVitesseSimulation(5);
            case 5 -> this.setVitesseSimulation(2);
            case 2 -> this.setVitesseSimulation(1);
            default -> this.setVitesseSimulation(this.vitesseSimulation);
        }
    }

}
