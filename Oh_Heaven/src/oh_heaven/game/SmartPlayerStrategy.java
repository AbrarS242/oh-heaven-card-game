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
        Card leadMax;
        Card minCard;
        Card leadMin;
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


        if (leadMax == null ) { // if there is no card of the leading suit in hand
            if (trumpMax == null || trumpMax.getRankId() < playHistory.getLeadTrumpRankId()) {
                // if there is no card of trump suit OR the highest ranking card in hand of the trump will not win
                return minCard;
            }
            return trumpMax;
        }
        // if the highest ranking card of the lead suit will not win
        if (leadMax.getRankId() < playHistory.getWinningRankId()) {
            return minCard;
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
