package oh_heaven.game;

import ch.aplu.jcardgame.Card;

import java.util.ArrayList;

public abstract class PlayObserver {

    public abstract void update(Card cardPlayed, Player playedBy, Oh_Heaven.Suit trumpSuit, Oh_Heaven.Suit leadSuit);

}
