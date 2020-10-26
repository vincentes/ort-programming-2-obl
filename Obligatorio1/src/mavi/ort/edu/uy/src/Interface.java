package mavi.ort.edu.uy.src;

import java.util.ArrayList;
import mavi.ort.edu.uy.src.models.Color;
import mavi.ort.edu.uy.src.models.Disc;
import mavi.ort.edu.uy.src.models.Match;
import mavi.ort.edu.uy.src.models.Player;
import mavi.ort.edu.uy.src.models.ResultCode;
import static mavi.ort.edu.uy.src.Game.persistence;
import mavi.ort.edu.uy.src.utils.Wrapper;

/**
 *
 * @authors Vicente Bermúdez - Matías Sallé
 */
public class Interface {

    public static ArrayList<Player> playersList;
    public ArrayList<Match> matchesList;

    public Interface() {
        playersList = new ArrayList<>();
        matchesList = new ArrayList<>();
    }

    // Players handling
    public static void addPlayer(Player player) {
        playersList.add(player);
    }
    
    public static void addPlayerFunctionality() {
        String name = "";
        int age = 100;
        boolean validatePlayerExistence = true;
        while (validatePlayerExistence) {
            name = Wrapper.validateString("Ingrese nombre:", "El nombre solo puede contener letras y números");
            validatePlayerExistence = persistence.validatePlayerExistence(name);
            if (validatePlayerExistence) {
                System.out.println("\nEl jugador " + name + " ya existe en el sistema");
            }
        }
        while (age >= 80 || age <= 12) {
            age = Wrapper.validateNumber("Ingrese edad:", "Solo el ingreso de números es permitido");
            if (age >= 80 || age <= 12) {
                System.out.println("Rango de edad permitido [12 - 80]");
            }
        }
        Player newPlayer = new Player(name, age);
        addPlayer(newPlayer);
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    // Matches handling
    public ArrayList<Match> getMatches() {
        return matchesList;
    }

    public void addMatch(Match match) {
        matchesList.add(match);
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
