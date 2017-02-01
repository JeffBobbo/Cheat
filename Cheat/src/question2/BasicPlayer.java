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
   * Adds a Card to the player's Hand
   * @param c The card to add
   */
  @Override
  public void addCard(Card c)
  {
    hand.add(c);
  }

  /**
   * Adds all the cards in h to the player's Hand
   * @param h The hand to add cards from
   */
  @Override
  public void addHand(Hand h)
  {
    hand.add(h);
  }

  /**
   * The number of cards in the Hand
   * @return The number of cards in the Hand
   */
  @Override
  public int cardsLeft()
  {
    return hand.size();
  }

  /**
   * Sets a reference to the CardGame we're playing
   * @param g The CardGame being played
   */
  @Override
  public void setGame(CardGame g)
  {
    game = g;
  }

  /**
   * Sets a reference to the Strategy used by the player
   * @param s The Strategy to use
   */
  @Override
  public void setStrategy(Strategy s)
  {
    strategy = s;
  }

 /**
  * Constructs a bid when asked to by the game.
  * @param b The last bid accepted by the game. .
  * @return The player's bid
  */
  @Override
  public Bid playHand(Bid b)
  {
    return strategy.chooseBid(b, hand, strategy.cheat(b, hand));
  }

 /**
  * Determine if the last play was a cheat
  * @param b The last player's bid
  * @param player The ID of the player who made the bid
  * @return true if calling the last player a cheat.
  */
  @Override
  public boolean callCheat(Bid b, int player)
  {
    return strategy.callCheat(hand, b, player);
  }

  /**
   * Broadcast when a cheat call has been made
   * @param player The player who made the bid 
   * @param caller The player who made the call
   * @param correct Whether the call was correct
   */
  @Override
  public void broadcastCheat(int player, int caller, boolean correct)
  {
    strategy.broadcastCheat(player, caller, correct);
  }


  private Hand hand;
  private Strategy strategy;
  private CardGame game;
}
