package fr.lomy.Physique;



public class Horloge {
    private Integer temps;

    public Horloge(Integer temps) {
        this.temps = temps;
    }

    public Integer getTemps() {
        return temps;
    }
    
    public void incremente(){
        this.temps += 1;
    }
}
