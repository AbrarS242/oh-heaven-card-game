package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jgamegrid.Actor;

import java.util.ArrayList;

import static ch.aplu.jgamegrid.Actor.delay;

public class NPCPlayer extends Player {

    private final int thinkingTime = 2000;
    private NPCPlayerStrategy strategy;
    private PlayHistory playHistory;


    public NPCPlayer(NPCPlayerStrategy strategy, int playerNum) {
        this.playerNum = playerNum;
        this.strategy = strategy;
        this.playHistory = new PlayHistory();
    }

    // Select a card from the hand according to strategy
    public Card pickCard() {
        delay(thinkingTime);
        return strategy.pickCard(playHistory, hand);
    }


    public String getFollowStatus() {
        return "Player " + playerNum + " thinking...";
    }
    public String getLeadStatus() {
        return "Player " + playerNum + " thinking...";
    }

    // Update the play history
    public void update(Card playedCard, int playerNum, int[] scores, int[] tricks, Oh_Heaven.Suit trump, Oh_Heaven.Suit lead, Card winningCard) {
        playHistory.update(playedCard, playerNum, scores, tricks, trump, lead, winningCard);
    }

    public void update(Oh_Heaven.Suit trump) {
        playHistory.update(trump);
    }
}

