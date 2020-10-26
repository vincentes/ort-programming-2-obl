/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src.models;

import static java.lang.Integer.max;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import mavi.ort.edu.uy.src.constants.ConsoleColor;
import mavi.ort.edu.uy.src.utils.PrettyPrint;

/**
 *
 * @author vicentebermudez
 */
public class Board {
    private Disc[][] discs = new Disc[8][8];
    
    public Board() {
        
    }
    
    public Board(Disc[][] discs) {
        this.discs = discs;
    }
    
    public Board copyBoard() {
        Disc[][] discsCopy = new Disc[8][8];
        for(int i = 0; i < discsCopy.length; i++) {
            discsCopy[i] = Arrays.copyOf(discs[i], discs[i].length);
        }
        return new Board(discsCopy);
    }
    
    private static Disc randomDisc() {
        Disc disc = new Disc();
        if(Math.random() < 0.5) {
            disc.setColor(Color.BLUE);
        } else {
            disc.setColor(Color.RED);
        }
        return disc;
    }
    
    public boolean isPositionTaken(int i, int j) {
        return !(this.discs[i][j] == null);
    }
    
    public boolean inBoundaries(int i, int j) {
        return j <= discs.length - 1;
    }
    
    public boolean inVerticalBoundaries(int i) {
        return i <=  6 && i >= 1;
    }
    
    public boolean inHorizontalBoundaries(int j) {
        return j >= 1 && j <= 6;
    }
    
