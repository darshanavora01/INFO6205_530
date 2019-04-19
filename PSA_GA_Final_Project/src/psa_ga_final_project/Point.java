/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa_ga_final_project;

/**
 *
 * @author darsh
 */
public class Point {
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public String toString(){
        return "Point Co-Ordinates: (" + x +"," + y + ")";
    }
    
    private int x;
    private int y;
}
