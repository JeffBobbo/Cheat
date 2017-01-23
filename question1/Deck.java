package question1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Iterator;

import question1.Card;

public class Deck implements Iterable<Card>
{
  private void newDeck()
  {
    if (deck.size() > 0)
      deck.clear(); // clear it out so we've got no cards

    // fill it with cards from each suit in order
    for (Card.Rank r = Card.Rank.TWO; r != Card.Rank.ACE; r = Card.Rank.getNext(r))
      deck.add(new Card(Card.Suit.CLUBS, r));
    deck.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
    for (Card.Rank r = Card.Rank.TWO; r != Card.Rank.ACE; r = Card.Rank.getNext(r))
      deck.add(new Card(Card.Suit.DIAMONDS, r));
    deck.add(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
    for (Card.Rank r = Card.Rank.TWO; r != Card.Rank.ACE; r = Card.Rank.getNext(r))
      deck.add(new Card(Card.Suit.HEARTS, r));
    deck.add(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
    for (Card.Rank r = Card.Rank.TWO; r != Card.Rank.ACE; r = Card.Rank.getNext(r))
      deck.add(new Card(Card.Suit.SPADES, r));
    deck.add(new Card(Card.Suit.SPADES, Card.Rank.ACE));
  }

  public void shuffle()
  {
    final int len = size();

    if (len <= 1) // if we've got 1 or less cards, no need to shuffle
      return;

    Random r = new Random(); // potential issue here, each reshuffle requires
    // a reseed of the rng, which can be exploited

    for (int i = len-1; i >= 1; --i)
    {
      int j = r.nextInt(i+1);
      Collections.swap(deck, i, j);
    }
  }

  public Deck()
  {
    deck = new ArrayList<Card>();

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
    return new OddEvenIterator();
  }

  public final class OddEvenIterator implements Iterator<Card>
  {
    public OddEvenIterator()
    {
      pos = 0;
    }

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

    public void remove()
    {
      throw new UnsupportedOperationException();
    }

    private int pos;
  }

  private ArrayList<Card> deck;
}
