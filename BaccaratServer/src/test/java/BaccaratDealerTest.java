import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class BaccaratDealerTest {
	@Test
	void constructorTest() {
		BaccaratDealer temp = new BaccaratDealer();
		int val = temp.deckSize();
		assertEquals(val, 0, "Something wrong with card class "  );
		
	}
	
	@Test
	void generateDeckTest() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		int val = temp.deckSize();
		assertEquals(val, 52, "Something wrong with card class "  );
	}
	
	
	@Test
	void generateDeckTest2() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		
		ArrayList<Integer> deckValue = new ArrayList();
		ArrayList<Integer> ActualDeckValue = new ArrayList();
		
		// adding heart
		for(int i = 1;i <= 13; i++) {
			deckValue.add(i);
		}
		// adding spades
		for(int i = 1;i <= 13; i++) {
			deckValue.add(i);
		}
		
		//adding diamond
		for(int i = 1;i <= 13; i++) {
			deckValue.add(i);
		}
		// adding club
		for(int i = 1;i <= 13; i++) {
			deckValue.add(i);
		}
		
		// getting actual value deck 
	   for(Card i: temp.deck) {
		   ActualDeckValue.add(i.value);
	   }
	   
	   // comparing them as array
	   assertArrayEquals(ActualDeckValue.toArray(), deckValue.toArray(), " Something went wrong");
	}
	
	@Test
	void dealHand() {
		
		//System.out.println("I am here!!!!!!!!!!!!!!!!!!!!!");
		
		ArrayList<Card> values; 
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		
		values = temp.dealHand();
		
		int firstV = 0;
		int secondValue = 0;
		
		int count = 0;
		
		for(Card i : values) {
			if(count == 0) {
			firstV =  i.value;
			}
			
		   if(count == 1) {
			 secondValue = i.value;
		   }
			count++;
		}
		
		assertEquals(firstV, 1, " Something went wrong");
		assertEquals(secondValue, 2, " Something went wrong");
		// another one here
	}
	
	@Test
	void dealHand2() {
		
		ArrayList<Card> values; 
		BaccaratDealer temp = new BaccaratDealer();
		
		temp.generateDeck();
		temp.shuffleDeck();
		values = temp.dealHand();
		int count = 0;
		int firstV = 0;
		int secondValue = 0;
		
		
		
		for(Card i : values) {
			if(count == 0) {
			firstV =  i.value;
			}
			
		   if(count == 1) {
			 secondValue = i.value;
		   }
			count++;
		}
		
		assertNotEquals(firstV, 0, " Something went wrong");  // neeed to edt
		assertNotEquals(secondValue, -1, " Something went wrong");
		
	}
	
	@Test
	void darwOne() {
		
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		
		Card value =  temp.drawOne();
		assertEquals(value.value, 1, "Somethig went wrong");
	}
	
	@Test
	void darwOne2() {
		Card value ;
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		 value =  temp.drawOne();
		
		for(int i = 0; i < 10; i++ ) {
			 value =  temp.drawOne();
		}
		assertEquals(value.value, 11, "Somethig went wrong");
	}
	
	@Test
	void  shuffleTest() {
		
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		
		ArrayList<Integer> deckValue = new ArrayList();
		ArrayList<Integer> ActualDeckValue = new ArrayList();
		
		// adding heart
		for(int i = 1;i <= 13; i++) {
			deckValue.add(i);
		}
		// adding spades
		for(int i = 1;i <= 13; i++) {
			deckValue.add(i);
		}
		
		//adding diamond
		for(int i = 1;i <= 13; i++) {
			deckValue.add(i);
		}
		// adding club
		for(int i = 1;i <= 13; i++) {
			deckValue.add(i);
		}
		
		temp.shuffleDeck();
		// getting actual value deck 
	   for(Card i: temp.deck) {
		   ActualDeckValue.add(i.value);
	   }
	   assertFalse(Arrays.equals(deckValue.toArray(), ActualDeckValue.toArray()));
	}
	
	@Test
	void shuffleTest2() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		//ArrayList<Card> deckV1 = temp.deck;
		ArrayList<Card> deckV1 = new ArrayList<Card>();
		temp.shuffleDeck();
		temp.shuffleDeck();
		temp.shuffleDeck();
		temp.shuffleDeck();
		temp.shuffleDeck();
		temp.shuffleDeck();
		ArrayList<Card> deckV2 = temp.deck;
		//System.out.println(Arrays.toString(deckV1.toArray()));
		//System.out.println(Arrays.toString(deckV2.toArray()));
		assertFalse(Arrays.equals(deckV1.toArray(), deckV2.toArray()));	
	}
	
	@Test
	void  sizeTest() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		assertEquals(temp.deckSize(), 52, "Somethig went wrong");
	}
	@Test
    void sizeTest2() {
    	BaccaratDealer temp = new BaccaratDealer();
    	assertEquals(temp.deckSize(), 0, "Somethig went wrong");
		temp.generateDeck();
		assertEquals(temp.deckSize(), 52, "Somethig went wrong");
		temp.drawOne();
		assertEquals(temp.deckSize(), 51, "Somethig went wrong");
		temp.dealHand();
		assertEquals(temp.deckSize(), 49, "Somethig went wrong");
    }
}
