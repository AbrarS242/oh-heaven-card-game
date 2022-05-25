package oh_heaven.game;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;


public class RandomPlayer extends Player {
    public Card pickCard() {
        return CardRandomiser.getInstance().randomCard(hand);
    }
}
