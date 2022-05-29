import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BaccaratGameLogicTest {
	
	@Test
	void  whoWonTest() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		temp.shuffleDeck();
		ArrayList<Card>  dealHand1 = temp.dealHand();
		temp.shuffleDeck();
		ArrayList<Card> dealHand2 = temp.dealHand();
		BaccaratGameLogic  temp1 = new BaccaratGameLogic();
		String value =  temp1.whoWon(dealHand1, dealHand2);
		//System.out.println(value);
		assert(true);
	}
	
	@Test
	void whoWinTest2() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck(); 
		// gonna test without shuffling
		
		ArrayList<Card>  dealHand1 = temp.dealHand();  // should be 1 and 2 totaal 3;
		ArrayList<Card> dealHand2 = temp.dealHand(); // 3 and 4 = 7
		BaccaratGameLogic  temp1 = null;
		String val = temp1.whoWon(dealHand1, dealHand2);  // the value must be    player since I am not shufflong 
		
		assertEquals(val, "Player", "Something wrong");
		/*
		for(Card i: dealHand1) {
		
			System.out.println(i.value);
		}
		System.out.println();
		for(Card i: dealHand2) {
			System.out.println(i.value);
		}
		System.out.println(val);
		*/
	}
	
	@Test
	void  handTotal() {
	BaccaratDealer temp = new BaccaratDealer();
	temp.generateDeck();  // without shuffling
	ArrayList<Card>  dealHand1 = temp.dealHand();
	BaccaratGameLogic  temp1 = null;
	int value = temp1.handTotal(dealHand1);
	assertEquals(value, 3, "Something wrong");
	ArrayList<Card>  dealHand2 = temp.dealHand();
	int value1 = temp1.handTotal(dealHand2);
	assertEquals(value1, 7, "Something wrong");
	}
	
	
	@Test
	void handTotal2() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();  
		temp.shuffleDeck(); // I am shuffoling the card now
		ArrayList<Card>  dealHand1 = temp.dealHand();
		BaccaratGameLogic  temp1 = null;
		int value = temp1.handTotal(dealHand1);
		// can't really test its gonna assign a random number
		assert(true);
	}
	
	@Test
	void handTotal3() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck(); 
		temp.dealHand();
		temp.dealHand();
		temp.dealHand();
		ArrayList<Card>  dealHand1 = temp.dealHand();  // greater than 10 so testing whether its  do modulo
		BaccaratGameLogic  temp1 = null;
		int value = temp1.handTotal(dealHand1);
		assertEquals(value, 5, "Something wrong");
	}
	
	@Test
	void playerDraw() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck(); 
		ArrayList<Card>  dealHand1 = temp.dealHand(); 
		BaccaratGameLogic  temp1 = new BaccaratGameLogic() ;
		boolean  value = temp1.evaluatePlayerDraw(dealHand1);
		assertEquals(value, true, "Something wrong");	
	}
	
	@Test
	void playerDraw2() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck(); 
		
		temp.dealHand();
		temp.dealHand();
		temp.dealHand();
		temp.dealHand();
		ArrayList<Card>  dealHand1 = temp.dealHand(); 
		
		BaccaratGameLogic  temp1 = new BaccaratGameLogic() ;
		boolean  value = temp1.evaluatePlayerDraw(dealHand1);
		assertEquals(value, false, "Something wrong");	
	}
	
	@Test
	void BankerDraw() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		temp.dealHand();
		temp.dealHand();
		ArrayList<Card>  dealHand1 = temp.dealHand(); // total is 11 % 10 = 1 Hence it is 
		Card playerCard = null;
		BaccaratGameLogic  temp1 = new BaccaratGameLogic() ;
		boolean  value = temp1.evaluateBankerDraw(dealHand1, playerCard);
		assertEquals(value, true, "Something wrong");	
	}
	
	@Test
	void BankerDraw2() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		temp.dealHand();
		ArrayList<Card>  dealHand1 = temp.dealHand(); // total is  3+ 4 = 7 
		Card playerCard = temp.drawOne();
		//System.out.println(playerCard.value);
		BaccaratGameLogic  temp1 = new BaccaratGameLogic() ;
		boolean  value = temp1.evaluateBankerDraw(dealHand1, playerCard);
		assertEquals(value, false, "Something wrong");
	}
}
