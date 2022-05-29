package oh_heaven.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class Referee {

    private static Oh_Heaven game;
    private static Referee instance;
    private boolean enforceRules=false;

    public static Referee getInstance() {
        if (instance == null) {
            instance = new Referee();
        }
        return instance;
    }

    public boolean ruleBroken(Card card, Hand hand) {
        if (card.getSuit() != game.getLead() && hand.getNumberOfCardsWithSuit(game.getLead()) > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void violationResponse(int nextPlayer, Card card) {
        // Rule violation
        String violation = "Follow rule broken by player " + nextPlayer + " attempting to play " + card;
        System.out.println(violation);
        if (enforceRules)
            try {
                throw(new BrokeRuleException(violation));
            } catch (BrokeRuleException e) {
                e.printStackTrace();
                System.out.println("A cheating player spoiled the game!");
                System.exit(0);
            }
    }

    public static void setGame(Oh_Heaven game) {
        Referee.game = game;
    }
}
