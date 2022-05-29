package oh_heaven.utility;

import oh_heaven.game.CardRandomiser;

import java.util.Random;

public class ServicesRandom {
    private static Random random;

    public static void initServicesRandom(Long seed){
        if (random == null) {
            if (seed == null) {
                random = new Random();
                System.out.println("Seed = null");
            } else {
                random = new Random(seed);
                System.out.println("Seed = " + seed);
            }
            CardRandomiser.setRandom(random);
        }
    }

    public static Random get() { return random; }
}
