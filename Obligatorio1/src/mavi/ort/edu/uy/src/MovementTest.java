/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src;

import mavi.ort.edu.uy.src.models.Board;
import mavi.ort.edu.uy.src.models.Color;
import mavi.ort.edu.uy.src.models.Compass;
import mavi.ort.edu.uy.src.models.Disc;

/**
 *
 * @author vicentebermudez
 */
public class MovementTest {
    public static void main(String args[]){
        Disc[][] discs = new Disc[8][8];
        Disc blueDisc = new Disc();
        blueDisc.setColor(Color.BLUE);
        Disc redDisc = new Disc();
        redDisc.setColor(Color.RED);

        
        Board board = Board.getDefaultBoard();
        
        board.move(1, Compass.EAST, 2);
        board.print();

        board.move(3, Compass.EAST, 2);
        board.print();
        
        board.move(2, Compass.SOUTH, 3);
        board.print();

        board.move(3, Compass.WEST, 5);
        board.print();

        board.move(5, Compass.EAST, 2);
        board.print();
        
        board.move(5, Compass.WEST, 4);
        board.print();
        
        board.move(4, Compass.EAST, 5);
        board.print();
        
        board.move(2, Compass.NORTH, 3);
        board.print();
        
    }
}
