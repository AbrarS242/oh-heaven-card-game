package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jgamegrid.Actor;

import java.util.ArrayList;

import static ch.aplu.jgamegrid.Actor.delay;

public class NPCPlayer extends Player {
    private final int thinkingTime = 2000;
    private NPCPlayerStrategy strategy = new LegalPlayerStrategy();
    private ArrayList<PlayObserver> playObservers = new ArrayList<PlayObserver>();
    public Card pickCard() {
        delay(thinkingTime);
        return strategy.pickCard();
    }
    public String getFollowStatus() {
        return "Player " + playerNum + " thinking...";
    }
    public String getLeadStatus() {
        return "Player " + playerNum + " thinking...";
    }

    public void addPlayObserver(PlayObserver observer) {
        playObservers.add(observer);
    }

    // update() will need to take arguments such as:
    // card (as in card that was just played), player (that just played card), scores
    public void update(Card playedCard, Player playedBy) {
        for (PlayObserver observer: playObservers) {
            observer.update(playedCard, playedBy);
        }
    }
}
