package question1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Iterator;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import question1.Card;

public class Deck implements Iterable<Card>, Serializable
{
  private void newDeck()
  {
    if (deck.size() > 0)
      deck.clear(); // clear it out so we've got no cards

    // fill it with cards from each suit in order
    for (Card.Rank r = Card.Rank.TWO; r != Card.Rank.ACE; r = r.getNext())
      deck.add(new Card(Card.Suit.CLUBS, r));
    deck.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
    for (Card.Rank r = Card.Rank.TWO; r != Card.Rank.ACE; r = r.getNext())
      deck.add(new Card(Card.Suit.DIAMONDS, r));
    deck.add(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
    for (Card.Rank r = Card.Rank.TWO; r != Card.Rank.ACE; r = r.getNext())
      deck.add(new Card(Card.Suit.HEARTS, r));
    deck.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
    for (Card.Rank r = Card.Rank.TWO; r != Card.Rank.ACE; r = r.getNext())
      deck.add(new Card(Card.Suit.SPADES, r));
    deck.add(new Card(Card.Suit.SPADES, Card.Rank.ACE));
  }

  public void shuffle()
  {
    final int len = size();

    if (len <= 1) // if we've got 1 or less cards, no need to shuffle
      return;

    for (int i = len-1; i >= 1; --i)
    {
      int j = rg.nextInt(i+1);
      Collections.swap(deck, i, j);
    }
  }

  public Deck()
  {
    deck = new ArrayList<>();

    rg = new Random();
    newDeck(); // fill the deck
  }

  public Card deal()
  {
    return deck.remove(size()-1);
  }

  public Card peek()
  {
    return deck.get(size()-1);
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    for (Card c : deck)
      sb.append(c).append(" ");
    return sb.toString();
  }

  public int size() { return deck.size(); }

  @Override
  public Iterator<Card> iterator()
  {
    return deck.iterator();
  }

  public final class OddEvenIterator implements Iterator<Card>
  {
    public OddEvenIterator()
    {
      pos = 0;
    }

    @Override
    public boolean hasNext()
    {
      final int n = pos;
      if (n >= size())
      {
        if (n % 2 == 1)
          return false;
      }
      return true;
    }

    @Override
    public Card next()
    {
      final int n = pos;
      if (n >= size())
      {
        if (n % 2 == 0)
          pos = 1;
        else
          return null;
      }
      Card c = deck.get(pos);
      pos += 2;
      return c;
    }

    @Override
    public void remove()
    {
      throw new UnsupportedOperationException();
    }

    private int pos;
  }

  private void readObject(ObjectInputStream is) throws ClassNotFoundException, IOException
  {
    int sz = (int)is.readObject();
    for (int i = 0; i <= sz/2; ++i)
      deck.add((Card)is.readObject());
    for (int i = sz/2, j = 1; i < sz; ++i, j+=2)

      deck.add(j, (Card)is.readObject());
    rg = (Random)is.readObject();
  }
  private void writeObject(ObjectOutputStream os) throws IOException
  {
    os.writeObject(size());
    OddEvenIterator it = new OddEvenIterator();
    while (it.hasNext())
    {
      Card c = it.next();
      os.writeObject(c);
    }
    os.writeObject(rg);
  }

  private ArrayList<Card> deck;
  private Random rg;
  private static final long serialVersionUID = 101L;
}
