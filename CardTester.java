import question1.Card;

public class CardTester
{
  private static int passed = 0;
  private static int failed = 0;

  private static String testSuitToString(final Card.Suit s, final String expect)
  {
    StringBuilder sb = new StringBuilder();
    if (s.toString().equals(expect))
    {
      sb.append("    PASSED").append(System.lineSeparator());
      ++passed;
    }
    else
    {
      sb.append("    FAILED: Expected `").append(expect).append("`, got `")
      .append(s.toString()).append("`").append(System.lineSeparator());
      ++failed;
    }
    return sb.toString();
  }

  private static void testSuit()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("TEST: Suits").append(System.lineSeparator());
    sb.append("  Card.Suit.toString()").append(System.lineSeparator());
    sb.append(testSuitToString(Card.Suit.CLUBS, "C"));
    sb.append(testSuitToString(Card.Suit.DIAMONDS, "D"));
    sb.append(testSuitToString(Card.Suit.HEARTS, "H"));
    sb.append(testSuitToString(Card.Suit.SPADES, "S"));
    System.out.println(sb);
  }

  private static String testRankToString(final Card.Rank r, final String expect)
  {
    StringBuilder sb = new StringBuilder();
    if (r.toString().equals(expect))
    {
      sb.append("    PASSED").append(System.lineSeparator());
      ++passed;
    }
    else
    {
      sb.append("    FAILED: Expected `").append(expect).append("`, got `")
      .append(r.toString()).append("`").append(System.lineSeparator());
      ++failed;
    }
    return sb.toString();
  }

  private static String testRankGetNext(final Card.Rank r, final Card.Rank expect)
  {
    StringBuilder sb = new StringBuilder();
    if (r.getNext() == expect)
    {
      sb.append("    PASSED").append(System.lineSeparator());
      ++passed;
    }
    else
    {
      sb.append("    FAILED: Expected `").append(expect).append("`, got `")
      .append(r.toString()).append("`").append(System.lineSeparator());
      ++failed;
    }
    return sb.toString();
  }


  private static void testRank()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("TEST: Ranks").append(System.lineSeparator());
    sb.append("  Card.Rank.toString()").append(System.lineSeparator());
    sb.append(testRankToString(Card.Rank.TWO,   "2"));
    sb.append(testRankToString(Card.Rank.THREE, "3"));
    sb.append(testRankToString(Card.Rank.FOUR,  "4"));
    sb.append(testRankToString(Card.Rank.FIVE,  "5"));
    sb.append(testRankToString(Card.Rank.SIX,   "6"));
    sb.append(testRankToString(Card.Rank.SEVEN, "7"));
    sb.append(testRankToString(Card.Rank.EIGHT, "8"));
    sb.append(testRankToString(Card.Rank.NINE,  "9"));
    sb.append(testRankToString(Card.Rank.TEN,   "10"));
    sb.append(testRankToString(Card.Rank.JACK,  "J"));
    sb.append(testRankToString(Card.Rank.QUEEN, "Q"));
    sb.append(testRankToString(Card.Rank.KING,  "K"));
    sb.append(testRankToString(Card.Rank.ACE,   "A"));

    // note, not testing all of getNext, since it's trivial implementation
    // testing this is enough
    sb.append("  Card.Rank.getNext()").append(System.lineSeparator());
    sb.append(testRankGetNext(Card.Rank.TWO, Card.Rank.THREE));
    sb.append(testRankGetNext(Card.Rank.TEN, Card.Rank.JACK));
    sb.append(testRankGetNext(Card.Rank.JACK, Card.Rank.QUEEN));
    sb.append(testRankGetNext(Card.Rank.KING, Card.Rank.ACE));
    sb.append(testRankGetNext(Card.Rank.ACE, Card.Rank.TWO));
    System.out.println(sb.toString());
  }


  public static void main(String[] args)
  {
    testSuit();
    testRank();

    int tot = passed + failed;
    System.out.printf("%d tests done, passed %d, failed: %d", tot, passed, failed);
    System.out.print(System.lineSeparator());
  }
}
