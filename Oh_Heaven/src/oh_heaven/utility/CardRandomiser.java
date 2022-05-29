package oh_heaven.utility;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Random;

public class CardRandomiser {

    private static CardRandomiser instance;

    public static CardRandomiser getInstance() {
        if (instance == null) {
            instance = new CardRandomiser();
        }
        return instance;
    }

    public static Random random;

    // return random Enum value
    public <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    // return random Card from Hand
    public Card randomCard(Hand hand){
        int x = random.nextInt(hand.getNumberOfCards());
        return hand.get(x);
    }

    // return random Card from ArrayList
    public Card randomCard(ArrayList<Card> list){
        int x = random.nextInt(list.size());
        return list.get(x);
    }

    public void initCardRandomiser(Long seed){
        if (random == null) {
            if (seed == null) {
                random = new Random();
                System.out.println("Seed = null");
            } else {
                random = new Random(seed);
                System.out.println("Seed = " + seed);
            }
        }
    }

    public Random get() { return random; }

}
