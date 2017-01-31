package question1;

import java.util.Comparator;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Card implements Comparable<Card>, Serializable
{
  public enum Suit
  {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    @Override
    public String toString()
    {
      switch (this)
      {
        case CLUBS:
          return "Clubs";
        case DIAMONDS:
          return "Diamonds";
        case HEARTS:
          return "Hearts";
        case SPADES:
          return "Spades";
      }
      return "?";
    }
  }

  public enum Rank
  {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    public Rank getNext()
    {
      return Rank.values()[(ordinal() + 1) % Rank.values().length];
    }

    @Override
    public String toString()
    {
      // more space considerate than the full name
      switch (this)
      {
        case JACK:
          return "J";
        case QUEEN:
          return "Q";
        case KING:
          return "K";
        case ACE:
          return "A";
        default:
          return String.valueOf(value);
      }
    }

    public static Rank fromString(String str)
    {
      switch (str)
      {
        case "j":
        case "jack":
          return Card.Rank.JACK;
        case "q":
        case "queen":
          return Card.Rank.QUEEN;
        case "k":
        case "king":
          return Card.Rank.KING;
        case "a":
        case "ace":
          return Card.Rank.ACE;
      }
      int i;
      try
      {
        i = Integer.valueOf(str);
      }
      catch (Exception e)
      {
        return null;
      }
      if (i < 2 || i > 13)
        return null;
      Rank r = Rank.TWO;
      while (r.getValue() != i)
        r = r.getNext();
      return r;
    }
    private int value;
    private Rank(int v) { value = v; }
    public int getValue() { return value; };
  }

  public static class CompareDescending implements Comparator<Card>
  {
    @Override
    public int compare(final Card c0, final Card c1)
    {
      int r = c1.rank.compareTo(c0.rank);
      return r != 0 ? r : c0.suit.compareTo(c1.suit);
    }

    public boolean equals(final Card c0, final Card c1)
    {
      return c0.compareTo(c1) == 0;
    }
  };

  public class CompareSuit implements Comparator<Card>
  {
    @Override
    public int compare(final Card c0, final Card c1)
    {
      int s = c0.suit.compareTo(c1.suit);
      return s != 0 ? s : c0.rank.compareTo(c1.rank);
    }

    public boolean equals(final Card c0, final Card c1)
    {
      return compare(c0, c1) == 0;
    }
  };

  public Card(final Suit s, final Rank r)
  {
    this.suit = s;
    this.rank = r;
  }

  public Suit getSuit() { return suit; }
  public Rank getRank() { return rank; }

  @Override
  public int compareTo(final Card c)
  {
    int r = rank.compareTo(c.rank);
    return r != 0 ? r : suit.compareTo(c.suit);
  }

  public boolean equals(final Card c)
  {
    return this.compareTo(c) == 0;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(suit).append(rank);
    return sb.toString();
  }

  public static int difference(final Card c0, final Card c1)
  {
    return c1.rank.ordinal() - c0.rank.ordinal();
  }
  public static int differenceValue(final Card c0, final Card c1)
  {
    return c1.rank.value - c0.rank.value;
  }


  private void readObject(ObjectInputStream is) throws ClassNotFoundException, IOException
  {
    suit = (Card.Suit)is.readObject();
    rank = (Card.Rank)is.readObject();
  }
  private void writeObject(ObjectOutputStream os) throws IOException
  {
    os.writeObject(suit);
    os.writeObject(rank);
  }


  private Suit suit;
  private Rank rank;
  private static final long serialVersionUID = 100L;
}
