package pokerHands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
//Class for holding information about the poker hand
public class PokerHand {
	
	//CONSTANTS
	static final int HAND_SIZE = 5;
	static final ArrayList<Integer> ACE_THROUGH_FIVE =  new ArrayList<Integer>(Arrays.asList(2,3,4,5,14));

	//Fields
	private ArrayList<String> cards;
	private int handScore;
	
	//suits and numbers/face-values are considered separately
	private ArrayList<Integer> cardNumbers ;
	private ArrayList<Integer> sortedCardNumbers;
	private ArrayList<Character> cardSuits;
	private HashMap<Integer,Integer> cardNumberOccuranceHash; 
	private String keyCards;
	
	/*Scores for Hands. 
	 *9: straight flush 
	 *8: four of a kind
	 *7: full house
	 *6: flush
	 *5: straight
	 *4: three of a kind
	 *3: two pair
	 *2: pair
	 *1: high card 
	 * */
	
	//Methods
	//Constructor
	public PokerHand(String cardsInfo){
		//Directly sets the simple field values, calls evaulateHand to set the more complex fields
		handScore = -1;
		//split the String cardsInfo into a String array, then convert to ArrayList
		cards = new ArrayList<String>(Arrays.asList(cardsInfo.split(" ")));
		
		cardSuits = generateCardSuits(cardsInfo);
		cardNumbers = generateCardNumbers(cardsInfo); 
		
		sortedCardNumbers=(ArrayList<Integer>) cardNumbers.clone();
		Collections.sort(sortedCardNumbers);
		
		cardNumberOccuranceHash = generateOccuranceHash(sortedCardNumbers);
		handScore = generateHandScore(cardSuits,sortedCardNumbers, cardNumberOccuranceHash);
		keyCards = generateKeyCards(handScore, sortedCardNumbers,cardNumberOccuranceHash);		
	}
	
	//Card data format for a three of clubs: 3C  
	
