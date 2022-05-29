package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import oh_heaven.utility.CardRandomiser;

public class SmartPlayerStrategy implements NPCPlayerStrategy {
// need to make it that somehow SmartPlayerStrategy has access to an instance of SmartHistory

    // problem here, the parameter needs to be of type SmartHistory not PlayObserver. How do we solve this?
    public Card pickCard(PlayHistory playHistory, Hand hand) {
        // look for trump suit cards, play highest card (if it will win)
        // look for lead suit cards, play highest card (if it will win)
        Card trumpMax = getMaxCardOfSuit(hand, playHistory.getTrumpSuit());
        Card leadMax = null;
        Card minCard = null;
        // playHistory would contain a playNo variable which would just be a count of how many plays there have been in total
        if (playHistory.getPlayNo() % 4 == 0) {
            if (trumpMax != null) {
                return trumpMax;
            }
            else {
                return getMaxCard(hand);
            }
        }

        leadMax = getMaxCardOfSuit(hand, playHistory.getLeadSuit());
        minCard = getMinCard(hand);

        if (leadMax == null) {
            if (trumpMax == null || trumpMax.getRankId() < playHistory.getLeadTrumpRankId()) {
                return minCard;
            }
            return trumpMax;
        }
        return leadMax;
    }

    public Card getMaxCardOfSuit(Hand hand, Oh_Heaven.Suit suit) {
        Card maxCardOfSuit = null;
        for (Card card : hand.getCardList()) {
            if (card.getSuit() == suit) {
                if (maxCardOfSuit == null) {
                    maxCardOfSuit = card;
                } else if (maxCardOfSuit.getRankId() < card.getRankId()) {
                    maxCardOfSuit = card;
                }
            }
        }
        return maxCardOfSuit;
    }

    public Card getMaxCard(Hand hand) {
        Card maxCard = null;
        for (Card card : hand.getCardList()) {
            if (maxCard == null) {
                maxCard = card;
            } else if (maxCard.getRankId() < card.getRankId()) {
                maxCard = card;
            }
        }
        return maxCard;
    }

    public Card getMinCard(Hand hand) {
        Card minCard = null;
        for (Card card : hand.getCardList()) {
            if (minCard == null) {
                minCard = card;
            } else if (minCard.getRankId() > card.getRankId()) {
                minCard = card;
            }
        }
        return minCard;
    }

}
