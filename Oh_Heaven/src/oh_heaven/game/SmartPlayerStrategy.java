package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class SmartPlayerStrategy implements NPCPlayerStrategy {
// need to make it that somehow SmartPlayerStrategy has access to an instance of SmartHistory

    // problem here, the parameter needs to be of type SmartHistory not PlayObserver. How do we solve this?
    public Card pickCard(PlayObserver playObserver, Hand hand) {

    }
}
