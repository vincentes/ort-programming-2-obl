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
import mavi.ort.edu.uy.src.models.Compass;
import mavi.ort.edu.uy.src.models.Disc;
import mavi.ort.edu.uy.src.models.Match;
import mavi.ort.edu.uy.src.utils.Wrapper;
import mavi.ort.edu.uy.src.models.Player;
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

                        System.out.println("Seleccione el jugador [ROJO]");
                        String optionStr = input.nextLine();
                        boolean inputFinished = false;
                        while (inputFinished) {
                            if (!Wrapper.isValidNumber(optionStr)) {
                                System.out.println("Solo el ingreso de números es permitido.");
                                optionStr = input.nextLine();
                            }

                            option = Integer.parseInt(optionStr);
                            if (option > players.length) {
                                System.out.println("Debe elegir un jugador dentro de la lista");
                                continue;
                            }
                            inputFinished = true;

                            option = Wrapper.validateNumber("Seleccione el jugador [ROJO]", "Solo el ingreso de números es permitido");

                            if (option > players.length) {
                                System.out.println("Debe elegir un jugador dentro de la lista");
                                continue;
                            }

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
                                            match = new Match(currentTime, matchName, steps, redPlayer, bluePlayer, "El ganado es <GANADOR>");
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
                                        turn++;
                                        continue;
                                    }

                                    if (strInput.equalsIgnoreCase("F")) {
                                        step.setMovementDescription("El jugador " + colorText + " ha decidido terminar el juego");
                                        step.setBoard(board.copyBoard());
                                        steps.add(step);
                                        solicitedEnd = true;
                                        turn++;
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

                                    int movements = Integer.parseInt(tokens[2]);
                                    int position = Integer.parseInt(tokens[0]);

                                    Compass compass = null;

                                    switch (movementChar.toUpperCase()) {
                                        case "S":
                                            compass = Compass.SOUTH;
                                            break;
                                        case "N":
                                            compass = Compass.NORTH;
                                            break;
                                        case "O":
                                            compass = Compass.WEST;
                                            break;
                                        case "E":
                                            compass = Compass.EAST;
                                            break;
                                    }

                                    board.move(position, compass, movements);
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
