package oh_heaven.game;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import oh_heaven.utility.CardRandomiser;


public class RandomPlayerStrategy implements NPCPlayerStrategy {

    public Card pickCard(PlayHistory playHistory, Hand hand) {
        return CardRandomiser.getInstance().randomCard(hand);
    }
}
