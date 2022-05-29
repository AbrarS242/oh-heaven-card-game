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
    private int playCount = 0;

    private Oh_Heaven.Suit trump;
    private Oh_Heaven.Suit lead;
    private Card winningCard;

    public void update(Card cardPlayed, int playerNum, int[] scores, int[] tricks, Oh_Heaven.Suit trump, Oh_Heaven.Suit lead, Card winningCard){
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
        this.winningCard = winningCard;
        playCount++;
    }

    // for the start of each round, sets trump suit and resets lead.
    public void update(Oh_Heaven.Suit trump) {
        this.trump = trump;
        this.lead = null;
    }


    public Oh_Heaven.Suit getTrumpSuit() {
        return trump;
    }


    public int getPlayNo() {
        return playCount;
    }

    public Oh_Heaven.Suit getLeadSuit() {
        return lead;
    }

    public int getLeadTrumpRankId() {
        if (winningCard.getSuit() != trump) {
            return 0;
        }
        else {
            return winningCard.getRankId();
        }
    }

    public int getWinningRankId() {
        return winningCard.getRankId();
    }
}
