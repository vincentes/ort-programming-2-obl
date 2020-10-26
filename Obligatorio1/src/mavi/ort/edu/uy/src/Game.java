/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mavi.ort.edu.uy.src.constants.ConsoleColor;
import mavi.ort.edu.uy.src.models.Board;
import mavi.ort.edu.uy.src.models.Color;
import mavi.ort.edu.uy.src.models.Compass;
import mavi.ort.edu.uy.src.models.Disc;
import mavi.ort.edu.uy.src.models.Match;
import mavi.ort.edu.uy.src.utils.Wrapper;
import mavi.ort.edu.uy.src.models.Player;
import mavi.ort.edu.uy.src.models.ResultCode;
import mavi.ort.edu.uy.src.models.Step;
import mavi.ort.edu.uy.src.utils.PrettyPrint;

/**
 *
 * @author vicentebermudez
 */
public class Game {

    public static Interface persistence = new Interface();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menuGame();
    }

    public static void menuGame() {
        int option = 0;
        Scanner input = new Scanner(System.in);
        ArrayList<Player> playerList = persistence.getPlayersList();
        Player testPlayerA = new Player("Joaquin", 20);
        Player testPlayerB = new Player("Roberto", 25);
        persistence.addPlayer(testPlayerA);
        persistence.addPlayer(testPlayerB);

        ArrayList<Match> matchesList = persistence.getMatches();
        while (option != 4) {
            System.out.println("\n-- Grupos --");
            System.out.println("1- Agregar jugador");
            System.out.println("2- Jugar grupos");
            System.out.println("3- Replicar partida");
            System.out.println("4- Salir");
            System.out.println("--        --");
            option = Wrapper.validateNumber("Ingrese opción:", "Solo el ingreso de números es permitido");
            switch (option) {
                case 1:
                    System.out.println("\nAgregar jugador");
                    persistence.addPlayerFunctionality();
                    break;
                case 2:
                    System.out.println("\nJugar grupos ");

                    String currentTime = Wrapper.getCurrentTime();
                    String matchName = Wrapper.validateString("Ingrese el nombre de la partida:", "El nombre de la partida solo puede contener letras y números");
                    Match match = null;
                    ArrayList<Step> steps = new ArrayList<Step>();

                    if (playerList.size() >= 2) {
                        Player[] players = playerList.toArray(new Player[playerList.size()]);
                        Player redPlayer = null;
                        for (int i = 0; i < players.length; i++) {
                            Player player = players[i];
                            System.out.println("[" + (i + 1) + "]" + " Jugador: " + (player.getName()) + " Años: " + player.getAge());
                        }

                        boolean inputFinished = false;
                        while (!inputFinished) {
                            System.out.println("Seleccione el jugador [ROJO]");
                            String optionStr = input.nextLine();

                            if (!Wrapper.isValidNumber(optionStr)) {
                                System.out.println("Solo el ingreso de números es permitido.");
                                optionStr = input.nextLine();
                                continue;
                            }

                            option = Integer.parseInt(optionStr);
                            if (option > players.length) {
                                System.out.println("Debe elegir un jugador dentro de la lista");
                                continue;
                            }

                            inputFinished = true;

                            redPlayer = players[option - 1];
                            players[option - 1] = null;
                            int excludedOption = option;

                            Player bluePlayer = null;
                            for (int i = 0; i < players.length; i++) {
                                if (players[i] == null) {
                                    continue;
                                }
                                Player player = players[i];
                                System.out.println("[" + (i + 1) + "]" + " Jugador: " + (player.getName()) + " Años: " + player.getAge());
                            }

                            System.out.println("Seleccione el jugador [AZUL]");
                            optionStr = input.nextLine();
                            inputFinished = false;
                            while (inputFinished) {
                                if (!Wrapper.isValidNumber(optionStr)) {
                                    System.out.println("Solo el ingreso de números es permitido.");
                                    optionStr = input.nextLine();
                                }

                                option = Integer.parseInt(optionStr);
                                if (option > players.length || option == excludedOption) {
                                    System.out.println("Debe elegir un jugador dentro de la lista");
                                    continue;
                                }
                                inputFinished = true;
                            }

                            bluePlayer = players[option - 1];

                            System.out.println("1 - Discos normales");
                            System.out.println("2 - Discos aleatorios");
                            System.out.println("3 - Cancelar");

                            option = Wrapper.validateNumber("Seleccione el modo de juego", "Solo el ingreso de números es permitido");

                            Board board = null;
                            while (option > 3 || option < 1) {
                                option = Wrapper.validateNumber("Seleccione el modo de juego", "Solo el ingreso de números es permitido");
                            }

                            Step step = new Step();
                            switch (option) {
                                case 1:
                                    board = Board.getDefaultBoard();
                                    step.setMovementDescription("Inicio de partida | Modo [DEFAULT]");
                                    step.setBoard(board.copyBoard());
                                    steps.add(step);
                                    break;
                                case 2:
                                    board = Board.getRandomBoard();
                                    step.setMovementDescription("Inicio de partida | Modo [RANDOM]");
                                    step.setBoard(board.copyBoard());
                                    steps.add(step);
                                    break;
                                case 3:
                                    continue;
                            }

                            boolean end = false;
                            boolean solicitedEnd = false;
                            Player playerCurr;
                            int turn = 1;
                            do {
                                step = new Step();
                                board.print();
                                
                                String colorText = "";
                                if (turn % 2 == 0) {
                                    colorText = "AZUL";
                                    playerCurr = bluePlayer;
                                } else {
                                    colorText = "ROJO";
                                    playerCurr = redPlayer;
                                }

                                System.out.println("TURNO " + colorText);
                                if (solicitedEnd) {
                                    String notCurrPlayer = "";
                                    if (colorText.equals("ROJO")) {
                                        notCurrPlayer = "AZUL";
                                    } else {
                                        notCurrPlayer = "ROJO";
                                    }
                                    System.out.println("El jugador " + notCurrPlayer + " propone terminar el juego. ¿Aceptar?");

                                    option = Wrapper.validateNumber("1 - SI\n2 - NO\n", "Debe ingresar un numero");

                                    while (option != 1 && option != 2) {
                                        System.out.println("Opciones válidas: 1 o 2");
                                        option = Wrapper.validateNumber("1 - SI\n2 - NO\n", "Debe ingresar un numero");
                                    }

                                    switch (option) {
                                        case 1:
                                            end = true;
                                            step.setMovementDescription("El jugador " + colorText + " ha aceptado la petición del jugador " + notCurrPlayer);
                                            step.setBoard(board.copyBoard());
                                            steps.add(step);
                                            ResultCode winner = null;
                                            System.out.println(steps.size());

                                            try {
                                                winner = persistence.getWinner(steps.get(steps.size() - 1).getBoard().getDiscs());
                                            } catch (Exception e) {
                                                winner = ResultCode.DRAW;
                                            }

                                            if (winner.equals(ResultCode.PLAYER_RED)) {
                                                System.out.println("El jugador [ROJO] ha ganado el partido.");
                                            } else if (winner.equals(ResultCode.PLAYER_BLUE)) {
                                                System.out.println("El jugador [AZUL] ha ganado el partido.");
                                            } else {
                                                System.out.println("El jugador [ROJO][AZUL] han empatado el partido.");
                                            }

                                            match = new Match(currentTime, matchName, steps, redPlayer, bluePlayer, winner);
                                            persistence.addMatch(match);
                                            continue;
                                        case 2:
                                            solicitedEnd = false;
                                            System.out.println("Has rechazado la propuesta de terminar el juego");
                                            break;
                                    }
                                }

                                inputFinished = false;
                                while (!inputFinished) {
                                    System.out.println("\nIntroduzca una acción");
                                    String strInput = input.nextLine();
                                    if (strInput.equalsIgnoreCase("P")) {
                                        step.setMovementDescription("El jugador " + colorText + " ha pasado de turno");
                                        step.setBoard(board.copyBoard());
                                        steps.add(step);
                                        System.out.println("Pasando turno.");
                                        inputFinished = true;
                                        continue;
                                    }

                                    if (strInput.equalsIgnoreCase("F")) {
                                        step.setMovementDescription("El jugador " + colorText + " ha decidido terminar el juego");
                                        step.setBoard(board.copyBoard());
                                        steps.add(step);
                                        solicitedEnd = true;
                                        inputFinished = true;
                                        continue;
                                    }

                                    String[] tokens = strInput.split(" ");
                                    if (tokens.length != 3) {
                                        // TODO: make input continue even if invalid input. fix this.
                                        System.out.println("Debes introduccir una acción correcta");
                                        System.out.println("Listado de posibilidaes:");
                                        System.out.println("<Indice> <Movimiento (S, N, E, O)> <Cantidad de movimientos>");
                                        System.out.println("P - Pasar turno");
                                        System.out.println("F - Solicitar terminar partida");
                                        continue;
                                    }

                                    String movementChar = tokens[1];
                                    if (movementChar.length() != 1 || (!movementChar.equalsIgnoreCase("S") && !movementChar.equalsIgnoreCase("N") && !movementChar.equalsIgnoreCase("E") && !movementChar.equalsIgnoreCase("O"))) {
                                        System.out.println("Movimiento inválido. Debe ser del formato <Indice> <Movimiento (S, N, E, O)> <Cantidad de movimientos>");
                                        continue;
                                    }

                                    String movementsStr = tokens[2];
                                    if (!Wrapper.isValidNumber(movementsStr)) {
                                        System.out.println("La cantidad de movimientos debe ser un numero.");
                                        continue;
                                    }

                                    int movements = Integer.parseInt(movementsStr);
                                    if (movements > 6 || movements < 1) {
                                        System.out.println("La cantidad de movimientos debe estar entre 1 y 6.");
                                        continue;
                                    }

                                    String positionStr = tokens[0];
                                    if (!Wrapper.isValidNumber(positionStr)) {
                                        System.out.println("La posición debe ser un número.");
                                        continue;
                                    }

                                    int position = Integer.parseInt(positionStr);
                                    if (position < 1 || position > 6) {
                                        System.out.println("La posición debe estar entre 1 y 6 inclusive.");
                                        continue;
                                    }

                                    Compass compass = null;
                                    Color color = null;
                                    switch (colorText) {
                                        case "AZUL":
                                            color = Color.BLUE;
                                            break;
                                        case "ROJO":
                                            color = Color.RED;
                                            break;
                                    }
                                    switch (movementChar.toUpperCase()) {
                                        case "S":
                                            if (!board.existsDisc(0, position)) {
                                                System.out.println("El disco a mover debe estar en el lugar indicado.");
                                                continue;
                                            }

                                            if (board.getColorOfDisc(0, position) != color) {
                                                System.out.println("El color del disco a mover debe ser de tu color.");
                                                continue;
                                            }
                                            compass = Compass.SOUTH;
                                            break;
                                        case "N":
                                            if (!board.existsDisc(7, position)) {
                                                System.out.println("El disco a mover debe estar en el lugar indicado.");
                                                continue;
                                            }

                                            if (board.getColorOfDisc(7, position) != color) {
                                                System.out.println("El color del disco a mover debe ser de tu color.");
                                                continue;
                                            }
                                            compass = Compass.NORTH;
                                            break;
                                        case "O":
                                            if (!board.existsDisc(position, 7)) {
                                                System.out.println("El disco a mover debe estar en el lugar indicado.");
                                                continue;
                                            }

                                            if (board.getColorOfDisc(position, 7) != color) {
                                                System.out.println("El color del disco a mover debe ser de tu color.");
                                                continue;
                                            }
                                            compass = Compass.WEST;
                                            break;
                                        case "E":
                                            if (!board.existsDisc(position, 0)) {
                                                System.out.println("El disco a mover debe estar en el lugar indicado.");
                                                continue;
                                            }

                                            if (board.getColorOfDisc(position, 0) != color) {
                                                System.out.println("El color del disco a mover debe ser de tu color.");
                                                continue;
                                            }
                                            compass = Compass.EAST;
                                            break;
                                    }

                                    Disc[][] result = board.move(position, compass, movements);
                                    if (result == null) {
                                        System.out.println("Movimiento inválido. El disco no puede salirse del tablero.");
                                        continue;
                                    }
                                    String positionTok = tokens[0];
                                    String directionTok = tokens[1];
                                    String movementsTok = tokens[2];
                                    switch (positionTok) {
                                        case "S":
                                            // TODO
                                            break;
                                    }
                                    step.setMovementDescription("El jugador " + colorText + " ha hecho el movimiento " + tokens[0] + " " + tokens[1] + " " + tokens[2]);
                                    step.setBoard(board.copyBoard());
                                    steps.add(step);
                                    inputFinished = true;
                                }

                                turn++;
                            } while (end == false);
                        }
                    } else {
                        System.out.println("\nDeben de existir al menos dos jugadores en el sistema para poder jugar");
                    }

                    break;
                case 3:
                    System.out.println("\nReplicar partida");
                    Match matchToReplicate = null;
                    if (matchesList.size() > 0) {
                        for (int i = 0; i < matchesList.size(); i++) {
                            matchToReplicate = matchesList.get(i);
                            System.out.println("[" + (i + 1) + "]" + " Nombre: " + matchToReplicate.getName() + " Hora/Fecha de juego: " + matchToReplicate.getDate());
                        }
                        option = Wrapper.validateNumber("\nSeleccione la partida a replicar", "Solo el ingreso de números es permitido");
                        if (option > matchesList.size()) {
                            System.out.println("Debe elegir una partida dentro de la lista");
                            continue;
                        }
                        matchToReplicate = matchesList.get(option - 1);

                        List<Step> stepsToReplicate = matchToReplicate.getSteps();
                        Board replicatedBoard = null;
                        Disc discs[][] = null;

                        for (int i = 0; i < stepsToReplicate.size(); i++) {
                            replicatedBoard = stepsToReplicate.get(i).getBoard();
                            System.out.println("--------------------");
                            System.out.println(stepsToReplicate.get(i).getMovementDescription());
                            if (Wrapper.areDifferent(stepsToReplicate.get(i).getBoard().getDiscs(), discs)) {
                                System.out.println("--------------------");
                                replicatedBoard.print();
                                System.out.println("--------------------");
                            }
                            discs = stepsToReplicate.get(i).getBoard().getDiscs();
                            String opt = Wrapper.validateString("\nPresione X para continuar | Cualquier otra tecla para finalizar\nDesea seguir?", "Solo el ingreso de números es permitido");
                            if (!opt.toLowerCase().equals("x")) {
                                break;
                            }
                        }

                    } else {
                        System.out.println("\nDebe de existir almenos una partida para poder utilizar la funcionalidad 'Replicar partida'");
                    }
                    break;
                case 4:
                    System.out.println("\nEstá seguro que desea salir?");
                    System.out.println("1- Si");
                    System.out.println("2- No");
                    option = Wrapper.validateNumber("Ingrese opción:", "Solo el ingreso de números es permitido");
                    if (option == 1) {
                        System.out.println("\nHa salido del juego con éxito");
                        option = 4;
                    }
                    break;
                default:
                    System.out.println("\nDebes de ingresar un número entre uno y cuatro");
                    continue;
            }
        }
    }

}
