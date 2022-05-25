package oh_heaven.game;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.CardAdapter;
import ch.aplu.jcardgame.CardListener;
import ch.aplu.jcardgame.Hand;
import ch.aplu.jgamegrid.*;

import static ch.aplu.jgamegrid.Actor.delay;

public class ActivePlayer extends Player {

    Card selected;

    public void setHand(Hand hand) {
        super.setHand(hand);
        // Set up human player for interaction
        CardListener cardListener = new CardAdapter()  // Human Player plays card
        {
            public void leftDoubleClicked(Card card) {selected = card; hand.setTouchEnabled(false); }
        };
        hand.addCardListener(cardListener);
    }

    public Card pickCard() {
        hand.setTouchEnabled(true);
        while (null == selected) delay(100);
        Card cardPlay = selected;
        selected = null;
        return cardPlay;
    }

    public String getFollowStatus() {
        return "Player 0 double-click on card to follow.";
    }
    public String getLeadStatus() {
        return "Player 0 double-click on card to lead.";
    }
}
