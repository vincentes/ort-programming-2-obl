/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src;

import java.util.InputMismatchException;
import java.util.Scanner;
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
        Scanner input = new Scanner(System.in);
        int option = 0;
        while (option != 4) {
            System.out.println("-- Grupos --");
            System.out.println("1- Agregar jugador");
            System.out.println("2- Jugar grupos");
            System.out.println("3- Replicar partida");
            System.out.println("4- Salir");
            System.out.println("--        --");
            String optionStr = input.nextLine();
            if (optionStr.matches("\\d+")) {
                option = Integer.parseInt(optionStr);
            } else {
                continue;
            }
            switch (option) {
                case 1:
                    System.out.println("Agregar jugador");
                    addPlayer();
                    break;
                case 2:
                    System.out.println("Jugar grupos ");
                    persistence.getPlayersList();
                    break;
                case 3:
                    System.out.println("Replicar partida");
                    break;
                case 4:
                    System.out.println("Está seguro que desea salir?");
                    System.out.println("1- Si");
                    System.out.println("2- No");

                    optionStr = input.nextLine();
                    if (optionStr.matches("\\d+")) {
                        option = Integer.parseInt(optionStr);
                        if (option == 1) {
                            System.out.println("Ha salido del juego con éxito");
                            option = 4;
                        }
                    } else {
                        option = 0;
                        continue;
                    }
                    break;
                default:
                    System.out.println("Debes de ingresar un número entre uno y cuatro");
                    continue;
            }
        }
    }

    public static void addPlayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese nombre:");
        String name = input.nextLine();
        System.out.println("Ingrese edad:");
        int age = input.nextInt();
        Player newPlayer = new Player(name, age);
        persistence.addPlayer(newPlayer);
    }

}
