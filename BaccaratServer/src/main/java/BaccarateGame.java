import java.util.ArrayList;

public class BaccarateGame {
	
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	double currentBet;
	double totalWinnings;
	String winner;
	String bettingOn;
	
	BaccarateGame(){
		System.out.println("I am in game class");
//		playerHand = new ArrayList<Card>();
//		bankerHand = new ArrayList<Card>();
	theDealer = new BaccaratDealer();
		
		
	}
	
	
	public double evaluateWinnings() {
		System.out.println("I am in evaluateWin");
		
		winner =  BaccaratGameLogic.whoWon(bankerHand,playerHand);  // this win show won won
		System.out.println("The winner is in Game class: "+ winner);
		if(bettingOn.equals("Banker") &&  ( winner.equals("Banker"))) {
			totalWinnings = currentBet  + currentBet;
			//System.out.print("The Banker is the winner");
		}
		else if (bettingOn.equals("Player") && (winner.equals("Player"))) {
			totalWinnings = currentBet + currentBet;
			//System.out.print("The Player is the winner");
		}
		else if (bettingOn.equals("Tie") && (winner.equals("Tie"))) {
			totalWinnings += currentBet * 0.8;
			//System.out.print("The Draw is the winner");
		}
		else {
			System.out.print("You lost the  bet");
			
			
				totalWinnings -= currentBet;
		
		}
		System.out.println(bettingOn);
		return totalWinnings;
	}
	
}
