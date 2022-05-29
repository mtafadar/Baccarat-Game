import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;



public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	int portNum;
	String ipAddress;
	String result;
	boolean anothergame = false;
	String betOn;
	String winner;

	private Consumer<Serializable> callback;
	private Consumer<Serializable> callback2;
	private Consumer<Serializable> callback3;
	
	Client(String portNm, String ipAdrs, Consumer<Serializable> call, Consumer<Serializable> call2){
		
//		, Consumer<Serializable> call2,
//		 Consumer<Serializable> call3
		
		
		this.portNum = Integer.parseInt(portNm);
		ipAddress = ipAdrs;
		callback = call;
		callback2 = call2;
//		callback3 = call3;
	
		
	}
	
	public void run() {
		
		try {
		socketClient= new Socket(ipAddress, portNum);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		
		while(true) {
			try {
			// take mesg for  server and pass this back to app thread and update GUI
			BaccaratInfo info =  new BaccaratInfo();
			info = (BaccaratInfo) in.readObject(); // its platform.runlater
			result = info.getGameResult();
			//betOn =  info.getBetChoice();
			
			int bankValue1 = info.get1stBankerValue();
			int bankValue2 = info.get2ndBankerValue();
			int playerValue = info.get1stPlayerValue();
			int playerValue2 = info.get2ndPlayerValue();
			
	        boolean thirdValuePlayer = info.getthirdCardNeededPlayer();
	        boolean  thirdValueBanker  = info.getthirdCardNeededBanker();
	        int thirCardPlayer = 0;
	        int thirdCardBanker = 0;
	        String thirdCardSuiteP = "";
	        String thirdCardSuiteB = "";
	        double totalWin = info.getFinalAmount();
	        

			callback2.accept(playerValue);
			callback.accept( "Player Card 1 : " +  playerValue + info.get1stPlayerSuite());
			callback.accept( "Player Card 2 : " +  playerValue2 + info.get2ndPlayerSuite()) ;
			callback.accept("Banker Card 1 :" + bankValue1 + info.get1stBankerSuite());
			callback.accept("Banker Card 2 : " + bankValue2 + info.get2ndBankeSuite());
			
	        
	        if (thirdValuePlayer == true) {
	        	thirCardPlayer = info.getPlayerThirdCardValue();
	        	thirdCardSuiteP = info.getPlayerThirdCardSuite();
	        	callback.accept( "Player Card 3 : " +  thirCardPlayer + thirdCardSuiteP );
	        	
	        }
	        
	        if(thirdValueBanker  == true) {
	        	thirdCardBanker = info.getBankerThirdCardValue();
	        	thirdCardSuiteB = info.getBankerThirdCardSuite();
	        	callback.accept( "Banker Card 3 : " +  thirdCardBanker + thirdCardSuiteB );
	        }
	        	
			callback.accept("Game Results: " + result);
			callback.accept("Total Winning: " + totalWin);
			
			}
			catch(Exception e) {}
		}
    }
	

	public void sendChoice(String betChoic, String betAm) {
		System.out.println("In send method");
		
		
		try {
			int val = Integer.parseInt(betAm);
			//System.out.println("send 1 " + betChoic);
			//System.out.println("send2 "+ val);
			// pass info to baccarrat class
			BaccaratInfo info = new BaccaratInfo();
			info.setBetChoice(betChoic);
			info.setBetAmount(val);
			System.out.println("Client chose " + info.getBetChoice());
			System.out.println("Client Bet amount"+ info.getBetAmount());
			out.writeObject(info);
			
		} catch (IOException e) {
			System.out.println("In send method catch block");
			// TODO Auto-generated catch bloc]
			e.printStackTrace();
		}
		
	}

}
