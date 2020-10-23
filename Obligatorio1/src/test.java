/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import mavi.ort.edu.uy.src.models.Board;
import mavi.ort.edu.uy.src.models.Color;
import mavi.ort.edu.uy.src.models.Disc;

/**
 *
 * @author matiassalle
 */
public class test {

    public static void main(String args[]) {
        Disc[][] discs = new Disc[8][8];
        Disc blueDisc = new Disc();
        blueDisc.setColor(Color.BLUE);
        Disc redDisc = new Disc();
        redDisc.setColor(Color.RED);

        discs[1][0] = blueDisc;
        discs[1][1] = redDisc;
        discs[1][2] = blueDisc;
        discs[1][3] = redDisc;
        discs[1][4] = blueDisc;
        discs[1][5] = redDisc;
        discs[1][6] = blueDisc;
        discs[1][7] = redDisc;

        Board.print(discs);
        getWinner(discs);
    }

    public static void getWinner(Disc[][] discs) {
       

    }

}
