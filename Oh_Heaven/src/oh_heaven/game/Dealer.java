package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;
import oh_heaven.utility.CardRandomiser;

public class Dealer {

    public void dealingOut(Deck deck, Player[] players, int nbPlayers, int nbCardsPerPlayer) {
        Hand pack = deck.toHand(false);
        // pack.setView(Oh_Heaven.this, new RowLayout(hideLocation, 0));
        for (int i = 0; i < nbCardsPerPlayer; i++) {
            for (int j=0; j < nbPlayers; j++) {
                if (pack.isEmpty()) return;
                Card dealt = CardRandomiser.getInstance().randomCard(pack);
                // System.out.println("Cards = " + dealt);
                dealt.removeFromHand(false);
                players[j].getHand().insert(dealt, false);
                // dealt.transfer(hands[j], true);
            }
        }
    }
}
