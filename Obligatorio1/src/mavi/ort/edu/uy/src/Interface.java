package mavi.ort.edu.uy.src;

import java.util.ArrayList;
import mavi.ort.edu.uy.src.models.Color;
import mavi.ort.edu.uy.src.models.Disc;
import mavi.ort.edu.uy.src.models.Match;
import mavi.ort.edu.uy.src.models.Player;
import mavi.ort.edu.uy.src.models.ResultCode;
import java.util.Date;
import java.util.List;
import mavi.ort.edu.uy.src.models.Step;

/**
 *
 * @author matiassalle
 */
public class Interface {

    private ArrayList<Player> playersList;
    private ArrayList<Match> matchesList;

    public Interface() {
        playersList = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        playersList.add(player);
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public ArrayList<Match> getMatches() {
        return matchesList;
    }

    public void addMatch(Date date, String name, List<Step> steps, Player playerRed, Player playerBlue, String result) {
        Match match = new Match();
        match.date = date;
        match.steps = steps;
        match.playerRed = playerRed;
        match.playerBlue = playerBlue;
        match.result = result;
        this.matchesList.add(match);
    }

    // Method to verify if a player is already on the playersList
    public boolean validatePlayerExistence(String name) {
        boolean isPlayerPresent = false;
        for (Player p : playersList) {
            if (p.getName().equals(name)) {
                isPlayerPresent = true;
            }
        }
        return isPlayerPresent;
    }

    // This method returns the ResultCode from a discs matrix
    public static ResultCode getWinner(Disc[][] discs) {
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
        return defineWinnerFromResults(discs, finalResult);

    }

    /*This method returns the intersection of a given adjacentes position String 
      for example let's say that the position 11 has the following adjacent positions 11 21 32 (this String is a valid input)
     */
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

    // Method to return an String with the position of the adjancet's discs of a given disc
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

    // Method to return the ResultCode (PLAYER_RED/PLAYER_BLUE/DRAW) from a given discs matrix and a given finalResult String. 
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

        if (!absolutWinner) {
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
        } else {
            if (colorAux.equals(Color.RED)) {
                result = ResultCode.PLAYER_RED;
            } else if (colorAux.equals(Color.BLUE)) {
                result = ResultCode.PLAYER_BLUE;
            }
        }
        return result;
    }

}
