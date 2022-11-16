package fr.lomy.Jeu;
import java.util.ArrayList;

public class Circuit {
    private ArrayList<ArrayList<Integer>> piste;
    private ArrayList<ArrayList<Integer>> horsPiste;

    public Circuit(ArrayList<ArrayList<Integer>> piste, ArrayList<ArrayList<Integer>> horsPiste) {
        this.piste = piste;
        this.horsPiste = horsPiste;
    }

    public ArrayList<ArrayList<Integer>> getPiste() {
        return piste;
    }

    public ArrayList<ArrayList<Integer>> getHorsPiste() {
        return horsPiste;
    }

    public void setPiste(ArrayList<ArrayList<Integer>> piste) {
        this.piste = piste;
    }

    public void setHorsPiste(ArrayList<ArrayList<Integer>> horsPiste) {
        this.horsPiste = horsPiste;}
    
    
    
}
