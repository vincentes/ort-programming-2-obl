package mavi.ort.edu.uy.src.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import mavi.ort.edu.uy.src.models.Disc;

/**
 *
 * @authors Vicente Bermúdez - Matías Sallé
 */
public class Wrapper {

    public static boolean isValidNumber(String numberStr) {
        return numberStr.chars().allMatch(Character::isDigit);
    }
    // method to validate int (numbers as valid input)
    public static int validateNumber(String inputMessage, String warningMessage) {
        Scanner input = new Scanner(System.in);
        int inputInt = 0;
        while (inputInt == 0) {
            System.out.print(inputMessage + " ");
            String inputStr = input.nextLine();
            if (inputStr.matches("\\d+")) {
                inputInt = Integer.parseInt(inputStr);
            } else {
                System.out.println("\n" + warningMessage + "\n");
            }
        }
        return inputInt;
    }

    // method to validate string (numbers and letters as valid input)
    public static String validateString(String inputMessage, String warningMessage) {
        Scanner input = new Scanner(System.in);
        String auxStr = null;
        while (auxStr == null) {
            System.out.print(inputMessage + " ");
            String inputStr = input.nextLine();
            if (inputStr.matches("\\w+\\.?")) {
                auxStr = inputStr;
            } else {
                System.out.println("\n" + warningMessage + "\n");
            }
        }
        return auxStr;
    }
    
    // method to get current time e.g 01:02:33 26/10/2020
    public static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    // method to verify if two matrix are different
    public static boolean areDifferent(Disc A[][], Disc B[][]) {
        boolean areDifferent = false;
        int i, j;
        if(A == null && B != null){
            areDifferent = true;
        }
        if(B == null && A != null){
            areDifferent = true;
        }
        if(A != null && B != null && !areDifferent){
            for (i = 0; i < A.length; i++) {
            for (j = 0; j < A[i].length; j++) {
                if (A[i][j] != B[i][j]) {
                    areDifferent = true;
                }
            }
        }
        }
        return areDifferent;
    }

}
