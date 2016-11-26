package question1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

import question1.Card;

public class Deck
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

    // calculate how much we need to shuffle
    // added bonus, this'll only give us 1 shuffle for 2 cards
    final int shuffles = (int)Math.pow(len-1, 2);

    Random r = new Random(); // potential issue here, each reshuffle requires
    // a reseed of the rng, which can be exploited

    for (int i = 0; i < shuffles; ++i)
    {
      final int a = r.nextInt(len);
      final int b = r.nextInt(len);
      if (a == b) // picked the same card, swapping is nop
        continue;
      Collections.swap(deck, a, b);
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

  /*
  public class OddEvenIterator<Card> extends Iterator<Object> implements Iterable<Card>
  {
    public OddEvenIterator()
    {
      pos = size() - 1;
    }

    public boolean hasNext()
    {
      return pos >= 0;
    }

    public Card next()
    {
      return hasNext() == true ? deck.get(pos--) : null;
    }

    @Override
    public Iterator<Card> iterator()
    {
      return new OddEvenIterator();
    }

    private int pos;
  }
  */

  private ArrayList<Card> deck;
}
