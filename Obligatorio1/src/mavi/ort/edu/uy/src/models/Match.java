/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src.models;

import java.util.List;

/**
 *
 * @author vicentebermudez
 */
public class Match {

    private String date;
    private String name;
    private List<Step> steps;
    private Player playerRed;
    private Player playerBlue;
    private ResultCode result;

    public Match(String date, String name, List<Step> steps, Player playerRed, Player playerBlue, ResultCode result) {
        this.setDate(date);
        this.setName(name);
        this.setSteps(steps);
        this.setPlayerRed(playerRed);
        this.setPlayerBlue(playerBlue);
        this.setResult(result);
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
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
    public ResultCode getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(ResultCode result) {
        this.result = result;
    }
    
}
