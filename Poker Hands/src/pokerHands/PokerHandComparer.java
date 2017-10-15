package pokerHands;
import java.util.ArrayList;
import java.util.Collections;
/**
 * 
 * @author Sean Mitchem
 *
 */
public class PokerHandComparer {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//"2H 3H 4H 5H 6H" Straight Flush: 9
		//"2H 2D 2C 2S 5H" 4 of a Kind: 8 
		//"2C 2H 2D 5H 5D" Full House: 7
		//"AH 3H 5H 9H KH" Flush: 6
		//"2H 3D 4H 5H 6H" Straight: 5
		//"2H 2D 2C 5H 6H" 3 of a Kind: 4
		//"2H 2D 3H 3D 4H" 2 Pair: 3
		//"2H 2D 7H 9D AC" Pair: 2
		//"2H 5D 8D 9S KC" High Card: 1

		PokerHand blackHand = new PokerHand("2C 3C 4C 5C 6C");
		PokerHand whiteHand = new PokerHand("2C 2D 2H 2S 3D");

		String winner = compareHands(blackHand, whiteHand);
		System.out.println(generateResults(winner, blackHand, whiteHand));
	}
	
	/**
	 * 
	 * @param winner
	 * @param black
	 * @param white
	 * @return a string detailing who won, the category of their hand (e.g. a flush),
	 * and the strongest card in their hand
	 */
	public static String generateResults(String winner, PokerHand black, PokerHand white) {
		String winningHand="";
		String winningCards="";
		if(winner=="Tie") {
			return "Tie.";
		}
		int winningScore=-1;
		winningScore = Math.max(black.getScore(),white.getScore());
		switch (winningScore){
			case 1: winningHand="high card: ";
				break;
			case 2: winningHand="pair: ";
				break;
			case 3: winningHand="two pair: ";
				break;
			case 4: winningHand="three of a kind: ";
				break;
			case 5: winningHand="straight: ";
				break;
			case 6: winningHand="flush: ";
				break;
			case 7: winningHand="full house: ";
				break;
			case 8: winningHand="four of a kind: ";
				break;
			case 9: winningHand="straight flush: ";
				break;
		}
		if (winner=="Black") {
			winningCards=black.getKeyCards();
		}
		else {
			winningCards=white.getKeyCards();
		}
		winningCards=winningCards.replace("11", "jack");
		winningCards=winningCards.replace("12", "queen");
		winningCards=winningCards.replace("13", "king");
		winningCards=winningCards.replace("14", "ace");
		
		return(winner+" wins. - with "+winningHand+winningCards);
	}
	
	/**
	 * 
	 * @param Black
	 * @param White
	 * @return a String containing the outcome of the comparison: either "Black", "White", or "Tie"
	 */
	public static String compareHands(PokerHand Black, PokerHand White) {
		String winner= "Tie"; 
		if (Black.getScore()>White.getScore()) {
			winner = "Black";
		}
		else if (Black.getScore()<White.getScore()) {
			winner = "White";
		}
		else {//Same score
			//if they're the same numerically and have the same score, it's a tie
			if (Black.getSortedCardNumbers().equals(White.getSortedCardNumbers())){
				winner="Tie";
				return winner;
			}
			//if both are straight or straight flush, there's a possible Ace-Through-Five edge case
			//Ace-Through-Five loses to all other Straights/Straight Flushes of its kind
			else if (Black.getSortedCardNumbers().equals(PokerHand.getAceThroughFive())) {
				winner="White";
				return winner;
			}
			else if(White.getSortedCardNumbers().equals(PokerHand.getAceThroughFive())) {
				winner="Black";
				return winner;
			}
			else if(Black.getScore()==7) {
				char blackHighNum = Black.getKeyCards().charAt(0);
				char whiteHighNum= White.getKeyCards().charAt(0);
				if(blackHighNum>whiteHighNum) {
					winner="Black";
				}
				else {
					winner="White";
				}
				return winner;
			}
			else {				
				ArrayList<Integer> blackNumbers= Black.getSortedCardNumbers();
				ArrayList<Integer> whiteNumbers= White.getSortedCardNumbers();
				Collections.reverse(blackNumbers);
				Collections.reverse(whiteNumbers);
				for (int i=0; i<PokerHand.getHandSize(); i++) {
					if(blackNumbers.get(i)>whiteNumbers.get(i)) {
						winner="Black";
						break;
					}
					else if(blackNumbers.get(i)<whiteNumbers.get(i)) {
						winner="White";
						break;
					}
				}
			}
		}
		return winner;
	}
}
