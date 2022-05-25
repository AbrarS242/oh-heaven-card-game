package oh_heaven.game;
import ch.aplu.jcardgame.Card;

public class LegalPlayer extends Player {
    public Card pickCard() {


        return CardRandomiser.getInstance().randomCard(hand);
    }
}
