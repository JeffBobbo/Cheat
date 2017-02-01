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
   * Decides whether to cheat
   * For BasicCheat, this simply means only if we have to.
   * @param b The last bid made, which would've been the previous player
   * @param h The hand to play from
   * @return True if a cheat is happening
   */
  @Override
  public boolean cheat(Bid b, Hand h)
  {
    return h.countRank(b.getRank()) == 0 &&
      h.countRank(b.getRank().getNext()) == 0;
  }

  /**
   * Determine a hand to play
   * @param b The last bid made
   * @param h The hand to choose cards from
   * @param cheat Whether we need to, or are going to, cheat
   * @return The bid to play, including the cards and what the rank is claimed to be.
   */
  @Override
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
   * Determine if a player should be called cheat based on the cards we have
   * and what they played
   * @param h The hand we have
   * @param b The bid they made
   * @param player ID of the player who made the bid
   * @return True to call cheat
   */
  @Override
  public boolean callCheat(Hand h, Bid b, int player)
  {
    // if the number of cards I have, plus the number in the bid is greater than four
    // then this is a cheat
    return h.countRank(b.getRank()) + b.getCount() > 4;
  }

  /**
   * Distributes information about a cheat call.
   * @param player The player who made the bid
   * @param caller The player who made the call
   * @param correct Whether the player was correct
   */
  @Override
  public void broadcastCheat(int player, int caller, boolean correct)
  {
  }

  protected Random rg;
}
