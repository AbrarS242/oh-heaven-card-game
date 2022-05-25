package oh_heaven.game;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class ActivePlayer extends Player {

    public void setHand(Hand hand) {
        super.setHand(hand);

    }

    public Card pickCard() {
        return new Card();
    }
}
