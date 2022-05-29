package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jgamegrid.Actor;

import java.util.ArrayList;

import static ch.aplu.jgamegrid.Actor.delay;

public class NPCPlayer extends Player {
    private final int thinkingTime = 2000;
    private NPCPlayerStrategy strategy;
    public NPCPlayer(NPCPlayerStrategy strategy) {
        this.strategy = strategy;
    }

    // will need to fix this below line as they are hardcoded for legalPlayerStrategy and SmartHistory
    // to be fixed in factory/player creator
    private PlayObserver playObserver;
    public Card pickCard() {
        delay(thinkingTime);
        return strategy.pickCard(playObserver, hand);
    }
    public String getFollowStatus() {
        return "Player " + playerNum + " thinking...";
    }
    public String getLeadStatus() {
        return "Player " + playerNum + " thinking...";
    }

    // potentially instantiate the playObserver through this??
    public void PlayObserver(PlayObserver observer) {
        this.playObserver = observer;
    }

    // update() will need to take arguments such as:
    // card (as in card that was just played), player (that just played card), scores
    public void update(Card playedCard, Player playedBy, Oh_Heaven.Suit trumpSuit, Oh_Heaven.Suit leadSuit) {
        playObserver.update(playedCard, playedBy, trumpSuit, leadSuit);
    }
}
