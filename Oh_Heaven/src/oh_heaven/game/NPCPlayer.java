package oh_heaven.game;

import ch.aplu.jcardgame.Card;

import static ch.aplu.jgamegrid.Actor.delay;

public abstract class NPCPlayer extends Player {
    private final int thinkingTime = 2000;
    public Card pickCard() {
        delay(thinkingTime);
        return this.pickCard();
    }
    public String getFollowStatus() {
        return "Player " + playerNum + " thinking...";
    }
    public String getLeadStatus() {
        return "Player " + playerNum + " thinking...";
    }
}
