package ca.sheridancollege.project;
import java.util.ArrayList;

/**
 * @author Davinder Singh
 *  * A class that models the War card game.
 */
public class WarGame extends Game {

    private final GroupOfCards deck;
    private final Player player1;
    private final Player player2;
    private final int roundLimit;
    private int currentRound;

    public WarGame(String player1Name, String player2Name, int roundLimit) {
        super("War");
        deck = new GroupOfCards();
        initializeDeck();
        deck.shuffle();
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        dealCards();
        this.roundLimit = roundLimit;
        currentRound = 0;
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.addCard(new Card(suit, rank));
            }
        }
    }

    private void dealCards() {
        ArrayList<Card> deckCards = deck.getCards();
        for (int i = 0; i < deckCards.size(); i++) {
            if (i % 2 == 0) {
                player1.getHand().addCard(deckCards.get(i));
            } else {
                player2.getHand().addCard(deckCards.get(i));
            }
        }
    }

    @Override
    public void play() {
        while (!player1.getHand().isEmpty() && !player2.getHand().isEmpty() && currentRound < roundLimit) {
            currentRound++;
            Card card1 = player1.playCard();
            Card card2 = player2.playCard();
            System.out.println(player1.getName() + " plays: " + card1);
            System.out.println(player2.getName() + " plays: " + card2);
            if (card1.getRank().compareTo(card2.getRank()) > 0) {
                System.out.println(player1.getName() + " wins the round!");
                player1.getHand().addCard(card1);
                player1.getHand().addCard(card2);
            } else if (card1.getRank().compareTo(card2.getRank()) < 0) {
                System.out.println(player2.getName() + " wins the round!");
                player2.getHand().addCard(card1);
                player2.getHand().addCard(card2);
            } else {
                System.out.println("It's a tie! Going to war...");
                // Implement war logic here
            }
            System.out.println(player1.getName() + " has " + player1.getHand().getSize() + " cards.");
            System.out.println(player2.getName() + " has " + player2.getHand().getSize() + " cards.");
            System.out.println();
        }
    }

    @Override
    public void declareWinner() {
        if (player1.getHand().isEmpty() || currentRound >= roundLimit) {
            System.out.println(player2.getName() + " wins the game!");
        } else {
            System.out.println(player1.getName() + " wins the game!");
        }
    }
}
