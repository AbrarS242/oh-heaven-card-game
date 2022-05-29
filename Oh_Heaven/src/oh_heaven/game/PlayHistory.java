package oh_heaven.game;

import ch.aplu.jcardgame.Card;

import java.util.ArrayList;

public class PlayHistory {

    private ArrayList<Card> playedClubCards = new ArrayList<Card>();
    private ArrayList<Integer> clubPlayers = new ArrayList<>();
    private ArrayList<Card> playedDiamondCards = new ArrayList<Card>();
    private ArrayList<Integer> diamondPlayers = new ArrayList<>();
    private ArrayList<Card> playedHeartCards = new ArrayList<Card>();
    private ArrayList<Integer> heartPlayers = new ArrayList<>();
    private ArrayList<Card> playedSpadeCards = new ArrayList<Card>();
    private ArrayList<Integer> spadePlayers = new ArrayList<>();

    private int[] scores;
    private int[] tricks;

    private Oh_Heaven.Suit trump;
    private Oh_Heaven.Suit lead;

    public void update(Card cardPlayed, int playerNum, int[] scores, int[] tricks, Oh_Heaven.Suit trump, Oh_Heaven.Suit lead){
        if (cardPlayed.getSuit() == Oh_Heaven.Suit.CLUBS) {
            playedClubCards.add(cardPlayed);
            clubPlayers.add(playerNum);
        }
        else if (cardPlayed.getSuit() == Oh_Heaven.Suit.DIAMONDS) {
            playedDiamondCards.add(cardPlayed);
            diamondPlayers.add(playerNum);
        }
        else if (cardPlayed.getSuit() == Oh_Heaven.Suit.HEARTS) {
            playedHeartCards.add(cardPlayed);
            heartPlayers.add(playerNum);
        }
        else if (cardPlayed.getSuit() == Oh_Heaven.Suit.SPADES) {
            playedSpadeCards.add(cardPlayed);
            spadePlayers.add(playerNum);
        }
        this.scores = scores;
        this.tricks = tricks;
        this.trump = trump;
        this.lead = lead;
    }


}
