import java.io.Serializable;

public class BaccaratInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double totalWins;
	String clientBetOn;
	String whoWon;
	int betAmount;
	int playerHandTotal;
	int bankerHandTotal;
	int thirdPlayerCard;
	int thirdBankerCard;
	int player1stValue;
	int player2ndValue;
	int banker1stValue;
	int banker2ndValue;
	
	String result;
	int count;
	
	String player1stSuite;
	String player2ndSuite;
	String banker1stSuite;
	String banker2ndSuite;
	String pThirdCardSuite;
	String bThirdCardSuite;
	boolean thirdCardNeedPlayer;
	boolean thirdCardNeedBanker;
	
	double finalAmount;
//	private Consumer<Serializable> callback;
	
	public BaccaratInfo(){
	    this.totalWins = 0;
	    this.clientBetOn = "";
	    this.whoWon = "";
	    this.betAmount = 0;
	    this.playerHandTotal = 0;
	    this.bankerHandTotal = 0;
	    this.thirdPlayerCard = 0;
	    this.thirdBankerCard = 0;
	    this.result = "";
	    this.count = 0;
	    this.player1stSuite = "";
		this.player2ndSuite = "";
		this.banker1stSuite = "";
		this.banker2ndSuite = "";
		this.finalAmount = 0.0;
		this.pThirdCardSuite = "";
		this.bThirdCardSuite = "";
		
	    
	}
	
	public double getTotalWinS() {
        return this.totalWins ;
    }
	
	
	public void  setthirdCardNeededPlayer(boolean value) {
		thirdCardNeedPlayer = value;
	}
	
	public void  setthirdCardNeededBanker(boolean value) {
		thirdCardNeedBanker = value;
	}
	
	
	public boolean  getthirdCardNeededPlayer() {
		return thirdCardNeedPlayer;
	}
	
	public  boolean  getthirdCardNeededBanker() {
		return thirdCardNeedBanker;
	}
	
	
	
	public String getBetChoice() {
        return this.clientBetOn;
    }
	
    public String getWhoWho() {
    	return this.whoWon;
    }
    
    public int getBetAmount() {
    	return this.betAmount;
    }

    
    public int getPlayerHandTotal() {
    	return this.playerHandTotal;
    }
    
    public int getBankerHandTotal() {
    	return this.bankerHandTotal;
    }
    
    
 //tempporarily
    
    public int get1stPlayerValue() {
    	return this.player1stValue ;
    }
    
    public int get2ndPlayerValue() {
    	return this.player2ndValue;
    	
    }
    
    public int  get1stBankerValue() {
    	 return this.banker1stValue;
    }
    
    public int get2ndBankerValue() {
    	return this.banker2ndValue;
    }
    
    public String get1stPlayerSuite() {
    	return this.player1stSuite ;
    }
    
    public String get2ndPlayerSuite() {
    	return this.player2ndSuite;
    	
    }
    
    public String  get1stBankerSuite() {
    	 return this.banker1stSuite;
    }
    
    public String get2ndBankeSuite() {
    	return this.banker2ndSuite;
    }
    
    
    
    public int getPlayerThirdCardValue() {
    	return this.thirdPlayerCard;
    }
    
    public String getPlayerThirdCardSuite() {
    	return this.pThirdCardSuite;
    }
    
    public int getBankerThirdCardValue() {
    	return this.thirdBankerCard;
    }
    
    public String getBankerThirdCardSuite() {
    	return this.bThirdCardSuite;
    }
    
    public String getGameResult() {
    	return result;
    }
    
    public int getClientCount() {
    	return this.count;
    }
    
    public double getFinalAmount() {
    	return this.finalAmount;
    }
    
	public void setTotalWinS(double totalwin) {
        this.totalWins = totalwin;
    }
	
	public void setBetChoice(String choice) {
        this.clientBetOn = choice;
    }
	
    public void setWhoWin(String winner) {
    	this.whoWon = winner;
    }
    
    public void setBetAmount(int amount) {
    	this.betAmount = amount;
    }

    
    public void setPlayerHandTotal(int total) {
    	this.playerHandTotal = total;
    }
    
    public void setBankerHandTotal(int total) {
    	this.bankerHandTotal = total;
    }
    
    public void setPlayerThirdCard(int thirdCrd, String val) {
    	this.thirdPlayerCard = thirdCrd;
    	this.pThirdCardSuite = val;
    }
    
    public void setBankerThirdCard(int thirdCrd, String val) {
    	this.thirdBankerCard = thirdCrd;
    	this.bThirdCardSuite = val;
    }
    
    public void setGameResult(String value) {
    	this.result = value;
    }
    
    public void setClientCount(int num, String val) {
    	this.count = num;
    }
    
    // tempporialy
    public void set1stPlayerValue(int num, String val) {
    	this.player1stValue = num;
    	this.player1stSuite =val;
    }
    
    public void  set2ndPlayerValue(int num, String val) {
    	this.player2ndValue = num;
    	this.player2ndSuite = val;
    }
    
    public void  set1stBankerValue(int num, String val) {
    	this.banker1stValue = num;
    	this.banker1stSuite = val;
    }
    
    
    public void set2ndBankerValue(int num, String val) {
    	this.banker2ndValue = num;
    	this.banker2ndSuite = val;
    }
    
    public void setFinalAmount(double val) {
    	this.finalAmount = val;
    }
    
    
}
