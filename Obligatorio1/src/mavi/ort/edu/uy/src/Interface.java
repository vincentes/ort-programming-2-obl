package mavi.ort.edu.uy.src;

import java.util.ArrayList;
import mavi.ort.edu.uy.src.models.Match;
import mavi.ort.edu.uy.src.models.Player;

/**
 *
 * @author matiassalle
 */
public class Interface {

    private ArrayList<Player> playersList;
    private ArrayList<Match> matchesList;
    

    public Interface() {
        playersList = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        playersList.add(player);
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
    
    public ArrayList<Match> getMatches() {
        return matchesList;
    }
    
    // Method to verify if a player is already on the playersList
     public boolean validatePlayerExistence(String name){
        boolean isPlayerPresent = false;
        for (Player p : playersList) {
            if(p.getName().equals(name)){
                isPlayerPresent = true;
            }
        }
        return isPlayerPresent;
    }

}
