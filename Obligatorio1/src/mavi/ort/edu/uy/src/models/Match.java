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
    public Date date;
    public String name;
    public List<Step> steps;
    public Disc[][] discs; 
    public Player playerRed;
    public Player playerBlue;
    public String result;
}