    public Disc[][] move(int position, Compass direction, int movements) {
        int i = -1;
        int j = -1;
        int iTarget = -1;
        switch(direction) {
            case SOUTH:
                i = 0;
                j = 1 + position - 1;
                iTarget = i + movements;
                
                int amtDisks = 0;
                
                for(int q = 1; q <= 6; q++) {
                    if(discs[q][j] != null) {
                        amtDisks++;
                    }
                }
                
                if(movements + amtDisks > 6) {
                    return null;
                }
                
                boolean okay = true;
                for(int q = 1; q <= iTarget; q++) {
                    if(discs[q][j] != null) {
                        okay = false;
                    }
                }
                
                int l = 1;
                while(!okay) {
                    if(inVerticalBoundaries(i + l) && discs[i + l][j] != null) {
                        if(inVerticalBoundaries(i + l + 1) && discs[i + l + 1][j] != null) {
                            if(inVerticalBoundaries(i + l + 2) && discs[i + l + 2][j] != null) {
                                if(inVerticalBoundaries(i + l + 3) && discs[i + l + 3][j] != null) {
                                    if(inVerticalBoundaries(i + l + 4) && discs[i + l + 4][j] != null) {
                                        if(inVerticalBoundaries(i + l + 5) && discs[i + l + 5][j] != null) {
                                            discs[i + l + 6][j] = discs[i + l + 5][j];
                                            discs[i + l + 5][j] = null;
                                        }
                                        discs[i + l + 5][j] = discs[i + l + 4][j];
                                        discs[i + l + 4][j] = null;
                                    }
                                    discs[i + l + 4][j] = discs[i + l + 3][j];
                                    discs[i + l + 3][j] = null;
                                }
                                discs[i + l + 3][j] = discs[i + l + 2][j];
                                discs[i + l + 2][j] = null;
                            }
                            discs[i + l + 2][j] = discs[i + l + 1][j];
                            discs[i + l + 1][j] = null;
                        }
                        discs[i + l + 1][j] = discs[i + l][j];
                        discs[i + l][j] = null;
                    }
                    
                    l++;
                    
                    okay = true;
                    for(int q = 1; q <= iTarget; q++) {
                        if(discs[q][j] != null) {
                            okay = false;
                        }
                    }
                    // print();
                }
                
                discs[iTarget][j] = discs[i][j];
                discs[i][j] = null;    
                // print();
                break;
            case NORTH:
                i = 7;
                j = position;
                iTarget = i - movements;
                
                amtDisks = 0;
                                    
                for(int q = 1; q <= 6; q++) {
                    if(discs[q][j] != null) {
                        amtDisks++;
                    }
                }
                
                if(movements + amtDisks > 6) {
                    return null;
                }
                
                okay = true;
                for(int q = 6; q >= iTarget; q--) {
                    if(discs[q][j] != null) {
                        okay = false;
                    }
                }
                
                l = 1;
                while(!okay) {
                    if(inVerticalBoundaries(i - l) && discs[i - l][j] != null) {
                        if(inVerticalBoundaries(i - l - 1) && discs[i - l - 1][j] != null) {
                            if(inVerticalBoundaries(i - l - 2) && discs[i - l - 2][j] != null) {
                                if(inVerticalBoundaries(i - l - 3) && discs[i - l - 3][j] != null) {
                                    if(inVerticalBoundaries(i - l - 4) && discs[i - l - 4][j] != null) {
                                        if(inVerticalBoundaries(i - l - 5) && discs[i - l - 5][j] != null) {
                                            discs[i + l + 6][j] = discs[i - l - 5][j];
                                            discs[i + l + 5][j] = null;
                                        }
                                        discs[i - l - 5][j] = discs[i - l - 4][j];
                                        discs[i - l - 4][j] = null;
                                    }
                                    discs[i - l - 4][j] = discs[i - l - 3][j];
                                    discs[i - l - 3][j] = null;
                                }
                                discs[i - l - 3][j] = discs[i - l - 2][j];
                                discs[i - l - 2][j] = null;
                            }
                            discs[i - l - 2][j] = discs[i - l - 1][j];
                            discs[i - l - 1][j] = null;
                        }
                        discs[i - l - 1][j] = discs[i - l][j];
                        discs[i - l][j] = null;
                    }
                    
                    l++;
                    
                    okay = true;
                    for(int q = i - 1; q >= iTarget; q--) {
                        if(discs[q][j] != null) {
                            okay = false;
                        }
                    }
                    print();
                }
                
                discs[iTarget][j] = discs[i][j];
                discs[i][j] = null;   

                break;
            case EAST:
                System.out.println("Moving from EAST");

                i = 1 + position - 1;
                j = 0;
                int jTarget = j + movements;
                
                amtDisks = 0;
                                    
                for(int q = 1; q <= 6; q++) {
                    if(discs[i][q] != null) {
                        amtDisks++;
                    }
                }
                
                if(movements + amtDisks > 6) {
                    return null;
                }
                
                
                okay = true;
                for(int q = 1; q <= jTarget; q++) {
                    if(discs[i][q] != null) {
                        okay = false;
                    }
                }
                
                l = 1;
                while(!okay) {
                    if(inHorizontalBoundaries(j + l) && discs[i][j + l] != null) {
                        if(inHorizontalBoundaries(j + l + 1) && discs[i][j + l + 1] != null) {
                            if(inHorizontalBoundaries(j + l + 2) && discs[i][j + l + 2] != null) {
                                if(inHorizontalBoundaries(j + l + 3) && discs[i][j + l + 3] != null) {
                                    if(inHorizontalBoundaries(j + l + 4) && discs[i][j + l + 4] != null) {
                                        if(inHorizontalBoundaries(j + l + 5) && discs[i][j + l + 5] != null) {
                                            discs[i][j + l + 6] = discs[i][j + l + 5];
                                            discs[i][j + l + 5] = null;
                                        }
                                        discs[i][j + l + 5] = discs[i][j + l + 4];
                                        discs[i][j + l + 4] = null;
                                    }
                                    discs[i][j + l + 4] = discs[i][j + l + 3];
                                    discs[i][j + l + 3] = null;
                                }
                                discs[i][j + l + 3] = discs[i][j + l + 2];
                                discs[i][j + l + 2] = null;
                            }
                            discs[i][j + l + 2] = discs[i][j + l + 1];
                            discs[i][j + l + 1] = null;
                        }
                        discs[i][j + l + 1] = discs[i][j + l];
                        discs[i][j + l] = null;
                    }
                    
                    l++;
                    
                    okay = true;
                    for(int q = 1; q <= jTarget; q++) {
                        if(discs[i][q] != null) {
                            okay = false;
                        }
                    }
                }
                
                discs[i][jTarget] = discs[i][j];
                discs[i][j] = null;
                break;
            case WEST:
                System.out.println("Moving from WEST");

                i = 1 + position - 1;
                j = 7;
                jTarget = j - movements;
                
                amtDisks = 0;
                                    
                for(int q = 1; q <= 6; q++) {
                    if(discs[i][q] != null) {
                        amtDisks++;
                    }
                }
                
                if(movements + amtDisks > 6) {
                    return null;
                }
                
                okay = true;
                for(int q = 6; q >= jTarget; q--) {
                    if(discs[i][q] != null) {
                        okay = false;
                    }
                }
                
                l = 1;
                while(!okay) {
                    if(inHorizontalBoundaries(j - l) && discs[i][j - l] != null) {
                        if(inHorizontalBoundaries(j - l - 1) && discs[i][j - l - 1] != null) {
                            if(inHorizontalBoundaries(j - l - 2) && discs[i][j - l - 2] != null) {
                                if(inHorizontalBoundaries(j - l - 3) && discs[i][j - l - 3] != null) {
                                    if(inHorizontalBoundaries(j - l - 4) && discs[i][j - l - 4] != null) {
                                        if(inHorizontalBoundaries(j - l - 5) && discs[i][j - l - 5] != null) {
                                            discs[i][j - l - 6] = discs[i][j - l - 5];
                                            discs[i][j - l - 5] = null;
                                        }
                                        discs[i][j - l - 5] = discs[i][j - l - 4];
                                        discs[i][j - l - 4] = null;
                                    }
                                    discs[i][j - l - 4] = discs[i][j - l - 3];
                                    discs[i][j - l - 3] = null;
                                }
                                discs[i][j - l - 3] = discs[i][j - l - 2];
                                discs[i][j - l - 2] = null;
                            }
                            discs[i][j - l - 2] = discs[i][j - l - 1];
                            discs[i][j - l - 1] = null;
                        }
                        discs[i][j - l - 1] = discs[i][j - l];
                        discs[i][j - l] = null;
                    }
                    
                    l++;
                    
                    okay = true;
                    for(int q = 6; q >= jTarget; q--) {
                        if(discs[i][q] != null) {
                            okay = false;
                        }
                    }
                    // print();
                }
                
                discs[i][jTarget] = discs[i][j];
                discs[i][j] = null;
                //print();
                break;
        }
        
        // New position for the disc.
        // East, West -> Can only move horizontally
        return getDiscs();
    }
    
