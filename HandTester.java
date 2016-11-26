import question1.Hand;
import question1.Card;

public class HandTester
{
  private static int passed = 0;
  private static int failed = 0;

  private static String testHandSize(final Hand hand, final int expect)
  {
    StringBuilder sb = new StringBuilder();
    if (hand.size() == expect)
    {
      sb.append("    PASSED").append(System.lineSeparator());
      ++passed;
    }
    else
    {
      sb.append("    FAILED: Expected `").append(expect).append("`, got `")
      .append(hand.size()).append("`").append(System.lineSeparator());
      ++failed;
    }
    return sb.toString();
  }

  private static String testHandFlush(Hand hand, final boolean expect)
  {
    StringBuilder sb = new StringBuilder();
    if (hand.isFlush() == expect)
    {
      sb.append("    PASSED").append(System.lineSeparator());
      ++passed;
    }
    else
    {
      sb.append("    FAILED: Expected `").append(expect).append("`, got `")
      .append(hand.isFlush()).append("`").append(System.lineSeparator());
      ++failed;
    }
    return sb.toString();
  }

  private static String testHandStraight(Hand hand, final boolean expect)
  {
    StringBuilder sb = new StringBuilder();
    if (hand.isStraight() == expect)
    {
      sb.append("    PASSED").append(System.lineSeparator());
      ++passed;
    }
    else
    {
      sb.append("    FAILED: Expected `").append(expect).append("`, got `")
      .append(hand.isStraight()).append("`").append(System.lineSeparator());
      ++failed;
    }
    return sb.toString();
  }


  private static void testHand()
  {
    StringBuilder sb = new StringBuilder();
    Hand hand = new Hand();
    sb.append("TEST: Hand").append(System.lineSeparator());
    sb.append("  Hand.size()").append(System.lineSeparator());
    sb.append(testHandSize(hand, 0));
    sb.append("  Hand.addCard()").append(System.lineSeparator());
    hand.addCard(new Card(Card.Suit.SPADES, Card.Rank.ACE));
    sb.append("  Hand.size() 2").append(System.lineSeparator());
    sb.append(testHandSize(hand, 1));
    hand.addCard(new Card(Card.Suit.SPADES, Card.Rank.TWO));
    hand.addCard(new Card(Card.Suit.SPADES, Card.Rank.KING));
    sb.append("  Hand.isStraight()").append(System.lineSeparator());
    sb.append(testHandStraight(hand, true));
    hand.addCard(new Card(Card.Suit.SPADES, Card.Rank.SEVEN));
    sb.append(testHandStraight(hand, false));
    sb.append("  Hand.isFlush()").append(System.lineSeparator());
    sb.append(testHandFlush(hand, true));

    System.out.println(sb);
    System.out.println(hand);
  }


  public static void main(String[] args)
  {
    testHand();

    int tot = passed + failed;
    System.out.printf("%d tests done, passed %d, failed: %d", tot, passed, failed);
    System.out.print(System.lineSeparator());
  }
}
