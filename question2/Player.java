package question2;

import question1.Card;
import question1.Hand;
import question2.Bid;
import question2.CardGame;
import question2.Strategy;

public interface Player
{
 /**
  * add to the players hand
  * @param c: Card to add
  */
  void addCard(Card c);

 /**
  * add all the cards in h to the players hand
  * @param h: hand to add
  */
  void addHand(Hand h);

 /**
  * @return number of cards left in the players hand
  */
  int cardsLeft();

 /**
  * @param g: the player should contain a reference to the game it is playing in
  */
  void setGame(CardGame g);

 /**
  * @param s: the player should contain a reference to its strategy
  */
  void setStrategy(Strategy s);

 /**
  * Constructs a bid when asked to by the game.
  * @param b: the last bid accepted by the game. .
  * @return the players bid
  */
  Bid playHand(Bid b);

 /**
  * @param b: the last players bid
  * @return true if calling the last player a cheat.
  */
  boolean callCheat(Bid b);

  void broadcastCheat(int player, int caller, boolean correct);
}
