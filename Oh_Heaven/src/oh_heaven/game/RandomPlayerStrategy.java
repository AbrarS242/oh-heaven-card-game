package oh_heaven.game;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;


public class RandomPlayerStrategy implements NPCPlayerStrategy {

    public Card pickCard(PlayObserver playObserver, Hand hand) {
        return CardRandomiser.getInstance().randomCard(hand);
    }
}
