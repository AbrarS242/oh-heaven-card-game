package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class PlayPublisher {

    private Player[] players;
    private int nbPlayers;
    public PlayPublisher(Player[] players, int nbPlayers){
        this.players = players;
        this.nbPlayers = nbPlayers;
    }

    public void notifyPlayers(Card cardPlayed, int playerNum, int[] scores, int[] tricks){
        for (int i = 0; i < nbPlayers; i++) {
            if (players[i] instanceof NPCPlayer){
                ((NPCPlayer) players[i]).update(cardPlayed, playerNum, scores, tricks);
            }
        }
    }


}
