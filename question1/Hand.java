package question1;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import question1.Card;

public class Hand
{
  public Hand()
  {
    hand = new ArrayList<Card>(); // replace with LinkedHashSet
    // bad, hard coded values
    suits = new int[4];
    ranks = new int[13];
  }
  public Hand(Card[] cards)
  {
    this();
    for (Card c : cards)
      addCard(c);
  }
  public Hand(final Hand h)
  {
    this();
    addHand(h);
  }

  public int size() { return hand.size(); }
  public int countSuit(final Card.Suit s) { return suits[s.ordinal()]; }
  public int countRank(final Card.Rank r) { return ranks[r.ordinal()]; }

  public int handValue()
  {
    int v = 0;
    for (Card c : hand)
      v += c.getRank().getValue();
    return v;
  }

  public void addCard(final Card c)
  {
    hand.add(c);
    suits[c.getSuit().ordinal()]++;
    ranks[c.getRank().ordinal()]++;
  }
  public void addCollection(Collection<Card> cards)
  {
    for (Card c : cards)
      addCard(c);
  }
  public void addHand(final Hand h)
  {
    for (Card c : h.hand)
      addCard(c);
  }

  boolean removeCard(final Card c)
  {
    suits[c.getSuit().ordinal()]--;
    ranks[c.getRank().ordinal()]--;
    return hand.remove(c);
  }
  boolean removeHand(final Hand h)
  {
    for (Card c : h.hand)
      removeCard(c);
    return false;
  }
  Card removeAt(final int pos)
  {
    if (pos < 0)
      return null;
    if (pos > size())
      return null;
    Card c = hand.get(pos);
    return removeCard(hand.get(pos)) ? c : null;
  }

  public void sortAscending()
  {
    Collections.sort(hand);
  }

  public void sortDescending()
  {
    Collections.sort(hand, new Card.CompareDescending());
  }

  public boolean isFlush()
  {
    // if we have no cards, we have no flush
    if (size() == 0)
      return false;

    Card.Suit s = hand.get(0).getSuit();
    for (int i = 1; i < size(); ++i)
    {
      if (!hand.get(i).getSuit().equals(s))
        return false;
    }
    return true;
  }

  public boolean isStraight()
  {
    ArrayList<Card.Rank> ranks = new ArrayList<Card.Rank>();
    for (Card c : hand)
    {
      if (ranks.contains(c.getRank()))
        return false;
      ranks.add(c.getRank());
    }
    Collections.sort(ranks);

    for (int i = 0; i < ranks.size(); ++i)
    {
      boolean okay = true;
      Card.Rank r = ranks.get(i);
      for (int j = 1; j < ranks.size(); ++j)
      {
        if (!ranks.get((i+j)%ranks.size()).equals(r = Card.Rank.getNext(r)))
          okay = false;
      }
      if (okay)
        return true;
    }

    return false;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    // TODO: Improve
    for (Card c : hand)
      sb.append(c).append(" ");
    return sb.toString();
  }

  // TODO: FIX THESE
  private void readObject(ObjectInputStream is) throws ClassNotFoundException, IOException
  {
    is.defaultReadObject();
  }
  private void writeObject(ObjectOutputStream os) throws IOException
  {
    os.defaultWriteObject();
  }


  private ArrayList<Card> hand;
  private static final long serialVersionUID = 102L;
  // bad, hard coded
  private int[] suits;
  private int[] ranks;
}
