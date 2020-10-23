/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src;

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
            System.out.println("-- Grupos --");
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
                    persistence.getPlayersList();
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
        int age = 100;
        String name = Wrapper.validateString("Ingrese nombre:", "El nombre solo puede contener letras y números");
        while (age >= 80 || age <= 12) {
            age = Wrapper.validateNumber("Ingrese edad:", "Solo el ingreso de números es permitido");
            if(age >= 80 || age <= 12){
                System.out.println("Rango de edad permitido [12 - 80]");
            }
        }
        Player newPlayer = new Player(name, age);
        persistence.addPlayer(newPlayer);
    }

}
