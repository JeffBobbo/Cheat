package question2;

import java.util.ArrayList;

import question1.Card;
import question1.Hand;

import question2.Strategy;
import question2.BasicStrategy;

public class ThinkerStrategy extends BasicStrategy
{
  public ThinkerStrategy()
  {
    super();
    played = new ArrayList<Bid>();
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
    return super.cheat(b, h) || rg.nextDouble() < CHEAT_RATE;
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
    Bid ret;
    if (cheat)
    {
      // sort the hand so the high values are near the front
      h.sortDescending();

      // roll 1.5 times higher
      int roll = (int)(h.size()*1.5);
      // and modulo is, this gives a large bias to the first half
      // (two chances for the first half, one for the rest)
      // which, because we sorted, is the high value cards
      int pos = rg.nextInt(roll) % h.size();
      play.add(h.remove(pos));
      ret = new Bid(play, b.getRank().getNext());
    }
    else
    {
      int total = h.countRank(b.getRank()) + h.countRank(b.getRank().getNext());

      // work out whichever rank is higher
      Card.Rank high = b.getRank().getNext();
      Card.Rank low  = b.getRank();
      if (high.ordinal() < b.getRank().ordinal())
      {
        low = high;
        high = b.getRank();
      }

      double r = (double)h.countRank(high) / (double)total;

      // use r, which is percentage of high cards as the roll chance
      // if we roll less than r, play a high card.
      // this'll conserve our higher card when we've got less of them
      Card.Rank target = rg.nextDouble() < r ? high : low;
      int num = rg.nextDouble() > PLAY_RANDOM_RATE ?
        rg.nextInt(h.countRank(target)) + 1 : // play random amount
        h.countRank(target); //full amount
      // could be rewritten to be better, but number of cards in h will not be large
      // and branch prediction will kick in for us anyway
      for (Card c : h)
      {
        if (num > 0 && c.getRank().equals(target))
        {
          play.add(c);
          --num;
        }
      }
      h.remove(play);
      ret = new Bid(play, target);
    }
    played.add(ret);
    return ret;
  }

 /**
  *
  * @param b the current bid
  * @return true if this player is going to call cheat  on the last play b
  */
  public boolean callCheat(Hand h, Bid b, int player)
  {
    int count = 0;
    // add up previous bids
    for (Bid bid : played)
    {
      for (Card c : bid.getHand())
      {
        if (c.getRank() == b.getRank())
          ++count;
      }
    }

    // and current cards and the claimed number
    count += h.countRank(b.getRank()) + b.getCount();

    if (count > 4) // always call if it's over 4
      return true;
    // otherwise have a small increasing chance
    return rg.nextDouble() > 1.0 - (CHEAT_CALL_MULT * count);
  }

  public void broadcastCheat(int player, int caller, boolean correct)
  {
    played.clear();
  }

  protected ArrayList<Bid> played;
  protected static final double CHEAT_RATE = 0.15;
  protected static final double PLAY_RANDOM_RATE = 0.75;
  protected static final double CHEAT_CALL_MULT = 0.025;
}
