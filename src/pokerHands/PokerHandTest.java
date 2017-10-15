package pokerHands;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PokerHandTest {
	PokerHand testHand= new PokerHand("2C 6C 4H 3D 5S");
	
	@Test
	public void getCardsTest() {
		//Arrange
		ArrayList<String> expectedVal = new ArrayList<String>();
		expectedVal.add("2C");
		expectedVal.add("6C");
		expectedVal.add("4H");
		expectedVal.add("3D");
		expectedVal.add("5S");
		
		//Act
		ArrayList<String> result = testHand.getCards();
		
		//Assert
		assertEquals(expectedVal, result);
	}
	
	@Test
	public void getScoreTest(){
		//Arrange
		
		//Act
		int result = testHand.getScore();

		//Assert
		assertEquals(5, result);
	}
	
	@Test
	public void getSortedCardNumbersTest(){
		//Arrange
		ArrayList<Integer> expectedVal = new ArrayList<Integer>();
		expectedVal.add(2);
		expectedVal.add(3);
		expectedVal.add(4);
		expectedVal.add(5);
		expectedVal.add(6);
		
		//Act
		ArrayList<Integer> result = testHand.getSortedCardNumbers();
		
		//Assert
		assertEquals(expectedVal, result);
		}
	
	@Test
	public void getHandSizeTest() {
		//Arrange
		
		//Act
		int result = PokerHand.getHandSize();
		
		//Assert
		assertEquals(5, result);
		}
	
	@Test
	public void getAceThroughFiveTest() {
		//Arrange
		ArrayList<Integer> expectedVal = new ArrayList<Integer>();
		expectedVal.add(2);
		expectedVal.add(3);
		expectedVal.add(4);
		expectedVal.add(5);
		expectedVal.add(14);
		
		//Act
		ArrayList<Integer> result = PokerHand.getAceThroughFive();
		
		//Assert
		assertEquals(expectedVal, result);
		}
	
	@Test
	public void getCardOccurencesHashTest() {
		//Arrange
		HashMap<Integer,Integer> expectedVal = new HashMap<Integer,Integer>();
		expectedVal.put(2, 1);
		expectedVal.put(3, 1);
		expectedVal.put(4, 1);
		expectedVal.put(5, 1);
		expectedVal.put(6, 1);
		
		//Act
		HashMap<Integer,Integer> result = testHand.getCardOccurenceHash();
		
		//Assert
		assertEquals(expectedVal, result);
	}
	
	@Test
	public void getKeyCardsTest() {
		//Arrange
		String expectedVal="6 high";
		
		//Act
		String result = testHand.getKeyCards();
		
		//Assert
		assertEquals(expectedVal, result);
	}
}
