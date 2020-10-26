/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src.models;

/**
 *
 * @author vicentebermudez
 */
public class Disc {
    private Color color;
    private Player player;

    public Disc(Color color) {
        this.color = color;
    }

    public Disc() {
        
    }
       
    public String getConsoleColor() {
        return color.getConsoleColor();
    }
    
    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    
        
}
