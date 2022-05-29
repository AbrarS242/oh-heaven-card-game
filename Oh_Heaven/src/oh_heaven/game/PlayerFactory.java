package oh_heaven.game;

public class PlayerFactory {
    private static final String HUMAN_PLAYER = "human";
    private static final String RANDOM_PLAYER = "random";
    private static final String LEGAL_PLAYER = "legal";
    private static final String SMART_PLAYER = "smart";

    private Player player = null;

    public Player createPlayer(String playerType){
        if (playerType.equals(HUMAN_PLAYER)){
            player = new ActivePlayer();
        } else if (playerType.equals(RANDOM_PLAYER)){
            player = new NPCPlayer(new RandomPlayerStrategy());
        } else if (playerType.equals(LEGAL_PLAYER)){
            player = new NPCPlayer(new LegalPlayerStrategy());
        } else if (playerType.equals(SMART_PLAYER)){
            player = new NPCPlayer(new SmartPlayerStrategy());
        }
        return player;
    }
}