    public static Board getRandomBoard() {
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
        
        for(int j = 1; j < discs[0].length - 2 - 1; j++) {
            Disc disc = discs[0][j];
            Disc nextDisc = discs[0][j + 1];
            Disc otherDisc = discs[0][j + 2];

            if(disc.getColor() == nextDisc.getColor() && nextDisc.getColor() == otherDisc.getColor()) {
                fixColor();
            }
        }
        
        for(int j = 1; j < discs[7].length - 2 - 1; j++) {
            Disc disc = discs[7][j];
            Disc nextDisc = discs[7][j + 1];
            Disc otherDisc = discs[7][j + 2];

            if(disc.getColor() == nextDisc.getColor() && nextDisc.getColor() == otherDisc.getColor()) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                switch(randomNum) {
                    case 1:
                        disc.setColor(disc.getColor().getInverse());
                        break;
                    case 2:
                        nextDisc.setColor(disc.getColor().getInverse());
                        break;
                    case 3:
                        nextDisc.setColor(disc.getColor().getInverse());
                        break;
                }
            }
        }
        
        for(int j = 1; j < discs[0].length - 2 - 1; j++) {
            Disc disc = discs[j][0];
            Disc nextDisc = discs[j + 1][0];
            Disc otherDisc = discs[j + 2][0];

            if(disc.getColor() == nextDisc.getColor() && nextDisc.getColor() == otherDisc.getColor()) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                switch(randomNum) {
                    case 1:
                        disc.setColor(disc.getColor().getInverse());
                        break;
                    case 2:
                        nextDisc.setColor(disc.getColor().getInverse());
                        break;
                    case 3:
                        nextDisc.setColor(disc.getColor().getInverse());
                        break;
                }
            }
        }
        
        for(int j = 1; j < discs[0].length - 2 - 1; j++) {
            Disc disc = discs[j][7];
            Disc nextDisc = discs[j + 1][7];
            Disc otherDisc = discs[j + 2][7];

            if(disc.getColor() == nextDisc.getColor() && nextDisc.getColor() == otherDisc.getColor()) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                switch(randomNum) {
                    case 1:
                        disc.setColor(disc.getColor().getInverse());
                        break;
                    case 2:
                        nextDisc.setColor(disc.getColor().getInverse());
                        break;
                    case 3:
                        nextDisc.setColor(disc.getColor().getInverse());
                        break;
                }
            }
        }
        
        Board board = new Board();
        board.setDiscs(discs);
        return board;
    }
    
    public static Board getDefaultBoard() {
        Disc[][] discs = new Disc[8][8];
        for(int i = 0; i < discs.length; i++) {
            for(int j = 0; j < discs[i].length; j++) {                
                Disc newDisc = new Disc();
                if(i == 0 || i == discs.length - 1) {
                    if(j == 0 || j == discs[i].length - 1) {
                        continue;
                    }
                    
                    if(j % 2 == 0) {
                        newDisc.setColor(Color.BLUE);
                        discs[i][j] = newDisc;
                    } else {
                        newDisc.setColor(Color.RED);
                        discs[i][j] = newDisc;
                    }
                } else {
                    if(j == 0) {
                        if(i % 2 == 0) {
                            newDisc.setColor(Color.RED);
                            discs[i][j] = newDisc;
                        } else {
                            newDisc.setColor(Color.BLUE);
                            discs[i][j] = newDisc;
                        }
                    } else if(j == discs[i].length - 1) {
                        if((i + 1) % 2 == 0) {
                            newDisc.setColor(Color.BLUE);
                            discs[i][j] = newDisc;
                        } else {
                            newDisc.setColor(Color.RED);
                            discs[i][j] = newDisc;
                        }
                    }
                }
            }
        }
        
        Board board = new Board();
        board.setDiscs(discs);
        return board;
    }
    
    public void print() {
        Board.print(this.discs);
    }
    
    public static void print(Disc[][] discs) {
        if(discs == null) {
            return;
        }
        System.out.println("    1 2 3 4 5 6");
        System.out.print("   ");
        for(int j = 0; j < discs[j].length - 1; j++) {
            Disc disc = discs[0][j];

            if(disc != null) {
                PrettyPrint.print(" X", disc.getConsoleColor());
            } else {
                if(j != 0 && j != 1) {
                    System.out.print("  ");
                }
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
                System.out.print("  ");
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
        
        System.out.print(" ");
        for(int j = 0; j < discs[j].length - 1; j++) {
            Disc disc = discs[discs.length - 1][j];

            if(disc != null) {
                PrettyPrint.print(" X", disc.getConsoleColor());
            } else {
                System.out.print("  ");
            }
        }
        
        System.out.println();
        System.out.println("    1 2 3 4 5 6");
    }

    /**
     * @return the discs
     */
    public Disc[][] getDiscs() {
        return discs;
    }

    /**
     * @param discs the discs to set
     */
    public void setDiscs(Disc[][] discs) {
        this.discs = discs;
    }
    
}
