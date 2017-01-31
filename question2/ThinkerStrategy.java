package question2;

import question1.Card;
import question1.Hand;

import question2.Strategy;
import question2.BasicStrategy;

public class ThinkerStrategy extends BasicStrategy
{
  public ThinkerStrategy()
  {
    super();
    known = new ArrayList<Bid>();
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
    return super() ? true : rg.nextDouble() < 0.05;
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
    if (cheat)
    {

    }
  }

 /**
  *
  * @param b the current bid
  * @return true if this player is going to call cheat  on the last play b
  */
  public boolean callCheat(Hand h, Bid b)
  {
  }

  public void broadcastCheat(int player, int caller, boolean correct)
  {
    known.clear();
  }

  private ArrayList<Bid> known;

}
