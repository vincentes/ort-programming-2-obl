/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src.utils;

import mavi.ort.edu.uy.src.constants.ConsoleColor;


/**
 *
 * @author vicentebermudez
 */
public class PrettyPrint {
    public static void print(String message, String foreground) {
        System.out.print(foreground);
        System.out.print(message);
        System.out.print(ConsoleColor.RESET);
    }

    public static void print(char message, String foreground, String background) {
        System.out.print(foreground);
        System.out.print(background);
        System.out.print(message);
        System.out.print(ConsoleColor.RESET);
    }
    
    public static void print(String message, String foreground, String background) {
        System.out.print(foreground);
        System.out.print(background);
        System.out.print(message);
        System.out.print(ConsoleColor.RESET);
    }

    public static void println(char message, String foreground) {
        System.out.print(foreground);
        System.out.print(message);
        System.out.print(ConsoleColor.RESET);
    }
    
    public static void println(String message, String foreground) {
        System.out.print(foreground);
        System.out.print(message);
        System.out.print(ConsoleColor.RESET);
    }
    
    public static void println(char message, String foreground, String background) {
        System.out.println();
        System.out.print(foreground);
        System.out.print(background);
        System.out.println(message);
        System.out.print(ConsoleColor.RESET);
    }
    
    public static void println(String message, String foreground, String background) {
        System.out.print(foreground);
        System.out.print(background);
        System.out.println(message);
        System.out.print(ConsoleColor.RESET);
    }
}
