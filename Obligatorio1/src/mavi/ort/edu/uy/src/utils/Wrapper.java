package mavi.ort.edu.uy.src.utils;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author matiassalle
 */
public class Wrapper {

    public static boolean isValidNumber(String numberStr) {
        return numberStr.chars().allMatch(Character::isDigit);
    }
    
    public static int validateNumber(String inputMessage, String warningMessage) {
        Scanner input = new Scanner(System.in);
        int inputInt = 0;
        while (inputInt == 0) {
            System.out.print(inputMessage+" ");
            String inputStr = input.nextLine();
            if (inputStr.matches("\\d+")) {
                inputInt = Integer.parseInt(inputStr);
            }else{
                System.out.println("\n"+warningMessage+"\n");
            }
        }
        return inputInt;
    }

    public static String validateString(String inputMessage, String warningMessage) {
        Scanner input = new Scanner(System.in);
        String auxStr = null;
        while (auxStr == null) {
            System.out.print(inputMessage+" ");
            String inputStr = input.nextLine();
            if (inputStr.matches("\\w+\\.?")) {
                auxStr = inputStr;
            }else{
                System.out.println("\n"+warningMessage+"\n");
            }
        }
        return auxStr;
    }

}
