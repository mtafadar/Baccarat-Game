import java.util.ArrayList;

public class BaccaratGameLogic {
	
	/*
	 * This method will evaluate two hands at the end of the game and return a string depending on the winner
	 * there are  3 string that should be return from this function: "Player", "Banker", "Draw"
	 */
  public  static  String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
	  int bankerVal = handTotal(hand1);
      int playerVal = handTotal(hand2);

      
      if((playerVal == 9 ) && (playerVal != bankerVal)){
          return "Player";
      } 
      
      else if((bankerVal == 9) && (playerVal != bankerVal)){
          return "Banker";
      } 
      
      else if ((playerVal== 8) && (playerVal != bankerVal)) {
    	  return "Player";
      }
      
      else if ((bankerVal== 8) && (playerVal != bankerVal)) {
    	  return "Banker";
      }
      
      else if(playerVal == bankerVal){
          return "Tie";
      }
      
      else if(bankerVal > playerVal) {
          return "Banker";
      } 
      else {
          return "Player"; 
      }
  }

  
 /*
  * This method  take a hand and return how many points  that hand is worth
  *  for values 1 to 9 is  counted as the number of values
  *  for number 10 to 13 is not counted since value for  queen, king and jake is  0 
  */
public  static int handTotal(ArrayList<Card> hand) {
	
	 int totalHand = 0;
	 for(Card i : hand) {
		 if(i.value == 1) {
			totalHand += i.value;
		 }
		 else if (i.value ==  2) {
			 totalHand += i.value;
		 }
		 else if (i.value == 3) {
			 totalHand += i.value;
		 }
		 else if(i.value == 4) {
			 totalHand += i.value;
		 }
		 else if (i.value == 5) {
			 totalHand += i.value;
		 }
		 else if (i.value == 6) {
			 totalHand += i.value;
		 }
		 else if(i.value == 7) {
			 totalHand += i.value;
		 }
		 else if(i.value == 8) {
			 totalHand += i.value;
		 }
		 else if(i.value == 9) {
			 totalHand += i.value;
		 }
		 else { 
			 totalHand += 0; //we are not add anything  for   Ace, king, queen, jack
		 }
	 }
	 
	 if (totalHand >= 10) {
		 totalHand = totalHand % 10;
	 }
     return totalHand;
}


/*
 *  This method will return true if banker should be dealt a third card. 
 *   If total hand value for banker is less that 2 automatically  banker have to draw another card
 *    else it depends on player
 */
public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){
    int totalVal = handTotal(hand);

    if(  totalVal <= 2){ 
        return true;
    } else if((totalVal == 3) || (totalVal == 4) || (totalVal == 5) || (totalVal == 6)){ 
        if(playerCard == null){
            if(totalVal <= 5)
                return true;
            else{ 
            	 return false;
            	}
        }
        if(playerCard.value == 0 || playerCard.value == 1) {
        	if(totalVal <= 3) {
        		return true;
        	}
        	else {
        		return false;
        	}
        }
        else if(playerCard.value == 2 || playerCard.value == 3) {
        	  if(totalVal <= 4) {
        		return true;
        	  }
        	  else {
        		return false;
        	}
        }
        
        else if(playerCard.value == 4 || playerCard.value == 5) {
      	  if(totalVal <= 5) {
      		return true;
      	  }
      	  else {
      		return false;
      	   }
        }
       else if(playerCard.value == 6 || playerCard.value == 7) {
       	  if(totalVal <= 6) {
       		return true;
       	  }
       	  else {
       		return false;
       	  }
        }
        
       else if(playerCard.value == 8 ) {
       	  if(totalVal <= 3) {
       		return false;
       	 }
       	  
      else if(playerCard.value == 9) {
       	   if(totalVal <= 3) {
       			  return true;
       		  }
       	  }

       	  else {
       		return false;
       	}
       }
    }
    else {
    	return false;
    } 
    
    return false;
}

 
  /*
   * This method will return true if player  should be dealt  a third card. 
   * After card has been drawn if the  total card value is <= 5 the player automatically 
   * have to draw another card.     
   */
  public boolean evaluatePlayerDraw(ArrayList<Card> hand){
         int  totalVal = handTotal(hand);

         if(totalVal <= 5) {
             return true;
         }
         return false;
     }
}
