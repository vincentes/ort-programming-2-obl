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
public class Step {
    private Board board;
    private String movementDescription;

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * @return the movementDescription
     */
    public String getMovementDescription() {
        return movementDescription;
    }

    /**
     * @param movementDescription the movementDescription to set
     */
    public void setMovementDescription(String movementDescription) {
        this.movementDescription = movementDescription;
    }
}
