package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class SmartPlayerStrategy implements NPCPlayerStrategy {
// need to make it that somehow SmartPlayerStrategy has access to an instance of SmartHistory

    // problem here, the parameter needs to be of type SmartHistory not PlayObserver. How do we solve this?
    public Card pickCard(PlayHistory playHistory, Hand hand) {
        // look for trump suit cards, play highest card (if it will win)
        // look for lead suit cards, play highest card (if it will win)
        Card trumpMax = null;
        Card leadMax = null;
        Card minCard = null;
        for (Card card: hand.getCardList()) {
            if (card.getSuit() == playHistory.getTrumpSuit()) {
                if (trumpMax == null) {
                    trumpMax = card;
                }
                else if (trumpMax.getRankId() < card.getRankId()) {
                    trumpMax = card;
                }
            }
            else if (card.getSuit() == playHistory.getLeadSuit()) {
                if (leadMax == null) {
                    leadMax = card;
                }
                else if (leadMax.getRankId() < card.getRankId()) {
                    leadMax = card;
                }
            }
            else if (minCard == null || minCard.getRankId() > card.getRankId()) {
                minCard = card;
            }
        }
        if (trumpMax == null || trumpMax.getRankId() < playHistory.getLeadTrumpRankId) {
            if (leadMax == null || leadMax.getRankId() < playHistory.getLeadLeadRankId) {
                return minCard;
            }
            return leadMax;
        }
        return trumpMax;
    }
}
