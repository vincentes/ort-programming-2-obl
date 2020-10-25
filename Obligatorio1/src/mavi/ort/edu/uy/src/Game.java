/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src;

import java.util.ArrayList;
import java.util.Scanner;
import mavi.ort.edu.uy.src.models.Board;
import mavi.ort.edu.uy.src.utils.Wrapper;
import mavi.ort.edu.uy.src.models.Player;

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
                    ArrayList<Player> playerList = persistence.getPlayersList();
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

                        System.out.println("Seleccione el jugador Azul.");

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

                        switch (option) {
                            case 1:
                                board = Board.getDefaultBoard();
                                break;
                            case 2:
                                board = Board.getRandomBoard();
                                break;
                            case 3:
                                continue;
                        }

                        boolean end = false;
                        boolean solicitedEnd = false;
                        Scanner input = new Scanner(System.in);
                        String strInput = "";
                        Player playerCurr;
                        int turn = 1;

                        do {
                            if (strInput.equals("P")) {
                                end = true;
                                continue;
                            }

                            String colorText = "";
                            if (turn % 2 == 0) {
                                colorText = "BLUE";
                                playerCurr = bluePlayer;
                            } else {
                                colorText = "RED";
                                playerCurr = redPlayer;
                            }

                            System.out.println("TURNO AZUL");

                            if (solicitedEnd) {
                                System.out.println("El jugador ROJO propone terminar el juego. ¿Aceptar?");

                                option = Wrapper.validateNumber("1 - SI\n2 - NO", "Debe ingresar un numero.");

                                while (option != 1 && option != 0) {
                                    switch (option) {
                                        case 1:
                                            end = true;
                                            // End game
                                            break;
                                        case 2:
                                            solicitedEnd = false;
                                            break;
                                    }
                                }
                            }

                            if (strInput.equals("F")) {
                                solicitedEnd = true;
                            } else if (strInput.equals("P")) {
                                turn++;
                                continue;
                            }

                            board.print();
                        } while (end == false);
                    }else{
                        System.out.println("\nDeben de existir al menos dos jugadores en el sistema para poder jugar");
                    }

                    break;
                case 3:
                    System.out.println("\nReplicar partida");
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
