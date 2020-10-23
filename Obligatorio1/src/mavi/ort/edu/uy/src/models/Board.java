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
    
    private static Disc randomDisc() {
        Disc disc = new Disc();
        if(Math.random() < 0.5) {
            disc.setColor(Color.BLUE);
        } else {
            disc.setColor(Color.RED);
        }
        return disc;
    }
    
    public Disc[][] getRandomBoard() {
        Disc[][] discs = new Disc[8][8];
        int redDiscs = 0;
        int blueDiscs = 0;
        int discLimitEach = 12;
        for(int i = 0; i < discs.length; i++) {
            for(int j = 0; j < discs[i].length; j++) {                
                Disc newDisc = new Disc();
                if(i == 0 || i == discs.length - 1) {
                    if(j == 0 || j == discs[i].length - 1) {
                        continue;
                    }

                    if(redDiscs < discLimitEach && blueDiscs < discLimitEach) {
                        discs[i][j] = randomDisc();
                        if(discs[i][j].getColor() == Color.BLUE) {
                            blueDiscs++;
                        } else {
                            redDiscs++;
                        }
                    } else {
                        if(redDiscs >= discLimitEach) {
                            discs[i][j] = new Disc(Color.BLUE);
                            blueDiscs++;
                        } else {
                            discs[i][j] = new Disc(Color.RED);
                            redDiscs++;
                        }
                    }
                    

                } else {
                    if(j == 0) {
                        discs[i][j] = randomDisc();
                    } else if(j == discs[i].length - 1) {
                        discs[i][j] = randomDisc();
                    }
                }
            }
        }
        return discs;
    }
    
    public Disc[][] getDefaultBoard() {
        Disc[][] discs = new Disc[8][8];
        for(int i = 0; i < discs.length; i++) {
            for(int j = 0; j < discs[i].length; j++) {                
                Disc newDisc = new Disc();
                if(i == 0 || i == discs.length - 1) {
                    if(j == 0 || j == discs[i].length - 1) {
                        continue;
                    }
                    
                    if(j % 2 == 0) {
                        newDisc.setColor(Color.RED);
                        discs[i][j] = newDisc;
                    } else {
                        newDisc.setColor(Color.BLUE);
                        discs[i][j] = newDisc;
                    }
                } else {
                    if(j == 0) {
                        if(i % 2 == 0) {
                            System.out.println("BLUE");
                            newDisc.setColor(Color.BLUE);
                            discs[i][j] = newDisc;
                        } else {
                            System.out.println("RED");
                            newDisc.setColor(Color.RED);
                            discs[i][j] = newDisc;
                        }
                    } else if(j == discs[i].length - 1) {
                        if((i + 1) % 2 == 0) {
                            System.out.println("BLUE");
                            newDisc.setColor(Color.BLUE);
                            discs[i][j] = newDisc;
                        } else {
                            System.out.println("RED");
                            newDisc.setColor(Color.RED);
                            discs[i][j] = newDisc;
                        }
                    }
                }
            }
        }
        return discs;
    }
    
    public static void print(Disc[][] discs) {
        System.out.println("    1 2 3 4 5 6");
        System.out.print("  ");
        for(int j = 0; j < discs[j].length - 1; j++) {
            Disc disc = discs[0][j];

            if(disc != null) {
                PrettyPrint.print(" X", disc.getConsoleColor());
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
        
        String rowSeparator = "+-+-+-+-+-+-+";
        PrettyPrint.print("   ");
        PrettyPrint.println(rowSeparator, ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
        
        for(int i = 1; i < discs.length - 1; i++) {
            System.out.print("   ");
            PrettyPrint.print("|", ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
            System.out.print("           ");
            PrettyPrint.print("|\n", ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
            
            Disc borderDisc = discs[i][0];
            System.out.print("" + i);
            if(borderDisc != null) {
                PrettyPrint.print(" X", borderDisc.getConsoleColor());
            } else {
                System.out.print("  ");
            }
            // this.printDisc(borderDisc);
            int discsLength = discs[i].length;
            for(int j = 1; j < discsLength - 1; j++) {
                Disc disc = discs[i][j];
                if(j == 1) {
                    PrettyPrint.print("|", ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
                } else {
                    System.out.print("|");
                }
                
                if(disc != null) {
                    PrettyPrint.print("X", disc.getConsoleColor());
                } else {
                    System.out.print(" ");
                }
            }
            
            PrettyPrint.print("|", ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);

            
            Disc rightBorderDisc = discs[i][discs.length - 1];
            if(rightBorderDisc != null) {
                PrettyPrint.print("X ", rightBorderDisc.getConsoleColor());
            } else {
                System.out.print(" ");
            }
            
            System.out.print(i);
            System.out.println();
            System.out.print("   ");
            PrettyPrint.print("|", ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
            System.out.print("           ");
            PrettyPrint.print("|", ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
            System.out.print("\n   ");
            PrettyPrint.println(rowSeparator, ConsoleColor.BLACK, ConsoleColor.GREEN_BACKGROUND);
        }
        
        System.out.print("  ");
        for(int j = 0; j < discs[j].length - 1; j++) {
            Disc disc = discs[discs.length - 1][j];

            if(disc != null) {
                PrettyPrint.print(" X", disc.getConsoleColor());
            } else {
                System.out.print(" ");
            }
        }
        
        
        System.out.println();
        System.out.println("    1 2 3 4 5 6");
    }
    
}
