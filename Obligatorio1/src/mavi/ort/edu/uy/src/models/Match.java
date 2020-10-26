/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author vicentebermudez
 */
public class Match {
    private Date date;
    private String name;
    private List<Step> steps;
    private Disc[][] discs; 
    private Player playerRed;
    private Player playerBlue;
    private String result;
    
     public Match(Date date, String name, List<Step> steps, Disc[][] discs, Player playerRed, Player playerBlue, String result) {
        date = date;
        steps = steps;
        discs = discs;
        playerRed = playerRed;
        playerBlue = playerBlue;
        result = result;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the steps
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * @param steps the steps to set
     */
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    /**
     * @return the discs
     */
    public Disc[][] getDiscs() {
        return discs;
    }

    /**
     * @param discs the discs to set
     */
    public void setDiscs(Disc[][] discs) {
        this.discs = discs;
    }

    /**
     * @return the playerRed
     */
    public Player getPlayerRed() {
        return playerRed;
    }

    /**
     * @param playerRed the playerRed to set
     */
    public void setPlayerRed(Player playerRed) {
        this.playerRed = playerRed;
    }

    /**
     * @return the playerBlue
     */
    public Player getPlayerBlue() {
        return playerBlue;
    }

    /**
     * @param playerBlue the playerBlue to set
     */
    public void setPlayerBlue(Player playerBlue) {
        this.playerBlue = playerBlue;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }
}
