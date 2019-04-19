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
public class Segment {
    public Segment(Point s1, Point s2){
        this.leftP = s1;
        this.rightP = s2;
    }

    // Return left point
    public Point leftPoint(){
        return leftP;
    }

    // Return right point
    public Point rightPoint(){
        return rightP;
    }

    @Override
    public String toString(){
        return "Segment Points: \n Point 1: " + leftP.toString() + "\n Point 2: " + rightP.toString();
    }

    private Point leftP;
    private Point rightP;
}
