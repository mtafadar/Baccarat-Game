
import java.util.ArrayList;
import java.util.function.Consumer;

//import Server.ClientThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Server {
	
	/*
	 * Data Members
	 */
	int count = 1;
	ArrayList<ClientThread> clientsArray = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	private Consumer<Serializable> callback2;
	private Consumer<Serializable> callback3;
	int portNumber = 0;
	String betChoice = "";
	double betAmount = 0;
	int bankerTotal = 0;
	int playerTotal = 0;
	String winner = "";
	boolean thirdBankerCard = false;
	boolean thrdCardPlyr =false;
	String results = "";
	boolean socketIsOpen = false;

	String clientHand = "No";

	/*
	 * Constructor
	 * Pass in consumer interface
	 */
	public Server(String portNum, Consumer<Serializable> call, Consumer<Serializable> call2) {
		callback = call;
		callback2 = call2;
		//Consumer<Serializable> call3
		//callback3 = call3;
		
		this.portNumber = Integer.parseInt(portNum);
		// this is where server socket is its first separate thread.
		// so no new clients can join here its just for the application thread else
		// accept will stop the execution
		server = new TheServer();
		server.start();
	}
	
	/*
	 * TheServer Class => thread running server socket
	 */
	public class TheServer extends Thread {
		// This object its own thread
		// Start server means start thread
		public void run() {
			
		//	System.out.println("The port number is: " + portNumber);
			try(ServerSocket mySocket = new ServerSocket(portNumber);) {
				socketIsOpen = true;
				
				//System.out.println("It does not even come here");
				
				
				System.out.println("Baccarat Server is waiting for a client!");
				
				// If the client connects go to this block of code
				// listen for new clients and keep listening for new clients
				while(true) {
					
					// create thread for the client
					// accept returns socket as parameter to client thread object
					ClientThread clientThrd = new ClientThread(mySocket.accept(), count);
					
					
					// send this to app thread
					callback.accept("Client has connected to server: " + "client # " + count);
					
					// add the client to the client array and increment the counter, also start the thread
					clientsArray.add(clientThrd);
					clientThrd.start();
					count++;
					callback2.accept("Number of clients connected: " + clientsArray.size());
					
					
				}
			} catch(Exception e) {
				callback.accept("Server socket did not launch");
				System.out.println("I am here");
			}
		}
		
		
		
		
	}
	
	/*
	 * ClientThread Class
	 */
	class ClientThread extends Thread {
		Socket connection;
		int count;
		ObjectInputStream in;
		ObjectOutputStream out;
		BaccarateGame game = new BaccarateGame();
		double finalAmount = 0;
	    
		// BacaratGame instance
		/*
		 * Constructor
		 * create new client and pass in the socket needed and what number of client is joining
		 */
		ClientThread(Socket socket, int countNum) {
			this.connection = socket;
			this.count = countNum;
		}
		
		/*
		 * method to send data to client
		 */
		
		public void updateClients(BaccaratInfo info, int i) {
			//for(int i = 0; i < clientsArray.size(); i++) {
				ClientThread t = clientsArray.get(i);
				try {
				 t.out.writeObject(info);
				}
				catch(Exception e) {}
			//}
		}
		
		
		/*
		 * When call on start method it goes to run method
		 */
		
		public void run() {
			try {
				in = new ObjectInputStream(connection.getInputStream());
				System.out.println("After stream!");
				
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);
				System.out.println("Streams  open!");
				
			} catch(Exception e) {
				System.out.println("Streams not open!");
			}
			
			
			
			while(true) {
				System.out.println("In while loop!");
				// read value from client
				try {
					//System.out.println("In try in while loop!");
					//String data = inStream.readObject().toString();
					
					System.out.println("before reading");
					
					BaccaratInfo info =  (BaccaratInfo) in.readObject();
					
					System.out.println("after reading");
					
					betChoice = info.getBetChoice();
					System.out.println("Bet choice is " + betChoice);
					
					betAmount = info.getBetAmount();
					System.out.println("Bet amount is " + betAmount);
					
					
					/*Game cards*/
//					BaccaratDealer bankerDeck = new BaccaratDealer();
//					BaccaratDealer playerDeck = new BaccaratDealer();
//				
//					bankerDeck.generateDeck();
//					playerDeck.generateDeck();
					
					
//					/*Shuffle cards*/
//					bankerDeck.shuffleDeck();
//					playerDeck.shuffleDeck();
//					
					game.theDealer.generateDeck();
					game.theDealer.shuffleDeck();
					
					// get two deal hand
					ArrayList<Card> bankerHands = game.theDealer.dealHand();
					ArrayList<Card> playerHands = game.theDealer.dealHand();
					
//					for(Card i : bankerHands ) {
//						System.out.println(i.value);
//						System.out.println(i.suite);
//					}
//					
//					for(Card i : playerHands ) {
//						System.out.println(i.value);
//						System.out.println(i.suite);
//					}
//					
					
					

					// create there total
				    bankerTotal = BaccaratGameLogic.handTotal(bankerHands);
				    playerTotal = BaccaratGameLogic.handTotal(playerHands);
				    
				    
				    // Look for Natural win 8 or 9
				    
				    
				   // Check for the third card
				   // Card card = bankerDeck.drawOne();
				    BaccaratGameLogic logic = new BaccaratGameLogic();
				    BaccaratDealer cardV = new BaccaratDealer();
				    
				   
				    Card playerThrdCrd = null;
				    Card bankerThrdCrd = null;
				    
				    // pass this third card to bankers third card for evaluation
				    thrdCardPlyr = logic.evaluatePlayerDraw(playerHands);
				    
				    info.setthirdCardNeededPlayer(thrdCardPlyr);
				    
				    
				    	
				    if (thrdCardPlyr == true) {
				    	
			    	
				    	// get the third card from deck
				    	playerThrdCrd = game.theDealer.drawOne();
				    	

				    	// add it to the total of player
				    	playerHands.add(playerThrdCrd);
				    	
				    	// adding the third card
				    	info.setPlayerThirdCard(playerThrdCrd.value, playerThrdCrd.suite);	
				    	
				    	//System.out.println("Player third values "+ playerThrdCrd.value+ " " + playerThrdCrd.suite);
				    	
				    	
				    	// total the three cards for player
				    	playerTotal = BaccaratGameLogic.handTotal(playerHands);
				    	
				    	// Now evaluate bankers third card
				    }
				    
				    //System.out.println(" I am here in line 260");
				    thirdBankerCard = logic.evaluateBankerDraw(bankerHands, playerThrdCrd);
			    	info.setthirdCardNeededBanker(thrdCardPlyr);
				    
				    
				 // check if we need third card for banker
			    	if (thirdBankerCard == true) {
			    		//System.out.println(" I am here in ThirdbankerCard");
			    		// get the third card for banker
			    		bankerThrdCrd = game.theDealer.drawOne();
			    		// add it to the total of banker
			    		bankerHands.add(bankerThrdCrd);
			    		info.setBankerThirdCard(bankerThrdCrd.value , bankerThrdCrd.suite);
			    		System.out.println("Banker third values "+bankerThrdCrd.value + " " + bankerThrdCrd.suite);
			    		// total the three cards for banker
			    		bankerTotal = BaccaratGameLogic.handTotal(bankerHands);
			    	}
				    
				  
			    
				 
				    
				    // Check for the winner:
				    winner = BaccaratGameLogic.whoWon(bankerHands, playerHands);
				    
				    
				    int Banker1stvalue =  bankerHands.get(0).value;
					int Banker2ndvalue = bankerHands.get(1).value;
					
					
					
					// add suite to cards
					String Banker1stSuite = bankerHands.get(0).suite;
					String Banker2ndSuite = bankerHands.get(1).suite;
					
					
//					System.out.println("Banker Values : " + Banker1stvalue + " " +  Banker2ndvalue + " "+ Banker1stSuite +" " +  Banker2ndSuite );
//					System.out.println("Banker third values "+bankerThrdCrd.value + " " + bankerThrdCrd.suite);
//					System.out.println("Player third values "+ playerThrdCrd.value+ " " + playerThrdCrd.suite);
					
					int Player1stValue = playerHands.get(0).value;
					int Player2ndValue = playerHands.get(1).value;
				    
				   
					
					String Player1stSuite = playerHands.get(0).suite;
					String Player2ndSuite = playerHands.get(1).suite;
					
					
				//	System.out.println("Banker Values : " + Player1stValue + " " +  Player2ndValue  + " "+ Player1stSuite +" " + Player2ndSuite  );
					
				    // set dealHand values
				    
				    info.set1stBankerValue(Banker1stvalue, Banker1stSuite);
				    info.set2ndBankerValue(Banker2ndvalue, Banker2ndSuite);
				    info.set1stPlayerValue(Player1stValue, Player1stSuite);
				    info.set2ndPlayerValue(Player2ndValue, Player2ndSuite);
				    
				    
				    
				 
				    
				 
			    	
				    info.setWhoWin(winner);
				    info.setPlayerHandTotal(playerTotal);
				    info.setBankerHandTotal(bankerTotal);
				    
				    // check for the final winner
				    if (betChoice.equals(winner)) {
//				    	System.out.println("im in true part");
				    	results = "You Won!";
				    	info.setGameResult("You Won!");
				    } else {
//				    	System.out.println("im in false part winner is" + info.getWhoWho());
//				    	System.out.println("im in false part betChoice is" + info.getBetChoice());
//				    	System.out.println("im in false part");
				    	results = "You Lost!";
				    	info.setGameResult("You Lost!");
				    }
				    
				    
				    
				   
				    System.out.println("The winner is : " + winner);
				    
				    // get the amount of winning
				    
				    game.bankerHand = bankerHands;
				    game.playerHand = playerHands;
				    //game.theDealer = 
				    game.bettingOn = betChoice;
				    game.currentBet = betAmount;
				    game.totalWinnings = info.getTotalWinS(); 
				    System.out.println("The final win amount before is : " + finalAmount);
				    double val = game.evaluateWinnings();
				    
				    System.out.println("Val is : " + val);
				    //finalAmount = val + info.getFinalAmount();
				    finalAmount = finalAmount + val;
				    
				    info.setFinalAmount(finalAmount);
				    System.out.println(finalAmount);
				    
				    System.out.println("The final win amount is : " + finalAmount);
				    info.setTotalWinS(finalAmount);
				    
					
					callback.accept("Client# " + count + " chose " + betChoice + " and the bet amount is " + betAmount);
					
//					callback.accept("client " + count +  "Playing another Hand: "+ clientHand);
					callback.accept("Clients# " + count + " Player Card 1 : " + Player1stValue + Player1stSuite);
					callback.accept("Clients# " + count + " Player Card 2 : " + Player2ndValue + Player2ndSuite);
					
					callback.accept("Clients# " + count + " Banker Card 1 : " + Banker1stvalue + Banker1stSuite);
					callback.accept("Clients# " + count + " Banker Card 2 : " +Banker2ndvalue  + Banker2ndSuite);
					
					
					if (thrdCardPlyr == true) {
						callback.accept("Clients# " + count + " Player Third Card : " + playerThrdCrd.value+ playerThrdCrd.suite);
					}
					
					if (thirdBankerCard == true) {
						callback.accept("Clients# " + count +  " Banker Third Card : " + bankerThrdCrd.value + bankerThrdCrd.suite);
					}
					 updateClients(info, count-1);
					callback.accept("Client# " + count + " game result: " + results);
					callback.accept("Client# " + count + " Total winning : " +  finalAmount);
				     
					
				} catch (Exception e) {
					// print to server log
					callback.accept("Client# " + count + " left the game!");
					clientsArray.remove(this);
					count = count - 1;
					e.printStackTrace();
					// remove the client and break brings to end of run method
					// means when run method is over the thread is terminated.
					break;
				}
			}
		}
		
	}

}

