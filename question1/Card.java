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
          return "C";
        case DIAMONDS:
          return "D";
        case HEARTS:
          return "H";
        case SPADES:
          return "S";
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

    public static Rank getNext(final Rank r)
    {
      return Rank.values()[(r.ordinal() + 1) % Rank.values().length];
    }

    @Override
    public String toString()
    {
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
    is.defaultReadObject();
  }
  private void writeObject(ObjectOutputStream os) throws IOException
  {
    os.defaultWriteObject();
  }


  private Suit suit;
  private Rank rank;
  private static final long serialVersionUID = 100L;
}
