import java.util.ArrayList;
import java.util.*;

public class BaccaratDealer {
	
	ArrayList<Card> deck;
	
	
	/*
	 * constructor
	 */
	 public BaccaratDealer(){
		 deck   =  new ArrayList<>();
	 }
	
	/*
	 * generateDeck will generate a new standard 52 card deck where each card is an
       instance of the Card class in the ArrayList<Card> deck 
	 */
	public void generateDeck() {
		deck   =  new ArrayList<Card>();
		
		/*
		 * In card game we have 4 suite (Heart,  Diamond, Spade, club)  total of 4*13 = 52 deck
		 */
		
	
		/*
		 * generating  13 deck. suite: heart
		 */
        for(int i = 1; i <= 13; i++){
            Card card = new Card("H", i);
            deck.add(card);
        }

        /*
		 * generating  13 deck suite: Diamond
		 */
        for(int i = 1; i <= 13; i++){
            Card card = new Card("D", i);
            deck.add(card);
        }

        /*
		 * generating  13 deck suite: Diamond
		 */
        
        for(int i = 1; i <= 13; i++){
            Card card = new Card("S", i);
            deck.add(card);
        }

        /*
		 * generating  13 deck suite: club
		 */
        for(int i = 1; i <= 13; i++){
            Card card = new Card("C", i);
            deck.add(card);
        }
        
        
        
	}
	
	
	/*
	 * dealhand will deal two cards and return them in an arrayList<Card>
	 */
	public ArrayList<Card> dealHand(){
		 ArrayList<Card> handDeal = new ArrayList<>();
		 
		    // getting first two card
		 
	        Card temp = deck.get(0); 
	        Card temp2 = deck.get(1);
	        
	        // adding them to the array
	        handDeal.add(temp);
	        handDeal.add(temp2);
	        
	        //  removing  from deck since we already use it
	        deck.remove(0); 
	        deck.remove(0);
	        return handDeal;
	}
	
	
	/*
	 * drawOne will deal a single card and return it 
	 */
	public Card drawOne() {
		Card card = deck.get(0); // get top card
        deck.remove(0); // delete from deck
        return card;
	}
	
	/*
	 * shuffleDeck will create a new deck of 52 cards and "shuffle"  (randomize the cards in that ArrayList<card> 
	 */
	public void shuffleDeck() {
		Collections.shuffle(deck);
		}
	
	
	/*
	 * deckSize will return the size of cards in the deck
	 */
	public int deckSize() {
		return deck.size();
	}
	
	

}
