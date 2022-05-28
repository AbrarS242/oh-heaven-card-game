package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jgamegrid.Actor;

import static ch.aplu.jgamegrid.Actor.delay;

public class NPCPlayer extends Player {
    private final int thinkingTime = 2000;
    private NPCPlayerStrategy strategy = new LegalPlayerStrategy();
    public Card pickCard() {
        delay(thinkingTime);
        return strategy.pickCard();
    }
    public String getFollowStatus() {
        return "Player " + playerNum + " thinking...";
    }
    public String getLeadStatus() {
        return "Player " + playerNum + " thinking...";
    }
}
