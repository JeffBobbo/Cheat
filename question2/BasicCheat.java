package question2;

import java.util.Iterator;
import java.util.Scanner;

import question1.Card;
import question1.Hand;
import question1.Deck;

import question2.CardGame;
import question2.Player;
import question2.Bid;

import question2.BasicPlayer;
import question2.BasicStrategy;
import question2.HumanStrategy;
import question2.ThinkerStrategy;
import question2.StrategyFactory;

public class BasicCheat implements CardGame
{
  public static final int MINPLAYERS = 5;
  private Player[] players;
  private int nosPlayers;
  private int currentPlayer;
  private Hand discards;
  private Bid currentBid;


  public BasicCheat()
  {
    this(MINPLAYERS);
  }

  public BasicCheat(int n)
  {
    nosPlayers = n;
    players = new Player[nosPlayers];
    StrategyFactory sf = new StrategyFactory();
    for (int i = 0; i < nosPlayers; ++i)
    {
      Strategy s = StrategyFactory.produce(sf.choose());
      players[i] = (new BasicPlayer(s, this));
    }

    currentBid = new Bid();
    currentBid.setRank(Card.Rank.TWO);
    currentPlayer = 0;
  }

  @Override
  public boolean playTurn()
  {
    //Ask player for a play,
    System.out.println("current bid = " + currentBid);
    currentBid = players[currentPlayer].playHand(currentBid);

    System.out.println("Player bid = " + currentBid);
    // Add hand played to discard pile
    discards.add(currentBid.getHand());
    // Offer all other players the chance to call cheat
    boolean cheat = false;
    for (int i = 0; !cheat && i < players.length; ++i)
    {
      if (i != currentPlayer)
      {
        cheat = players[i].callCheat(currentBid, currentPlayer);
        if (cheat)
        {
          System.out.println("Player called cheat by Player " + (i+1));
          if (isCheat(currentBid)) //CHEAT CALLED CORRECTLY
          {
            for (Player p : players)
              p.broadcastCheat(currentPlayer, i, true);
            //Give the discard pile of cards to currentPlayer who then has to play again
            players[currentPlayer].addHand(discards);
            System.out.println("Player cheats!");
            System.out.println("Adding cards to player "+
                (currentPlayer+1) + " " + players[currentPlayer]);
          }
          else //CHEAT CALLED INCORRECTLY
          {
            for (Player p : players)
              p.broadcastCheat(currentPlayer, i, false);
            System.out.println("Player Honest");
            currentPlayer = i;
            players[currentPlayer].addHand(discards);
            System.out.println("Adding cards to player "+
                (currentPlayer+1) + players[currentPlayer]);
          }
          //If cheat is called, current bid reset to an empty bid with rank two whatever
          //the outcome
          currentBid = new Bid();
          //Discards now reset to empty
          discards = new Hand();
        }
      }
    }
    if (!cheat)
    {
      //Go to the next player
      System.out.println("No Cheat Called");

    }
    /*
    Game bug fix:
      The design of the game, as given, was floored in conjuction with
      the required implementations of the strategies.
      A scenario can arise where one player has all the twos and the other
      has none, but it's their turn to play.
      The player with all the twos can consitently correctly call cheat on the
      player, as they have to claim to of laid a 2.

      This is easily fixed by moving the turn to the player after the one who
      just got called out
     */
    currentPlayer = (currentPlayer+1) % nosPlayers;
    return true;
  }

  public int winner()
  {
    for (int i = 0; i < nosPlayers; ++i)
    {
      if (players[i].cardsLeft() == 0)
        return i;
    }
    return -1;
  }

  public void initialise()
  {
    //Create Deck of cards
    Deck d = new Deck();
    d.shuffle();
    //Deal cards to players
    Iterator<Card> it = d.iterator();
    int count = 0;
    while (it.hasNext())
    {
      players[count % nosPlayers].addCard(it.next());
      it.remove();
      ++count;
    }
    //Initialise Discards
    discards = new Hand();
    //Chose first player
    currentPlayer = 0;
    currentBid = new Bid();
    currentBid.setRank(Card.Rank.TWO);
  }

  public void playGame()
  {
    initialise();
    int c = 0;
    Scanner in = new Scanner(System.in);
    boolean finished = false;
    while (!finished)
    {
      //Play a hand
      System.out.println(" Cheat turn for player " + (currentPlayer+1));
      playTurn();
      System.out.println(" Current discards =\n" + discards);
      System.out.println(" Turn " + ++c +  " Complete. Press any key to continue or enter Q to quit>");
      String str = "";// = in.nextLine();
      if (str.equals("Q") || str.equals("q") || str.equals("quit"))
        finished = true;
      int w = winner();
      if (w >= 0)
      {
        System.out.println("The Winner is Player " + (w+1));
        finished = true;
      }
    }
  }

  public static boolean isCheat(Bid b)
  {
    for (Card c : b.getHand())
    {
      if (c.getRank() != b.getRank())
        return true;
    }
    return false;
  }
  public static void main(String[] args)
  {
    BasicCheat cheat = new BasicCheat();
    cheat.playGame();
  }
}
