package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public interface NPCPlayerStrategy {
    public abstract Card pickCard(PlayHistory playHistory, Hand hand);
}
