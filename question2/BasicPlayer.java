package question2;

import question1.Card;
import question1.Hand;

import question2.Player;
import question2.BasicCheat;
import question2.Strategy;

public class BasicPlayer implements Player
{
  BasicPlayer(Strategy s, CardGame g)
  {
    hand = new Hand();
    setStrategy(s);
    setGame(g);
  }
 /**
  * add to the players hand
  * @param c: Card to add
  */
  public void addCard(Card c)
  {
    hand.add(c);
  }

 /**
  * add all the cards in h to the players hand
  * @param h: hand to add
  */
  public void addHand(Hand h)
  {
    hand.add(h);
  }

 /**
  * @return number of cards left in the players hand
  */
  public int cardsLeft()
  {
    return hand.size();
  }

 /**
  * @param g: the player should contain a reference to the game it is playing in
  */
  public void setGame(CardGame g)
  {
    game = g;
  }

 /**
  * @param s: the player should contain a reference to its strategy
  */
  public void setStrategy(Strategy s)
  {
    strategy = s;
  }

 /**
  * Constructs a bid when asked to by the game.
  * @param b: the last bid accepted by the game. .
  * @return the players bid
  */
  public Bid playHand(Bid b)
  {
    return strategy.chooseBid(b, hand, strategy.cheat(b, hand));
  }

 /**
  * @param b: the last players bid
  * @return true if calling the last player a cheat.
  */
  public boolean callCheat(Bid b)
  {
    return strategy.callCheat(hand, b);
  }

  public void broadcastCheat(int player, int caller, boolean correct)
  {
    strategy.broadcastCheat(player, caller, correct);
  }


  private Hand hand;
  private Strategy strategy;
  private CardGame game;
}
