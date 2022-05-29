import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyTest {

	@Test
	void cardTest1() {
	  Card card = new Card("Diamond", 12);
	  assertEquals(card.value, 12, "Something wrong with card class "  );
	 }
	
	@Test
	void   cardTest2() {
		Card card = new Card("Diamond", 12);
		 assertEquals(card.suite, "Diamond", "Something wrong with card class "  );
	}
	@Test
	void cardTest3() {
		Card card = new Card("Heart", 1);
		Card  temp = new Card("Spades", 1);
		assertEquals(card.suite, "Heart", "Something wrong with card class "  );
		assertEquals(temp.suite, "Spades", "Something wrong with card class "  );
	}
	
	@Test
	void cardTest4() {
		Card card = new Card("Heart", 100);
		Card  temp = new Card("Spades", 500);
		assertEquals(card.value, 100, "Something wrong with card class "  );
		assertEquals(temp.value, 500, "Something wrong with card class "  );
	}
	
	
}