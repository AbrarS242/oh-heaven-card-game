package oh_heaven.game;

import ch.aplu.jcardgame.*;

import java.util.ArrayList;

public class SmartHistory extends PlayObserver{


    private ArrayList<Card> playedClubCards = new ArrayList<Card>();
    private ArrayList<Card> playedDiamondCards = new ArrayList<Card>();
    private ArrayList<Card> playedHeartCards = new ArrayList<Card>();
    private ArrayList<Card> playedSpadeCards = new ArrayList<Card>();
    private Oh_Heaven.Suit trumpSuit;
    private Oh_Heaven.Suit leadSuit;


    public void update(Card cardPlayed, Player playedBy, Oh_Heaven.Suit trumpSuit, Oh_Heaven.Suit leadSuit) {
        if (cardPlayed.getSuit() == Oh_Heaven.Suit.CLUBS) {
            playedClubCards.add(cardPlayed);
        }
        else if (cardPlayed.getSuit() == Oh_Heaven.Suit.DIAMONDS) {
            playedDiamondCards.add(cardPlayed);
        }
        else if (cardPlayed.getSuit() == Oh_Heaven.Suit.HEARTS) {
            playedHeartCards.add(cardPlayed);
        }
        else if (cardPlayed.getSuit() == Oh_Heaven.Suit.SPADES) {
            playedSpadeCards.add(cardPlayed);
        }

        this.trumpSuit = trumpSuit;
        this.leadSuit = leadSuit;
    }

    public Oh_Heaven.Suit getTrumpSuit() {
        return this.trumpSuit;
    }

    public Oh_Heaven.Suit getLeadSuit() {
        return this.leadSuit;
    }
}
