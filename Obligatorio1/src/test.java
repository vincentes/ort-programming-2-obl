/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import mavi.ort.edu.uy.src.models.Board;
import mavi.ort.edu.uy.src.models.Color;
import mavi.ort.edu.uy.src.models.Disc;
import mavi.ort.edu.uy.src.models.ResultCode;

/**
 *
 * @author matiassalle
 */
public class test {

    public static void main(String args[]) {
        Disc[][] discs = new Disc[8][8];
        Disc blueDisc = new Disc();
        blueDisc.setColor(Color.BLUE);
        Disc redDisc = new Disc();
        redDisc.setColor(Color.RED);

        discs[0][1] = redDisc;
        discs[1][3] = redDisc;
        discs[3][4] = redDisc;
        discs[1][1] = redDisc;
        discs[3][1] = redDisc;
        discs[3][3] = redDisc;
        discs[4][4] = redDisc;
        discs[5][5] = redDisc;
        discs[6][6] = blueDisc;

        discs[2][1] = redDisc;
        discs[1][2] = redDisc;
        discs[3][2] = redDisc;
        discs[3][3] = redDisc;
        discs[1][4] = redDisc;
        discs[4][5] = redDisc;
        discs[1][6] = blueDisc;
        discs[1][4] = blueDisc;
        discs[4][1] = blueDisc;
        discs[4][2] = blueDisc;
        discs[4][3] = blueDisc;
        discs[5][1] = blueDisc;
        discs[5][2] = blueDisc;

        /*
        String finalresult = "11 12 32";
        String[] currencies = finalresult.split(" ");
        System.out.println(currencies[0].charAt(0));
        */
        Board.print(discs);
        getWinner(discs);

        // System.out.println(adjacentsIntersection("11 21 12","21 11 31"));
        /*
        String master = "011102223303";
        String to_remove="22";

        master = master.replace(to_remove, "");
        // the above line replaces the t_remove string with blank string in master

        System.out.println(master);
         */
    }

    public static void getWinner(Disc[][] discs) {
        String finalResult = "";
        for (int x = 1; x <= 6; x++) {
            for (int y = 1; y <= 6; y++) {
                Disc currentDisc = discs[x][y];
                String currentDiscAdjacents = getAdjacents(discs, currentDisc, x, y);
                if (currentDiscAdjacents.length() > 2) {
                    String results = "";
                    for (int i = 1; i <= 6; i++) {
                        for (int j = 1; j <= 6; j++) {
                            Disc disc = discs[i][j];
                            results = adjacentsIntersection(currentDiscAdjacents, getAdjacents(discs, disc, i, j));
                            if (results.length() > currentDiscAdjacents.length()) {
                                currentDiscAdjacents = results;
                            }
                        }
                    }
                    if (results.length() > finalResult.length()) {
                        finalResult = results;
                    }
                }
            }
        }
        System.out.println(defineWinnerFromResults(discs, finalResult));
        
    }

    public static String adjacentsIntersection(String adjacentsPositions, String newAdjacentsPositions) {
        String[] currencies = newAdjacentsPositions.split(" ");
        String adjacentsIntersection = adjacentsPositions;

        if (adjacentsPositions.contains(currencies[0])) {
            for (int i = 1; i < currencies.length; i++) {
                if (!adjacentsPositions.contains(currencies[i])) {
                    adjacentsIntersection += " " + currencies[i];
                }
            }
        }
        return adjacentsIntersection;
    }

    public static String getAdjacents(Disc[][] discs, Disc disctToValidate, int i, int j) {
        String adjacentsPositions = "";
        if (disctToValidate != null) {
            adjacentsPositions = i + "" + j;
            if (i - 1 != 0 && discs[i - 1][j] != null && disctToValidate.getColor().equals(discs[i - 1][j].getColor())) {
                adjacentsPositions += " " + (i - 1) + "" + j;
            }
            if (i + 1 != 7 && discs[i + 1][j] != null && disctToValidate.getColor().equals(discs[i + 1][j].getColor())) {
                adjacentsPositions += " " + (i + 1) + "" + j;
            }
            if (j - 1 != 0 && discs[i][j - 1] != null && disctToValidate.getColor().equals(discs[i][j - 1].getColor())) {
                adjacentsPositions += " " + i + "" + (j - 1);
            }
            if (j + 1 != 7 && discs[i][j + 1] != null && disctToValidate.getColor().equals(discs[i][j + 1].getColor())) {
                adjacentsPositions += " " + i + "" + (j + 1);
            }
        }
        return adjacentsPositions;
    }

    public static ResultCode defineWinnerFromResults(Disc[][] discs, String finalResult) {
        ResultCode result = ResultCode.DRAW;
        
        int column = Integer.parseInt(String.valueOf(finalResult.charAt(1)));
        int row = Integer.parseInt(String.valueOf(finalResult.charAt(0)));
        
        Color winner = discs[row][column].getColor();
        if (winner.equals(Color.RED)) {
            result = ResultCode.PLAYER_RED;
        } else if (winner.equals(Color.BLUE)) {
            result = ResultCode.PLAYER_BLUE;
        }
        return result;
    }

}
