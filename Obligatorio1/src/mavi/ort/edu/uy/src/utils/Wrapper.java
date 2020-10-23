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

    public static int validateNumber(String warningMessage) {
        Scanner input = new Scanner(System.in);
        int inputInt = 0;
        while (inputInt == 0) {
            System.out.print("Ingreso: ");
            String inputStr = input.nextLine();
            if (inputStr.matches("\\d+")) {
                inputInt = Integer.parseInt(inputStr);
            }else{
                System.out.println(warningMessage);
            }
        }
        return inputInt;
    }

    public static String validateString(String warningMessage) {
        Scanner input = new Scanner(System.in);
        String auxStr = null;
        while (auxStr == null) {
            System.out.print("Ingreso: ");
            String inputStr = input.nextLine();
            if (inputStr.matches("\\w+\\.?")) {
                auxStr = inputStr;
            }else{
                System.out.println(warningMessage);
            }
        }
        return auxStr;
    }

}
