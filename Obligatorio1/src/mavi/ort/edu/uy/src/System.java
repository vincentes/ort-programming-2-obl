/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavi.ort.edu.uy.src;

import java.util.List;
import mavi.ort.edu.uy.src.models.Board;
import mavi.ort.edu.uy.src.models.Match;

/**
 *
 * @author vicentebermudez
 */
public class System {

    public List<Match> matches;
    public List<Player> players;
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board();  
        b.print();
    }
    
}
