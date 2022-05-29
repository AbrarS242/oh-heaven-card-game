package oh_heaven.game;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class LegalPlayerStrategy implements NPCPlayerStrategy {
    public Card pickCard(PlayObserver playObserver, Hand hand) {
        ArrayList<Card> legalCards = new ArrayList<>();
        for (Card card: hand.getCardList()) {
            if (!Referee.getInstance().ruleBroken(card, hand)) {
                legalCards.add(card);
            }
        }
        if (legalCards.isEmpty()) {
            return CardRandomiser.getInstance().randomCard(hand);
        }
        else {
            return CardRandomiser.getInstance().randomCard(legalCards);
        }
    }
}
