package question2;

import java.util.Random;

import question1.Card;
import question1.Hand;

import question2.Strategy;

public class BasicStrategy implements Strategy
{
  public BasicStrategy()
  {
    rg = new Random();
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
    if (cheat) // we're going to cheat. Grin innanely.
    {
      int pos = rg.nextInt(h.size());
      play.add(h.remove(pos));
      return new Bid(play, b.getRank().getNext());
    }
    else // play it safe
    {
      Card.Rank target;
      // if we have some of the first card
      if (h.countRank(b.getRank()) > 0)
      {
        // and of the second
        if (h.countRank(b.getRank().getNext()) > 0 &&
          // and it's of lower rank than the first
          b.getRank().getNext().ordinal() - b.getRank().ordinal() < 0
        )
          target = b.getRank().getNext(); // play the second
        else
          target = b.getRank(); // play the first
      }
      else
      {
        // play the second, as we can't play the first
        target = b.getRank().getNext();
      }
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
    // if the number of cards I have, plus the number in the bid is greater than four
    // then this is a cheat
    return h.countRank(b.getRank()) + b.getCount() > 4;
  }

  public void broadcastCheat(int player, int caller, boolean correct)
  {
  }

  protected Random rg;
}