	private ArrayList<Integer> generateCardNumbers (String cardsInfo){
		ArrayList<Integer> cardNumbersToReturn = new ArrayList<Integer>();
		for (int i = 0; i < cardsInfo.length(); i = i + 3) {
			switch (cardsInfo.charAt(i)) {
				case 'T':
					cardNumbersToReturn.add(10);
					break;
				case 'J':
					cardNumbersToReturn.add(11);
					break;
				case 'Q': 
					cardNumbersToReturn.add(12);
					break;
				case 'K': 
					cardNumbersToReturn.add(13);
					break;
				case 'A': 
					cardNumbersToReturn.add(14); 
					break;
				default:
					cardNumbersToReturn.add(Character.getNumericValue(cardsInfo.charAt(i)));
					break;
			}	
		}
		return cardNumbersToReturn;
	}
	
	
	private ArrayList<Character> generateCardSuits (String cardsInfo){
		ArrayList<Character> cardSuitsToReturn = new ArrayList<Character>();
		for (int i = 1; i < cardsInfo.length(); i = i + 3) {
			cardSuitsToReturn.add(cardsInfo.charAt(i));
		}
		return cardSuitsToReturn;
	}
	
	
	private HashMap<Integer, Integer> generateOccuranceHash(ArrayList<Integer> sortedCardNumbersGiven){
		HashMap<Integer,Integer> cardNumberOccuranceHashToReturn = new HashMap<Integer,Integer>();
		for (int i=0; i<HAND_SIZE;i++) {
			Integer cardNumber = sortedCardNumbersGiven.get(i);
			//If the number has already been seen, add +1 to the count for that number
			if (cardNumberOccuranceHashToReturn.containsKey(cardNumber)) {
				cardNumberOccuranceHashToReturn.put(cardNumber, cardNumberOccuranceHashToReturn.get(cardNumber)+1);
			}
			//if the number hasn't been seen, then add it to the hash
			else {
				cardNumberOccuranceHashToReturn.put(cardNumber, 1);
			}
		}
		return cardNumberOccuranceHashToReturn;
	}

	
	//implementation that generates the score for the hand
	private int generateHandScore(ArrayList<Character> cardSuitsGiven, ArrayList<Integer> sortedCardNumbersGiven, HashMap<Integer,Integer>cardNumberOccuranceHashGiven) {
		//set score for hand
		//set key cards for hand
		
		int handScoreToReturn=-1;
		boolean isFlush;
		boolean isStraight;

		//check for a flush		
		isFlush = true;
		for (int i = 0; i < HAND_SIZE-1; i++) {
			
			//if mismatched suits, flag it
			if(cardSuitsGiven.get(i)!=cardSuitsGiven.get(i+1)) {
				isFlush = false;
			}
		}
		//check for a straight
		if (sortedCardNumbersGiven.equals(ACE_THROUGH_FIVE)){ //edge case
			isStraight=true;
		}
		else {
			isStraight=true;
			for (int i = 0; i < HAND_SIZE-1; i++) {
				int firstCardNum = sortedCardNumbersGiven.get(i);
				int secondCardNum = sortedCardNumbersGiven.get(i+1);
				if(firstCardNum + 1 != secondCardNum) {
					isStraight=false;
				}
			}	
		}
		if(isStraight && isFlush) {
			handScoreToReturn=9;
		}
		else if(isFlush) {
			handScoreToReturn=6;
		}
		else if (isStraight) {
			handScoreToReturn=5;
		}
		if (handScoreToReturn!=-1) {
			return handScoreToReturn;
		}
		//Check for Four of a Kind
		if (cardNumberOccuranceHashGiven.containsValue(4)) {
			handScoreToReturn=8;
		}
		//Check for Full House
		else if (cardNumberOccuranceHashGiven.containsValue(3) && cardNumberOccuranceHashGiven.containsValue(2)) {
			handScoreToReturn=7;
		}
		//Check for Three of a Kind
		else if (cardNumberOccuranceHashGiven.containsValue(3)) {
			handScoreToReturn=4;
		}
		else { //Check for Two Pair, Pair, High Card
			//Iterate through hash of each card's number of occurence. See if how many values appear twice.
			Iterator<Entry<Integer,Integer>> iterator = cardNumberOccuranceHashGiven.entrySet().iterator();
			int numberOfPairs=0;
			while (iterator.hasNext()) {
				HashMap.Entry<Integer,Integer> entry = (HashMap.Entry<Integer,Integer>) iterator.next();
				if((int)entry.getValue()==2) {
					numberOfPairs++;
				}
			}
			handScoreToReturn = numberOfPairs+1;
		}
		return handScoreToReturn;
	}
	
	
	//Returns a string with details regarding the important cards to a hand
	private String generateKeyCards(int handScoreGiven, ArrayList<Integer> sortedCardNumbersGiven, HashMap<Integer,Integer> cardNumberOccuranceHashGiven) {	
		String keyCardsToReturn="";
		if((handScoreGiven==5 || handScoreGiven==9) && sortedCardNumbersGiven.equals(ACE_THROUGH_FIVE)) {
			keyCardsToReturn="5";
		}
		else {
			switch(handScoreGiven) {
			case 1:{}
			case 5:{}
			case 6:{}
			case 9:{
				keyCardsToReturn=Collections.max(sortedCardNumbersGiven).toString()+ " high";
				return keyCardsToReturn;
			}
			default:{break;}
			}
		}
		Iterator<Entry<Integer,Integer>> iterator = cardNumberOccuranceHashGiven.entrySet().iterator();
		while (iterator.hasNext()) {
			HashMap.Entry<Integer,Integer> entry = (HashMap.Entry<Integer,Integer>) iterator.next();
			
			switch((int)entry.getValue()) {
				case 4:{ //four occurances of a card's number
					keyCardsToReturn=entry.getKey().toString();
					return keyCardsToReturn;
				}
				case 3:{//three occurances
					if (handScoreGiven==7) {
						keyCardsToReturn=entry.getKey().toString()+" over ";
						entry = (HashMap.Entry<Integer,Integer>) iterator.next(); //advance to next entry
						keyCardsToReturn+=entry.getKey().toString();
						return keyCardsToReturn;
					}
					else if(handScoreGiven==4) {
						keyCardsToReturn=entry.getKey().toString();
						return keyCardsToReturn;
					}
				}
				case 2:{//two occurances
					if(handScoreGiven==7) {
						keyCardsToReturn=" over "+entry.getKey().toString();
						entry = (HashMap.Entry<Integer,Integer>) iterator.next(); //advance to next entry
						keyCardsToReturn= entry.getKey().toString()+ keyCardsToReturn;
						return keyCardsToReturn;
					}
					else if(handScoreGiven==3) {
						if (keyCardsToReturn.length()<1) {
							keyCardsToReturn+=entry.getKey().toString()+" and ";
						}
						else {
							keyCardsToReturn+=entry.getKey().toString();
						}
					}
					else if(handScoreGiven==2) {
						keyCardsToReturn=entry.getKey().toString();
						return keyCardsToReturn;
					}
				}
				default:{
					break;
				}
			}
		}
	return keyCardsToReturn;
}
	
	//getters
	public ArrayList<String> getCards() {
		return cards;
	}
	public int getScore(){
		return handScore;
	}
	public ArrayList<Integer> getSortedCardNumbers(){
		return sortedCardNumbers;
	}
	static public int getHandSize() {
		return HAND_SIZE;
	}
	static public ArrayList<Integer> getAceThroughFive() {
		return ACE_THROUGH_FIVE;
	}
	public HashMap<Integer,Integer> getCardOccurenceHash() {
		return cardNumberOccuranceHash;
	}
	public String getKeyCards() {
		return keyCards;
	}
}
