import java.util.List;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.lang.Math;

enum Suit
{
    SPADES("Spades","Black"),
    HEARTS("Hearts","Red"),
    DIAMONDS("Diamonds","Red"),
    CLUBS("Clubs","Black");
        
    private String color;
    private String name;
    private Suit(String name, String color)
    {
        this.name = name;
        this.color = color;
    };
    public String getName()
    {
        return name;
    }
    public String getColor()
    {
        return color;
    }
    public int getOrdinal()
    {
        return ordinal();
    }
}

enum Rank
{
    TWO("Two",2),
    THREE("Three",3),
    FOUR("Four",4),
    FIVE("Five",5),
    SIX("Six",6),
    SEVEN("Seven",7),
    EIGHT("Eight",8),
    NINE("Nine",9),
    TEN("Ten",10),
    JACK("Jack",10),
    QUEEN("Queen",10),
    KING("King",10),
    ACE("Ace",1);
  
    private String name;
    private int value;
    private Rank(String name, int value)
    {
        this.name = name;
        this.value = value;
    };
    public String getName()
    {
        return name;
    }
    public int getValue()
    {
        return value;
    }
    public int getOrdinal()
    {
        return ordinal();
    }
};

public class DeckOfCards
{
    class Card
    {
        private Rank rank;
        private Suit suit;
        public Card(Rank rank, Suit suit)
        {
            this.rank = rank;
            this.suit = suit;
        };
        public Rank getRank()
        {
            return rank;
        }
        public Suit getSuit()
        {
            return suit;
        }
        public String getString()
        {
            return     getRank().getName() + " of " + getSuit().getName();
        }
    };
    
    public static void main(String[] args)
    {
        DeckOfCards originalDeck = new DeckOfCards();
        DeckOfCards shuffledDeck = originalDeck.shuffle();
        
        if (args[0].equalsIgnoreCase("new"))
        {
            System.out.println("                                     ");
            System.out.println("DECK OF CARDS: in order -------------");
            System.out.println("-------------------------------------");
        
            Card currentCard = originalDeck.drawCard();
            while (currentCard != null)
            {
                System.out.println(currentCard.getString());
                currentCard = originalDeck.drawCard();
            }
        }
        else if (args[0].equalsIgnoreCase("shuffled"))
        {
            System.out.println("                                     ");
            System.out.println("DECK OF CARDS: shuffled -------------");
            System.out.println("-------------------------------------");

            Card currentCard = shuffledDeck.drawCard();
            while (currentCard != null)
            {
                System.out.println(currentCard.getString());
                currentCard = shuffledDeck.drawCard();
            }
        }
    }
    private List<Card> deck;
    private int startIndex = 0;
    private int currentIndex = 0;
    private int finalIndex = 51;
    public DeckOfCards()
    {
        deck = new ArrayList<>();
        for (Suit s : Suit.values())
            for (Rank r : Rank.values())
                deck.add(new Card(r,s));
    }
    public DeckOfCards(List<Card> deck)
    {
        this.deck = deck;
    }
    public DeckOfCards shuffle()
    {
        List<Card> shuffled = new ArrayList<>();
        SortedMap<Double,Card> map = new TreeMap<>();
        for (Card c : deck) map.put(new Double(Math.random()),c);
        for (Card c : map.values()) shuffled.add(c);
        return new DeckOfCards(shuffled);
    }
    public Card drawCard()
    {
        Card card = null;
        if (currentIndex >= startIndex && currentIndex <= finalIndex)
        {
             card = deck.get(currentIndex);
             currentIndex++;
        }
        return card;
    }
}