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
    // using this method would require doing double the work we'd have to do
    // in the best case, and increase it by half in the worse case
    // thus, we're going to leave it as a stub
    return false;
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
    Card.Rank rank = null;
    int wiggle = 0; // 'wiggle' is how far out we can go
    // this'll only go to 1, but we could increase this higher if we wanted
    // to allow players more range on bids (e.g., bidding 5s on 2s).
    while (wiggle <= 1 && play.size() == 0)
    {
      for (Card c : h)
      {
        // for each card, find those that match the wiggle room
        int diff = c.getRank().ordinal() - b.getRank().ordinal();
        // specification definition for difference says it should compare the
        // difference of Cards, but we've only got a rank so we can't use it.
        if (diff == wiggle)
        {
          if (rank != null || c.getRank() == rank)
          {
            rank = c.getRank();
            play.add(c);
          }
        }
      }
      ++wiggle;
    }
    if (play.size() > 0) // we have cards, so play 'em
    {
      h.removeHand(play);
      return new Bid(play, rank);
    }
    else // we've exhausted all our wiggle room and still have no cards, cheat!
    {
      Random r = new Random(); // really bad, but oh well
      int pos = r.nextInt(h.size());
      play.add(h.removeAt(pos));
      return new Bid(play, Card.Rank.getNext(b.getRank()));
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
