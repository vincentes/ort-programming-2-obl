/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src.models;

import mavi.ort.edu.uy.src.constants.ConsoleColor;
import mavi.ort.edu.uy.src.utils.PrettyPrint;

/**
 *
 * @author vicentebermudez
 */
public class Board {
    public Disc[][] discs = new Disc[8][8];    
    
    public void print() {
        System.out.println("1 2 3 4 5 6");
        System.out.print("   ");
        String rowSeparator = "+-+-+-+-+-+-+";
        PrettyPrint.println(rowSeparator, ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
        for(int i = 1; i < discs.length - 1; i++) {
            Disc borderDisc = discs[i][1];
            System.out.print("" + i);
            PrettyPrint.print(" X", borderDisc.getConsoleColor());
            // this.printDisc(borderDisc);
            int discsLength = discs[i].length;
            for(int j = 1; j < discsLength - 1; j++) {
                Disc disc = discs[i][j];
                System.out.print("|");
                
                if(disc != null) {
                    PrettyPrint.print("X", disc.getConsoleColor());
                } else {
                    System.out.print(" ");
                }
            }
            
            System.out.print("|");
            
            Disc rightBorderDisc = discs[i][discs.length - 2];
            this.printDisc(rightBorderDisc);
            System.out.print(i);
            PrettyPrint.println(rowSeparator.charAt(0), ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
            }
        
        System.out.println("1 2 3 4 5 6");
    }
    
    public void printDisc(Disc disc) {
        if(disc != null) {
            PrettyPrint.print("X", disc.getConsoleColor());
        } else {
            System.out.print(" ");
        }
    }
    
}
