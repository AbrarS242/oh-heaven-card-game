package oh_heaven.game;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class LegalPlayerStrategy implements NPCPlayerStrategy {
    Hand hand;
    public Card pickCard(PlayObserver playObserver, Hand hand) {
        ArrayList<Card> legalCards = new ArrayList<>();
        for (Card card: this.hand.getCardList()) {
            if (!Referee.getInstance().ruleBroken(card, this.hand)) {
                legalCards.add(card);
            }
        }
        if (legalCards.isEmpty()) {
            return CardRandomiser.getInstance().randomCard(this.hand);
        }
        else {
            return CardRandomiser.getInstance().randomCard(legalCards);
        }
    }
}
