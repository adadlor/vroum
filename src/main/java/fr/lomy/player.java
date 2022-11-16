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
public class player {
    private Integer priorite;// pour l'ordre de jeu
    private Integer x;
    private Integer y;
    
    public ArrayList<Integer> getPosition(){
        ArrayList<Integer> p = new ArrayList<Integer>();
        p.add(x);
        p.add(y);
        return p;
    }
    
    public Integer getPrio(){
        return this.priorite;
    }
    
    public boolean estHorsPiste(){
        if (this.getPosition in listDecors){ // pas encore les nom exacte
            return true;
        }
        else{
                return false;
        }
    }
}
