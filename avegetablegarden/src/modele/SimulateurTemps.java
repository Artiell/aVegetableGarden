package modele;

public class SimulateurTemps {

    //singleton
    private static SimulateurTemps instance = null;

    //Représente les ms, secondes, minutes, heurs, jours écoulés depuis le début de la simulation
    private int ms;
    private int s;

    public int getMs() {
        return ms;
    }
    public int getS() {
        return s;
    }

    private SimulateurTemps() {
        this.ms = 0;
        this.s = 0;
    }

    public static SimulateurTemps getSimuTemps() {
        if (instance == null) {
            instance = new SimulateurTemps();
        }
        return instance;
    }

    public void Incr(int time){
        this.ms += time;
        if (this.ms >= 1000) {
            this.ms = 0;
            this.s += 1;
        }
    }

    public int getMinu(){
        return this.s/60;
    }

    public int getHour(){
        return this.s/3600;
    }

    public int getDay(){
        return this.s/86400;
    }





}
