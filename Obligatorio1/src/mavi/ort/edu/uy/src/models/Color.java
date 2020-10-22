/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src.models;

import mavi.ort.edu.uy.src.constants.ConsoleColor;

/**
 *
 * @author vicentebermudez
 */
public enum Color {
    RED(ConsoleColor.RED), BLUE(ConsoleColor.GREEN);
    
    private String consoleColor;
    
    Color(String consoleColor) {
        this.consoleColor = consoleColor;
    }

    /**
     * @return the consoleColor
     */
    public String getConsoleColor() {
        return consoleColor;
    }
    
}
