package oh_heaven.game;

import oh_heaven.utility.CardRandomiser;
import oh_heaven.utility.PropertiesLoader;

// Oh_Heaven.java

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class Oh_Heaven extends CardGame {

  private Properties properties;

  public enum Suit
  {
    SPADES, HEARTS, DIAMONDS, CLUBS
  }

  public enum Rank
  {
    // Reverse order of rank importance (see rankGreater() below)
	// Order of cards is tied to card images
	ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO
  }
  
  final String trumpImage[] = {"bigspade.gif","bigheart.gif","bigdiamond.gif","bigclub.gif"};
  
  public boolean rankGreater(Card card1, Card card2) {
	  return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
  }
	 
  private final String version = "1.0";
  public final int nbPlayers = 4;
  public int nbStartCards = 13;
  public int nbRounds = 3;
  public final int madeBidBonus = 10;
  private final int handWidth = 400;
  private final int trickWidth = 40;
  private final Deck deck = new Deck(Suit.values(), Rank.values(), "cover");
  private final Location[] handLocations = {
			  new Location(350, 625),
			  new Location(75, 350),
			  new Location(350, 75),
			  new Location(625, 350)
	  };
  private final Location[] scoreLocations = {
			  new Location(575, 675),
			  new Location(25, 575),
			  new Location(575, 25),
			  // new Location(650, 575)
			  new Location(575, 575)
	  };
  private Actor[] scoreActors = {null, null, null, null };
  private final Location trickLocation = new Location(350, 350);
  private final Location textLocation = new Location(350, 450);
  private Player[] players;
  private Dealer dealer = new Dealer();
  private Location hideLocation = new Location(-500, - 500);
  private Location trumpsActorLocation = new Location(50, 50);

  public void setStatus(String string) { setStatusText(string); }
  
private int[] scores = new int[nbPlayers];
private int[] tricks = new int[nbPlayers];
private int[] bids = new int[nbPlayers];

Font bigFont = new Font("Serif", Font.BOLD, 36);

private void initScore() {
	 for (int i = 0; i < nbPlayers; i++) {
		 // scores[i] = 0;
		 String text = "[" + String.valueOf(scores[i]) + "]" + String.valueOf(tricks[i]) + "/" + String.valueOf(bids[i]);
		 scoreActors[i] = new TextActor(text, Color.WHITE, bgColor, bigFont);
		 addActor(scoreActors[i], scoreLocations[i]);
	 }
  }

private void updateScore(int player) {
	removeActor(scoreActors[player]);
	String text = "[" + String.valueOf(scores[player]) + "]" + String.valueOf(tricks[player]) + "/" + String.valueOf(bids[player]);
	scoreActors[player] = new TextActor(text, Color.WHITE, bgColor, bigFont);
	addActor(scoreActors[player], scoreLocations[player]);
}

private void initScores() {
	 for (int i = 0; i < nbPlayers; i++) {
		 scores[i] = 0;
	 }
}

private void updateScores() {
	 for (int i = 0; i < nbPlayers; i++) {
		 scores[i] += tricks[i];
		 if (tricks[i] == bids[i]) scores[i] += madeBidBonus;
	 }
}

private void initTricks() {
	 for (int i = 0; i < nbPlayers; i++) {
		 tricks[i] = 0;
	 }
}

private void initBids(Suit trumps, int nextPlayer) {
	int total = 0;
	for (int i = nextPlayer; i < nextPlayer + nbPlayers; i++) {
		 int iP = i % nbPlayers;
		 bids[iP] = nbStartCards / 4 + CardRandomiser.getInstance().get().nextInt(2);
		 total += bids[iP];
	 }
	 if (total == nbStartCards) {  // Force last bid so not every bid possible
		 int iP = (nextPlayer + nbPlayers) % nbPlayers;
		 if (bids[iP] == 0) {
			 bids[iP] = 1;
		 } else {
			 bids[iP] += CardRandomiser.getInstance().get().nextBoolean() ? -1 : 1;
		 }
	 }
	// for (int i = 0; i < nbPlayers; i++) {
	// 	 bids[i] = nbStartCards / 4 + 1;
	//  }
 }

private Card selected;
private Player nextPlay;
private Suit lead;

private void initRound() {
		players = new Player[nbPlayers];
		PlayerFactory playerFactory = new PlayerFactory();

		// Create players
		for (int i = 0; i < nbPlayers; i++){
			String playerType = properties.getProperty("players." + i);
			players[i] = playerFactory.createPlayer(playerType, i);
		}


		for (int i = 0; i < nbPlayers; i++) {
			   players[i].setHand(new Hand(deck));
		}
		dealer.dealingOut(deck,players, nbPlayers, nbStartCards);
		 for (int i = 0; i < nbPlayers; i++) {
			   players[i].getHand().sort(Hand.SortType.SUITPRIORITY, true);
		 }
		 // graphics
	    RowLayout[] layouts = new RowLayout[nbPlayers];
	    for (int i = 0; i < nbPlayers; i++) {
	      layouts[i] = new RowLayout(handLocations[i], handWidth);
	      layouts[i].setRotationAngle(90 * i);
	      // layouts[i].setStepDelay(10);
	      players[i].getHand().setView(this, layouts[i]);
	      players[i].getHand().setTargetArea(new TargetArea(trickLocation));
	      players[i].getHand().draw();
	    }
//	    for (int i = 1; i < nbPlayers; i++) // This code can be used to visually hide the cards in a hand (make them face down)
//	      hands[i].setVerso(true);			// You do not need to use or change this code.
	    // End graphics
 }

private void playRound() {
	// Select and display trump suit
		final Suit trumps = CardRandomiser.getInstance().randomEnum(Suit.class);
		final Actor trumpsActor = new Actor("sprites/"+trumpImage[trumps.ordinal()]);
	    addActor(trumpsActor, trumpsActorLocation);
	// End trump suit
	Hand trick;
	int winner;
	Card winningCard;
	int nextPlayer = CardRandomiser.getInstance().get().nextInt(nbPlayers); // randomly select player to lead for this round
	initBids(trumps, nextPlayer);
    // initScore();
    for (int i = 0; i < nbPlayers; i++) updateScore(i);
	for (int i = 0; i < nbStartCards; i++) {
		trick = new Hand(deck);
    	selected = null;
    	// if (false) {
		nextPlay = players[nextPlayer];
		setStatus(nextPlay.getLeadStatus());
		selected = nextPlay.pickCard();
        // Lead with selected card
	        trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards()+2)*trickWidth));
			trick.draw();
			selected.setVerso(false);
			// No restrictions on the card being lead
			lead = (Suit) selected.getSuit();
			selected.transfer(trick, true); // transfer to trick (includes graphic effect)
			winner = nextPlayer;
			winningCard = selected;
		// End Lead
		for (int j = 1; j < nbPlayers; j++) {
			if (++nextPlayer >= nbPlayers) nextPlayer = 0;  // From last back to first
			selected = null;
			nextPlay = players[nextPlayer];
			// if (false) {
			setStatus(nextPlay.getFollowStatus());
			selected = nextPlay.pickCard();
	        // Follow with selected card
		        trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards()+2)*trickWidth));
				trick.draw();
				selected.setVerso(false);  // In case it is upside down
				// Check: Following card must follow suit if possible
				if (Referee.getInstance().ruleBroken(selected, nextPlay.getHand())) {
					Referee.getInstance().violationResponse(nextPlayer, selected);
				}
				// End Check
				 selected.transfer(trick, true); // transfer to trick (includes graphic effect)
				 System.out.println("winning: " + winningCard);
				 System.out.println(" played: " + selected);
				 // System.out.println("winning: suit = " + winningCard.getSuit() + ", rank = " + (13 - winningCard.getRankId()));
				 // System.out.println(" played: suit = " +    selected.getSuit() + ", rank = " + (13 -    selected.getRankId()));
				 if ( // beat current winner with higher card
					 (selected.getSuit() == winningCard.getSuit() && rankGreater(selected, winningCard)) ||
					  // trumped when non-trump was winning
					 (selected.getSuit() == trumps && winningCard.getSuit() != trumps)) {
					 System.out.println("NEW WINNER");
					 winner = nextPlayer;
					 winningCard = selected;
				 }
			// End Follow
		}
		lead = null;
		delay(600);
		trick.setView(this, new RowLayout(hideLocation, 0));
		trick.draw();		
		nextPlayer = winner;
		setStatusText("Player " + nextPlayer + " wins trick.");
		tricks[nextPlayer]++;
		updateScore(nextPlayer);
	}
	removeActor(trumpsActor);
}

  public Oh_Heaven(Properties properties)
  {
	super(700, 700, 30);
	this.properties = properties;
	nbStartCards = Integer.parseInt(properties.getProperty("nbStartCards"));
	nbRounds = Integer.parseInt(properties.getProperty("rounds"));
	Referee.getInstance().setEnforceRules(Boolean.parseBoolean(properties.getProperty("enforceRules")));
	Referee.getInstance().setGame(this);

    setTitle("Oh_Heaven (V" + version + ") Constructed for UofM SWEN30006 with JGameGrid (www.aplu.ch)");
    setStatusText("Initializing...");
    initScores();
    initScore();

    for (int i=0; i <nbRounds; i++) {
      initTricks();
      initRound();
      playRound();
      updateScores();
    };
    for (int i=0; i <nbPlayers; i++) updateScore(i);
    int maxScore = 0;
    for (int i = 0; i <nbPlayers; i++) if (scores[i] > maxScore) maxScore = scores[i];
    Set <Integer> winners = new HashSet<Integer>();
    for (int i = 0; i <nbPlayers; i++) if (scores[i] == maxScore) winners.add(i);
    String winText;
    if (winners.size() == 1) {
    	winText = "Game over. Winner is player: " +
    			winners.iterator().next();
    }
    else {
    	winText = "Game Over. Drawn winners are players: " +
    			String.join(", ", winners.stream().map(String::valueOf).collect(Collectors.toSet()));
    }
    addActor(new Actor("sprites/gameover.gif"), textLocation);
    setStatusText(winText);
    refresh();
  }

  public static void main(String[] args)
  {

  	// Load the properties file
  	final Properties properties;
	if (args == null || args.length == 0) {
		properties = PropertiesLoader.loadPropertiesFile(null);
	} else {
		properties = PropertiesLoader.loadPropertiesFile(args[0]);
	}

	// Utilise a seed
	String seedProp = properties.getProperty("seed");
	Long seed = null;
	if (seedProp != null){
		seed = Long.parseLong(seedProp);
	}
	CardRandomiser.getInstance().initCardRandomiser(seed);

    new Oh_Heaven(properties);
  }

	public Card getSelected() {
		return selected;
	}

	public Player[] getPlayers() {
		return players;
	}

	public Player getNextPlay() {
		return nextPlay;
	}

	public Suit getLead() {
		return lead;
	}
}
