/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lomy;
import java.util.ArrayList;
/**
 *
 * @author valentinwendling
 */
public class circuit {
    private ArrayList<ArrayList<Integer>> piste;
    private ArrayList<ArrayList<Integer>> horsPiste;

    public circuit(ArrayList<ArrayList<Integer>> piste, ArrayList<ArrayList<Integer>> horsPiste) {
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
