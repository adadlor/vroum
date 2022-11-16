package fr.lomy.Jeu;


import java.util.ArrayList;

public class Player {
    private Integer priorite;// pour l'ordre de jeu
    private Integer x;
    private Integer y;

    public Player(Integer priorite, Integer x, Integer y) {
        this.priorite = priorite;
        this.x = x;
        this.y = y;
    }

    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }
    
    public ArrayList<Integer> getPosition(){
        ArrayList<Integer> p = new ArrayList<>();
        p.add(x);
        p.add(y);
        return p;
    }
    
    public Integer getPrio(){
        return this.priorite;
    }
        /*
    public boolean estHorsPiste(){
        if (this.getPosition in listDecors){ // pas encore les nom exacte
            return true;
        }
        else{
                return false;
        }
    }

         */
}

