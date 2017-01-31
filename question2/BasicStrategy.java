package question2;

import java.util.Random;

import question1.Card;
import question1.Hand;

import question2.Strategy;

public class BasicStrategy implements Strategy
{
  public BasicStrategy()
  {
  }
 /**
  * Decides on whether to cheat or not
  * @param b   the bid this player has to follow (i.e the
  * bid prior to this players turn.
  * @param h   The players current hand
  * @return true if the player will cheat, false if not
  */
  public boolean cheat(Bid b, Hand h)
  {
    return h.countRank(b.getRank()) == 0 &&
      h.countRank(b.getRank().getNext()) == 0;
  }

 /**
  * @param b   the bid the player has to follow.
  * @param h   The players current hand
  * @param cheat true if the Strategy has decided to cheat (by call to cheat())
  * @return a Bid with the cards to pass to the game and the Rank. This will be
  * different to the rank of thecards if the player is cheating!
  *
  */
  public Bid chooseBid(Bid b, Hand h, boolean cheat)
  {
    Hand play = new Hand();
    if (cheat) // we have cards, so play 'em
    {
      Random r = new Random(); // really bad, but oh well
      int pos = r.nextInt(h.size());
      play.add(h.remove(pos));
      return new Bid(play, b.getRank().getNext());
    }
    else // we've exhausted all our wiggle room and still have no cards, cheat!
    {
      Card.Rank target = h.countRank(b.getRank()) > 0 ? b.getRank() :
        b.getRank().getNext();
      for (Card c : h)
      {
        if (c.getRank().equals(target))
          play.add(c);
      }
      h.remove(play);
      return new Bid(play, target);
    }
  }

 /**
  *
  * @param b the current bid
  * @return true if this player is going to call cheat  on the last play b
  */
  public boolean callCheat(Hand h, Bid b)
  {
    // how many cards are left unaccounted for
    int left = 4 - h.countRank(b.getRank());
    // if what is left, minus the number of cards in the bid is less than 0
    // then this a cheat
    return left - b.getCount() < 0;
  }
}
