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

        //Tablero 1 Ganador: AZUL
        /*
        discs[1][2] = redDisc; 
        discs[1][5] = redDisc;
        discs[1][6] = blueDisc;
        discs[2][1] = redDisc; 
        discs[2][3] = blueDisc; 
        discs[2][5] = blueDisc; 
        discs[2][6] = blueDisc; 
        discs[3][2] = redDisc; 
        discs[3][3] = blueDisc; 
        discs[3][5] = blueDisc; 
        discs[3][6] = redDisc; 
        discs[4][3] = blueDisc;
        discs[4][4] = blueDisc;
        discs[4][5] = blueDisc;
        discs[5][4] = redDisc; 
        discs[6][1] = redDisc;
        discs[6][4] = redDisc;
        discs[6][5] = redDisc;
        discs[6][6] = redDisc;
        */
        
        //Tablero 2 Ganador: AZUL
        /*
        discs[1][3] = redDisc;
        discs[2][1] = blueDisc ;
        discs[2][3] = redDisc ;
        discs[2][6] = blueDisc ;
        discs[3][1] = redDisc ;
        discs[3][4] = blueDisc;
        discs[3][5] = blueDisc;
        discs[3][6] = blueDisc;
        discs[4][4] = blueDisc;
        discs[4][6] = blueDisc ;
        discs[5][2] = redDisc;
        discs[5][6] = blueDisc;
        discs[6][2] = redDisc;
        discs[6][3] = redDisc;
        discs[6][6] = blueDisc;        
        */
        
        //Tablero 3 Ganador: EMPATE
        /*
        discs[1][3] = blueDisc;
        discs[2][2] = redDisc;
        discs[2][5] = blueDisc;
        discs[3][2] = redDisc;
        discs[3][3] = redDisc;
        discs[3][5] = redDisc;
        discs[4][1] = blueDisc;
        discs[4][3] = redDisc;
        discs[4][5] = blueDisc;
        discs[5][3] = redDisc;
        discs[5][4] = blueDisc;
        discs[5][5] = blueDisc;
        discs[6][4] = blueDisc;
        discs[6][5] = blueDisc;
        discs[6][6] = redDisc ;
        */
        
        //Tablero 4 Ganador: ROJO
        /*
        discs[1][2] = redDisc;
        discs[2][2] = redDisc;
        discs[2][3] = blueDisc;
        discs[2][4] = redDisc;
        discs[2][6] = redDisc;
        discs[3][2] = redDisc;
        discs[3][3] = redDisc;
        discs[3][4] = redDisc;
        discs[3][6] = redDisc;
        discs[4][2] = blueDisc;
        discs[4][4] = redDisc;
        discs[4][6] = blueDisc;
        discs[5][2] = blueDisc ;
        discs[5][3] = blueDisc;
        discs[5][4] = redDisc;
        discs[5][6] = blueDisc;
        discs[6][3] = blueDisc;
        discs[6][4] = redDisc;
        */
        
        //Tablero 5 Ganador: ROJO
        /*
        discs[1][1] = redDisc;
        discs[2][1] = redDisc;
        discs[2][2] = redDisc;
        discs[2][4] = redDisc;
        discs[2][5] = redDisc;
        discs[2][6] = redDisc;
        discs[3][2] = redDisc;
        discs[3][3] = redDisc;
        discs[3][4] = redDisc;
        discs[3][5] = redDisc;
        discs[5][3] = redDisc;
        discs[5][4] = redDisc;
        discs[5][5] = redDisc;
        discs[1][4] = blueDisc;
        discs[1][5] = blueDisc;
        discs[3][1] = blueDisc;
        discs[4][1] = blueDisc;
        discs[4][2] = blueDisc;
        discs[4][6] = blueDisc;
        discs[5][2] = blueDisc;
        */
        
        //Tablero 6 Ganador: EMPATE
        /*
        discs[1][1] = redDisc;
        discs[2][1] = redDisc;
        discs[3][1] = redDisc;
        discs[4][1] = redDisc;
        discs[5][1] = redDisc;
        discs[6][1] = redDisc;
        discs[1][5] = redDisc;
        discs[3][4] = redDisc;
        discs[5][5] = redDisc;
        discs[6][3] = redDisc;
        discs[6][5] = redDisc;
        discs[1][6] = blueDisc;
        discs[2][6] = blueDisc;
        discs[3][6] = blueDisc;
        discs[4][6] = blueDisc;
        discs[5][6] = blueDisc;
        discs[6][6] = blueDisc;
        discs[3][3] = blueDisc;
        discs[4][4] = blueDisc;
        discs[5][3] = blueDisc;
        discs[5][4] = blueDisc;
        */
        
        Board.print(discs);
        getWinner(discs);
    }

    public static void getWinner(Disc[][] discs) {
        String finalResult = "";
        String finalResultAux = "";
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
                    if (results.length() >= finalResultAux.length()) {
                        finalResultAux = results;
                        finalResult += finalResultAux + "/";
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
        boolean absolutWinner = true;
        Color winnerColor;

        String[] currencies = finalResult.split("/");
        
        int column = Integer.parseInt(String.valueOf(currencies[0].charAt(1)));
        int row = Integer.parseInt(String.valueOf(currencies[0].charAt(0)));
        Color colorAux = discs[row][column].getColor();

        int currenciesLenght = currencies[0].length();

        for (int x = 0; x < currencies.length; x++) {
            column = Integer.parseInt(String.valueOf(currencies[x].charAt(1)));
            row = Integer.parseInt(String.valueOf(currencies[x].charAt(0)));
            if (colorAux != discs[row][column].getColor()) {
                absolutWinner = false;
            }

        }

        if(!absolutWinner){
            for (int i = 0; i < currencies.length; i++) {
            column = Integer.parseInt(String.valueOf(currencies[i].charAt(1)));
            row = Integer.parseInt(String.valueOf(currencies[i].charAt(0)));
            if (currenciesLenght < currencies[i].length()) {
                currenciesLenght = currencies[i].length();
                winnerColor = discs[row][column].getColor();
                if (winnerColor.equals(Color.RED)) {
                    result = ResultCode.PLAYER_RED;
                } else if (winnerColor.equals(Color.BLUE)) {
                    result = ResultCode.PLAYER_BLUE;
                }
            }
        }
        }else{
            if (colorAux.equals(Color.RED)) {
                    result = ResultCode.PLAYER_RED;
                } else if (colorAux.equals(Color.BLUE)) {
                    result = ResultCode.PLAYER_BLUE;
                }
        }
        

        return result;
    }

}
