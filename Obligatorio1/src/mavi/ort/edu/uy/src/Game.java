/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import mavi.ort.edu.uy.src.models.Board;
import mavi.ort.edu.uy.src.models.Compass;
import mavi.ort.edu.uy.src.models.Disc;
import mavi.ort.edu.uy.src.models.Match;
import mavi.ort.edu.uy.src.utils.Wrapper;
import mavi.ort.edu.uy.src.models.Player;
import mavi.ort.edu.uy.src.models.Step;

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
        playerList.add(testPlayerA);
        playerList.add(testPlayerB);
        
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
                    addPlayer();
                    break;
                case 2:
                    System.out.println("\nJugar grupos ");
                    
                    Date now = new Date();
                    String matchName = Wrapper.validateString("Ingrese el nombre de la partida:", "El nombre de la partida solo puede contener letras y números");
                    ArrayList<Step> steps = new ArrayList<Step>();
                    
                    if (playerList.size() >= 2) {
                        Player[] players = playerList.toArray(new Player[playerList.size()]);
                        Player redPlayer = null;
                        for (int i = 0; i < players.length; i++) {
                            Player player = players[i];
                            System.out.println("["+(i + 1)+ "]" + " Jugador: " + (player.getName()) + " Años: " + player.getAge());
                        }

                        option = Wrapper.validateNumber("Seleccione el jugador Rojo", "Solo el ingreso de números es permitido.");

                        if (option > players.length) {
                            System.out.println("Debe elegir un jugador dentro de la lista.");
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
                            System.out.println("["+(i + 1)+ "]" + " Jugador: " + (player.getName()) + " Años: " + player.getAge());
                        }

                        option = Wrapper.validateNumber("Seleccione el jugador Azul", "Solo el ingreso de números es permitido.");

                        if (option > players.length || option == excludedOption) {
                            System.out.println("Debe elegir un jugador dentro de la lista.");
                            continue;
                        }

                        bluePlayer = players[option - 1];

                        System.out.println("1 - Discos normales");
                        System.out.println("2 - Discos aleatorios");
                        System.out.println("3 - Cancelar");
                        
                        option = Wrapper.validateNumber("Seleccione el modo de juego", "Solo el ingreso de números es permitido.");

                        Board board = null;
                        while (option > 3 || option < 1) {
                            option = Wrapper.validateNumber("Seleccione el modo de juego", "Solo el ingreso de números es permitido.");
                        }
                        Disc[][] initialBoard = null;

                        switch (option) {
                            case 1:
                                board = Board.getDefaultBoard();
                                initialBoard = board.getDiscs();
                                break;
                            case 2:
                                board = Board.getRandomBoard();
                                initialBoard = board.getDiscs();
                                break;
                            case 3:
                                continue;
                        }

                        boolean end = false;
                        boolean solicitedEnd = false;
                        Player playerCurr;
                        int turn = 1;
                        
                        do {
                            board.print();
                            
                            Step step = new Step();
                            
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
                                if(colorText.equals("ROJO")) {
                                    notCurrPlayer = "AZUL";
                                } else {
                                    notCurrPlayer = "ROJO";
                                }
                                System.out.println("El jugador " + notCurrPlayer + " propone terminar el juego. ¿Aceptar?");

                                option = Wrapper.validateNumber("1 - SI\n2 - NO\n", "Debe ingresar un numero.");

                                while (option != 1 && option != 2) {
                                    System.out.println("Opciones válidas: 1 o 2");
                                    option = Wrapper.validateNumber("1 - SI\n2 - NO\n", "Debe ingresar un numero.");
                                }
                                
                                switch (option) {
                                    case 1:
                                        end = true;
                                        step.setMovementDescription("El jugador " + colorText + " ha aceptado la petición del jugador " + notCurrPlayer);
                                        step.setBoard(board);
                                        steps.add(step);
                                        Match match = new Match(now, matchName, steps, initialBoard, redPlayer, bluePlayer, "El ganado es <GANADOR>");
                                        persistence.addMatch(match);
                                        continue;
                                    case 2:
                                        solicitedEnd = false;
                                        System.out.println("Has rechazado la propuesta de terminar el juego.");
                                        break;
                                }
                            }
                            
                            System.out.println("Introduzca una acción");
                            String strInput = input.nextLine();
                            
                            if (strInput.equalsIgnoreCase("P")) {
                                step.setMovementDescription("El jugador " + colorText + " ha pasado de turno.");
                                step.setBoard(board);
                                steps.add(step);
                                System.out.println("Pasando turno.");
                                turn++;
                                continue;
                            }
                            
                            if (strInput.equalsIgnoreCase("F")) {
                                step.setMovementDescription("El jugador " + colorText + " ha pasado de turno.");
                                step.setBoard(board);
                                steps.add(step);
                                solicitedEnd = true;
                                turn++;
                                continue;
                            }
                            
                            String[] tokens = strInput.split(" ");
                            if(tokens.length != 3) {
                                // TODO: make input continue even if invalid input. fix this.
                                System.out.println("Debes introduccir una acción correcta.");
                                continue;
                            }

                            String movementChar = tokens[1];
                            if(movementChar.length() != 1 || (!movementChar.equals("S") && !movementChar.equals("N") && !movementChar.equals("E") && !movementChar.equals("O"))) {
                                System.out.println("Movimiento inválido. Debe ser del formato <Indice> <Movimiento (S, N, E, O)> <Cantidad de movimientos>");
                            }

                            int movements = Integer.parseInt(tokens[2]);
                            int position = Integer.parseInt(tokens[0]);

                            Compass compass = null;

                            switch(movementChar.toUpperCase()) {
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
                            step.setMovementDescription("El jugador " + colorText + " ha hecho el movimiento " + tokens[0] + " " + tokens[1] + " " + tokens[2]);
                            step.setBoard(board);
                            steps.add(step);
                            turn++;
                        } while (end == false);
                    }else{
                        System.out.println("\nDeben de existir al menos dos jugadores en el sistema para poder jugar");
                    }
                    
                    break;
                case 3:
                    System.out.println("\nReplicar partida");
                    ArrayList<Match> matches = persistence.getMatches();
                    if(matches.size() > 0){
                    
                    }else{
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

    public static void addPlayer() {
        String name = "";
        int age = 100;
        boolean validatePlayerExistence = true;
        while (validatePlayerExistence) {
            name = Wrapper.validateString("Ingrese nombre:", "El nombre solo puede contener letras y números");
            validatePlayerExistence = persistence.validatePlayerExistence(name);
            if(validatePlayerExistence){
                System.out.println("\nEl jugador " +name+ " ya existe en el sistema");
            }
        }
        while (age >= 80 || age <= 12) {
            age = Wrapper.validateNumber("Ingrese edad:", "Solo el ingreso de números es permitido");
            if (age >= 80 || age <= 12) {
                System.out.println("Rango de edad permitido [12 - 80]");
            }
        }
        Player newPlayer = new Player(name, age);
        persistence.addPlayer(newPlayer);
    }

}
