package question2;

import java.util.Scanner;

import question1.Card;
import question1.Hand;

import question2.Strategy;

public class HumanStrategy implements Strategy
{
  public HumanStrategy()
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
    // human don't need this, so left as stub
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
    Card.Rank r = Card.Rank.TWO;
    System.out.println("You have:");
    for (int z = 0; z < 13; ++z)
    {
      int c = h.countRank(r);
      if (c != 0)
        System.out.print("  " + c + "x" + r + " ");
      r = r.getNext();
    }
    System.out.print("\n");
    r = null;
    Scanner in = new Scanner(System.in);
    while (true)
    {
      System.out.print("What rank would you like to play?\n> ");
      String ans = in.nextLine().toLowerCase();

      r = Card.Rank.fromString(ans);
      if (r == null)
      {
        System.out.println("Invalid card");
        continue;
      }
      if (h.countRank(r) > 0)
        break;
      System.out.println("You don't have any of this card");
    }
    int num = 0;
    int count = h.countRank(r);
    while (num == 0)
    {
      System.out.print("How many would you like to play? [1.." + count + "]\n> ");
      try
      {
        num = Integer.valueOf(in.nextLine());
        if (num > count)
        {
          System.out.println("You don't have that many cards.");
          num = 0;
        }
        if (num <= 0)
        {
          System.out.println("You can't play no cards.");
          num = 0;
        }
      }
      catch (Exception e)
      {
      }
    }
    for (Card c : h)
    {
      if (num > 0 && c.getRank().equals(r))
      {
        play.add(c);
        --num;
      }
    }
    h.remove(play);
    if (r != b.getRank() && r != b.getRank().getNext())
    {
      Card.Rank c = null;
      while (c == null)
      {
        System.out.print("This is a cheat, what would you like to claim it is?\n> ");
        String ans = in.nextLine().toLowerCase();
        c = Card.Rank.fromString(ans);
      }
      r = c;
    }
    return new Bid(play, r);
  }

 /**
  *
  * @param b the current bid
  * @return true if this player is going to call cheat  on the last play b
  */
  public boolean callCheat(Hand h, Bid b, int player)
  {
    Scanner in = new Scanner(System.in);
    while (true)
    {
      System.out.print("Would you like to call cheat? [y/n]\n> ");
      String ans = in.nextLine().toLowerCase();
      if (ans.equals("y") || ans.equals("yes"))
        return true;
      else if (ans.equals("n") || ans.equals("no"))
        return false;
      else
        System.out.println("Sorry, didn't understand that");
    }
  }

  public void broadcastCheat(int player, int caller, boolean correct)
  {
    // nothing to do
  }
}
