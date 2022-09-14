package chapter2.elementarySorts;

public class CreativeProblems {
    public static void main(String[] args) {
		Card[] deck = buildDeck();
		Utils.shuffle(deck);
		System.out.println(Utils.print(deck));

		//deckSort(deck);
		dequeueSort(deck);

		System.out.println(Utils.print(deck));
    }

    public static void dequeueSort(Card[] deck) {
		if (Utils.less(deck[1], deck[0])) Utils.swap(deck, 0, 1);

		boolean swapped = true;
		Card initial = deck[0];
		System.out.println(String.format("Initial card is %s", initial));
		while (swapped) {
			swapped = false;
			while (initial != deck[1]) {
			Card card1 = deck[0];
			Card card2 = deck[1];

			if (Utils.less(card2, card1)) {
				Utils.swap(deck, 0, 1);
				swapped = true;
			} else {
				moveTopToBottom(deck);
			}
			}

			// We went throught an iteration!
			moveTopToBottom(deck);
			if (Utils.less(deck[1], deck[0])) Utils.swap(deck, 0, 1);
			initial = deck[0];
		}
    }

    public static void moveTopToBottom(Card[] deck) {
		Card exTop = deck[0];
		for (int j = 1; j < deck.length; j++) {
			Utils.swap(deck, j, j-1);
		}
		deck[deck.length - 1] = exTop;
    }

    public static void deckSort(Card[] deck) {
		int currentIdx = 0;
		Card currentCard = deck[currentIdx];
		int posCardInDeck = getPosInDeckOf(currentCard);
		for (int j = 0; j < deck.length; j++) {
			while (currentIdx != posCardInDeck)  {
			Utils.swap(deck, currentIdx, getPosInDeckOf(currentCard));
			currentCard = deck[currentIdx];
			posCardInDeck = getPosInDeckOf(currentCard);
			}
			currentIdx ++;
		}
    }

    public static int getPosInDeckOf(Card card) {
		int res = card.getNumber() - 1;
		switch (card.getSuit()) {
		case SPADES:
			break;
		case HEARTS:
			res += 13;
			break;
		case CLUBS:
			res += 2*13;
			break;
		case DIAMONDS:
			res += 3*13;
			break;
		default:
			System.out.println("Should never be here");
		}
		return res;
    }

    public static Card[] buildDeck() {
		Card[] res = new Card[52];
		CardSuit[] suits = new CardSuit[]{CardSuit.SPADES, CardSuit.HEARTS, CardSuit.CLUBS, CardSuit.DIAMONDS};
		int current = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 1; i < 14; i++) {
			res[current] = new Card(i, suits[j]);
			current++;
			}
		}

		return res;
    }


    static class Utils{
		private static <Item> void shuffle(Item[] array) {
			for (int i = 0; i < array.length; i++) {
			int r = (int) (Math.random() * (i + 1));
			swap(array, r, i);
			}
		}

		private static boolean less(Comparable a, Comparable b) {
			return a.compareTo(b) < 0;
		}

		private static <Item> void swap(Item[] array, int i, int j) {
			Item temp = array[i];
			array[i] = array[j];
			array[j] =  temp;
		}

		private static <Item> String print(Item[] array) {
			StringBuilder str = new StringBuilder();
			for (Item val : array) {
			str.append(val);
			str.append(" ");
			}
			return str.toString();
		}
    }
    

    static class Card implements Comparable<Card> {
		int number;
		CardSuit suit;

		Card(int number, CardSuit suit) {
			this.number = number;
			this.suit = suit;
		}

		public CardSuit getSuit() {
			return this.suit;
		}

		public int getNumber() {
			return this.number;
		}

		public int compareTo(Card another) {
			//int suitComparisson = this.getSuit().customComparisson(another.getSuit());
			if (this.getSuit() == another.getSuit()) {
			return this.number - another.getNumber();
			} else {
			return this.getSuit().compareTo(another.getSuit());
			}
		}

		@Override
		public String toString() {
			return String.format("%d of %s", this.number, this.suit);
		}
    }

    enum CardSuit {
		SPADES("Spades", 1),

		HEARTS("Hearts", 2),

		CLUBS("Clubs", 3),

		DIAMONDS("Diamonds", 4);

		String name;
		int importance;

		CardSuit(String name, int importance) {
			this.name = name;
			this.importance = importance;
		}

		public int customComparisson(CardSuit another) {
			return this.importance - another.importance;
		}

		@Override
		public String toString() {
			return name;
		}
    }
}
