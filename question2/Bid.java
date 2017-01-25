package question2;

import question1.Card;
import question1.Hand;

public class Bid
{
  public Bid()
  {
    h = new Hand();
    r = Card.Rank.ACE;
  }
  public Bid(Hand hand, Card.Rank bidRank)
  {
    h = hand;
    r = bidRank;
  }

  public void setHand(Hand hand) { h = hand; }
  public void setRank(Card.Rank rank){ r = rank; }

  public Hand getHand() { return h; }
  public int getCount(){ return h.size(); }
  public Card.Rank getRank() { return r; }

  @Override
  public String toString()
  {
    return h.size() + " x " + r;
  }

  private Hand h;
  private Card.Rank r;
}
