package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public abstract class Player {

    Hand hand;
    int playerNum = 0;

    public abstract Card pickCard();
    public abstract String getFollowStatus();
    public abstract String getLeadStatus();

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
