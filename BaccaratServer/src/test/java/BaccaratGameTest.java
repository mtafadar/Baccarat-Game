import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BaccaratGameTest {
	
	
	@Test
	void   evaluateWinnings() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		ArrayList<Card>  dealHand1 = temp.dealHand();  // should be 1 and 2 totaal 3;
		ArrayList<Card> dealHand2 = temp.dealHand(); // 3 and 4 = 7
		BaccarateGame temp2 = new BaccarateGame();
		temp2.playerHand = dealHand1;
		temp2.bankerHand = dealHand2;
		temp2.bettingOn = "Banker";
		temp2.currentBet = 500;
		double value = temp2.evaluateWinnings();
		System.out.println(value);
		assertEquals(value, 1000, "Something went wrong");
	}
	
	@Test
	void   evaluateWinnings2() {
		BaccaratDealer temp = new BaccaratDealer();
		temp.generateDeck();
		ArrayList<Card>  dealHand1 = temp.dealHand();  // should be 1 and 2 totaal 3;
		ArrayList<Card> dealHand2 = temp.dealHand(); // 3 and 4 = 7
		BaccarateGame temp2 = new BaccarateGame();
		temp2.playerHand = dealHand1;
		temp2.bankerHand = dealHand2;
		temp2.bettingOn = "Banker";
		temp2.currentBet = 700;
		double value = temp2.evaluateWinnings();
		System.out.println(value);
		assertEquals(value, 1400, "Something went wrong");
	}
	
	
}
