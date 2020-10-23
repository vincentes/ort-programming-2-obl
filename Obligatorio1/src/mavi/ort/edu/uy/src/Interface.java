package mavi.ort.edu.uy.src;

import java.util.ArrayList;
import mavi.ort.edu.uy.src.models.Player;

/**
 *
 * @author matiassalle
 */
public class Interface {

    private ArrayList<Player> playersList;

    public Interface() {
        playersList = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        playersList.add(player);
    }

    public ArrayList<Player> getPlayersList() {
        int count = 1;
        for (Player p : playersList) {
            System.out.println(count + "- " + p.getName());
            count++;
        }
        return playersList;
    }

}
