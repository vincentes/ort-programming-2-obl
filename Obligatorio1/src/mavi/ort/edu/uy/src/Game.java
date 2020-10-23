/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src;

import mavi.ort.edu.uy.src.utils.Wrapper;
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
        int option = 0;
        while (option != 4) {
            System.out.println("-- Grupos --");
            System.out.println("1- Agregar jugador");
            System.out.println("2- Jugar grupos");
            System.out.println("3- Replicar partida");
            System.out.println("4- Salir");
            System.out.println("--        --");
            option = Wrapper.validateNumber("Solo el ingreso de números es permitido");
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
                    option = Wrapper.validateNumber("Solo el ingreso de números es permitido");
                    if (option == 1) {
                        System.out.println("Ha salido del juego con éxito");
                        option = 4;
                    }
                    break;
                default:
                    System.out.println("Debes de ingresar un número entre uno y cuatro");
                    continue;
            }
        }
    }

    public static void addPlayer() {
        int age = 13;
        System.out.println("Ingrese nombre:");
        String name = Wrapper.validateString("El nombre solo puede contener letras y números");
        System.out.println("Ingrese edad:");
        while (age > 80 && age < 12) {
            age = Wrapper.validateNumber("Solo el ingreso de números es permitido");
        }
        Player newPlayer = new Player(name, age);
        persistence.addPlayer(newPlayer);
    }

}
