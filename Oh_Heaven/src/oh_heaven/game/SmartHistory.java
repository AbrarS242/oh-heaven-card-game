package oh_heaven.game;

import ch.aplu.jcardgame.*;

import java.util.ArrayList;

public class SmartHistory extends PlayObserver{
    public enum Suit
    {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    ArrayList<Card> playedClubCards = new ArrayList<Card>();
    ArrayList<Card> playedDiamondCards = new ArrayList<Card>();
    ArrayList<Card> playedHeartCards = new ArrayList<Card>();
    ArrayList<Card> playedSpadeCards = new ArrayList<Card>();

    public void update(Card cardPlayed, Player playedBy) {
        if (cardPlayed.getSuit() == Suit.CLUBS) {
            playedClubCards.add(cardPlayed);
        }
        else if (cardPlayed.getSuit() == Suit.DIAMONDS) {
            playedDiamondCards.add(cardPlayed);
        }
        else if (cardPlayed.getSuit() == Suit.HEARTS) {
            playedHeartCards.add(cardPlayed);
        }
        else if (cardPlayed.getSuit() == Suit.SPADES) {
            playedSpadeCards.add(cardPlayed);
        }
    }
}
