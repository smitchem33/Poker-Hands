/**
 * 
 */
package pokerHands;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author Sean
 *
 */
public class PokerHandComparerTest {

	@Test
	public void testCompareHandsStraightFlushVsFourKind() {
		//Arrange	
		PokerHand black = new PokerHand("2C 3C 4C 5C 6C");
		PokerHand white = new PokerHand("2C 2D 2H 2S 3D");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsStraightFlushTie() {
		//Arrange	
		PokerHand black = new PokerHand("2C 3C 4C 5C 6C");
		PokerHand white = new PokerHand("2C 3C 4C 5C 6C");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Tie", result);
	}
	
	@Test
	public void testCompareHandsStraightFlushBeatsStraightFlush() {
		//Arrange	
		PokerHand black = new PokerHand("5D 4D 6D 8D 7D");
		PokerHand white = new PokerHand("2C 3C 4C 5C 6C");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}

	@Test
	public void testCompareHandsStraightFlushAceThroughFive() {
		//Arrange	
		PokerHand black = new PokerHand("5D 4D 6D 8D 7D");
		PokerHand white = new PokerHand("AC 2C 3C 4C 5C");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsFourKindVsFullHouse() {
		//Arrange	
		PokerHand black = new PokerHand("2C 2D 2H 2S 3D");
		PokerHand white = new PokerHand("2C 2D 2H 3C 3D");
		
		//Act
		String result = PokerHandComparer.compareHands(black,white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsFourKindTie() {
		//Arrange	
		PokerHand black = new PokerHand("2C 2D 2H 2S 3S");
		PokerHand white = new PokerHand("2C 2D 2H 2S 3D");
		
		//Act
		String result = PokerHandComparer.compareHands(black,white);
		
		//Assert
		assertEquals("Tie", result);
	}
	
	@Test
	public void testCompareHandsFourKindBeatsFourKind() {
		//Arrange	
		PokerHand black = new PokerHand("4C 4D 4H 4S 3S");
		PokerHand white = new PokerHand("2C 2D 2H 2S 3D");
		
		//Act
		String result = PokerHandComparer.compareHands(black,white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsFullHouseVsFlush() {
		//Arrange	
		PokerHand black = new PokerHand("2C 2D 2H 3C 3D");
		PokerHand white = new PokerHand("2C 4C 6C 8C TC");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsFullHouseTie() {
		//Arrange	
		PokerHand black = new PokerHand("2C 2D 2H 3C 3D");
		PokerHand white = new PokerHand("2C 2D 2H 3C 3D");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Tie", result);
	}
	
	@Test
	public void testCompareHandsFullHouseBeatsFullHouse() {
		//Arrange	
		PokerHand black = new PokerHand("5C 5D 5H 3C 3D");
		PokerHand white = new PokerHand("2C 2D 2H 7C 7D");
			
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsFlushVsStraight() {
		//Arrange	
		PokerHand black = new PokerHand("2C 4C 6C 8C TC");
		PokerHand white = new PokerHand("2C 3D 4H 5S 6C");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsFlushTie() {
		//Arrange	
		PokerHand black = new PokerHand("2C 4C 6C 8C TC");
		PokerHand white = new PokerHand("2C 4C 6C 8C TC");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Tie", result);
	}
	
	@Test
	public void testCompareHandsFlushBeatsFlush() {
		//Arrange	
		PokerHand black = new PokerHand("2C 4C 6C 8C TC");
		PokerHand white = new PokerHand("3C 3C 5C 7C 9C");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsStraightVsThreeKind() {
		//Arrange	
		PokerHand black = new PokerHand("2C 3D 4H 5S 6C");
		PokerHand white = new PokerHand("2C 2D 2H 8C TS");
			
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsStraightTie() {
		//Arrange	
		PokerHand black = new PokerHand("2C 3D 4H 5S 6C");
		PokerHand white = new PokerHand("2C 3D 4H 5S 6C");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Tie", result);
	}
	
	@Test
	public void testCompareHandsStraightBeatsStraight() {
		//Arrange	
		PokerHand black = new PokerHand("3C 4D 5H 6S 7C");
		PokerHand white = new PokerHand("2D 3C 4D 5C 6D");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsStraightAceThroughFive() {
		//Arrange	
		PokerHand black = new PokerHand("2C 3D 4H 5S 6C");
		PokerHand white = new PokerHand("AC 2D 3H 4S 5C");
			
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsThreeKindVsTwoPair() {
		//Arrange	
		PokerHand black = new PokerHand("2C 2D 2H 9C 7D");
		PokerHand white = new PokerHand("4C 4D 5C 5D TC");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsThreeKindTie() {
		//Arrange	
		PokerHand black = new PokerHand("2C 2D 2H 9C 7D");
		PokerHand white = new PokerHand("2C 2D 2H 9C 7D");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Tie", result);
	}
	
	public void testCompareHandsThreeKindBeatsThreeKind() {
		//Arrange	
		PokerHand black = new PokerHand("3C 3D 3H 9C 7D");
		PokerHand white = new PokerHand("2C 2D 2H 9C 7D");
				
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}

	@Test
	public void testCompareHandsTwoPairVsPair() {
		//Arrange	
		PokerHand black = new PokerHand("4C 4D 5C 5D TC");
		PokerHand white = new PokerHand("JC 2D 6C 8C 2C");
		
				
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsTwoPairTie() {
		//Arrange	
		PokerHand black = new PokerHand("4C 4D 3C 3D TC");
		PokerHand white = new PokerHand("4S 4H 3S 3H TH");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Tie", result);
	}
	
	@Test
	public void testCompareHandsTwoPairBeatsTwoPair() {
		//Arrange	
		PokerHand black = new PokerHand("AC 3D 3C AD TC");
		PokerHand white = new PokerHand("4S 4H 3S 3H TH");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsPairVsHighCard() {
		//Arrange	
		PokerHand black = new PokerHand("2C 3D 2H 5C 7D");
		PokerHand white = new PokerHand("2S TC 6S 8S AC");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsPairTie() {
		//Arrange	
		PokerHand black = new PokerHand("2C 3D 2H 5C 7D");
		PokerHand white = new PokerHand("2C 3D 2H 5C 7D");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Tie", result);
	}
	
	@Test
	public void testCompareHandsPairBeatsPair() {
		//Arrange	
		PokerHand black = new PokerHand("3C 4D 3H 5C 7D");
		PokerHand white = new PokerHand("2C 3D 2H 5S 7S");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}	
	
	@Test
	public void testCompareHandsHighCardBeatsHighCard() {
		//Arrange	
		PokerHand black = new PokerHand("5C 4D 6H AC 7D");
		PokerHand white = new PokerHand("5D 3H 2H 4S JS");
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Black", result);
	}
	
	@Test
	public void testCompareHandsHighCardTie() {
		//Arrange	
		PokerHand black = new PokerHand("5C 4D 6H AC 7D");
		PokerHand white = new PokerHand("5D 4C 6S AD 7C");		
		
		//Act
		String result = PokerHandComparer.compareHands(black, white);
		
		//Assert
		assertEquals("Tie", result);
	}	
	
	@Test
	public void testGenerateResultsTie() {
		//Arrange
		PokerHand black = new PokerHand("5C 4D 6H AC 7D");
		PokerHand white = new PokerHand("5D 4C 6S AD 7C");
		String winner="Tie";
		
		//Act
		String result=PokerHandComparer.generateResults(winner, black, white);
		
		//Assert
		assertEquals("Tie.",result);
	}
	
	@Test
	public void testGenerateResultsBlackWins() {
		//Arrange
		PokerHand black = new PokerHand("2C 3D 2H 5C 7D");
		PokerHand white = new PokerHand("5D 4C 6S AD 7C");
		String winner="Black";
		
		//Act
		String result=PokerHandComparer.generateResults(winner, black, white);
		
		//Assert
		assertEquals("Black wins. - with pair: 2",result);
	}
	
	@Test
	public void testGenerateResultsWhiteWins() {
		//Arrange
		PokerHand black = new PokerHand("5D 4C 6S AD 7C");
		PokerHand white = new PokerHand("2C 3D 2H 5C 7D");
		String winner="White";
		
		//Act
		String result=PokerHandComparer.generateResults(winner, black, white);
		
		//Assert
		assertEquals("White wins. - with pair: 2",result);
	}
}
