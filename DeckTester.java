import question1.Deck;
import question1.Card;

public class DeckTester
{
  private static int passed = 0;
  private static int failed = 0;

  private static String testDeckSize(final Deck deck, final int expect)
  {
    StringBuilder sb = new StringBuilder();
    if (deck.size() == expect)
    {
      sb.append("    PASSED").append(System.lineSeparator());
      ++passed;
    }
    else
    {
      sb.append("    FAILED: Expected `").append(expect).append("`, got `")
      .append(deck.size()).append("`").append(System.lineSeparator());
      ++failed;
    }
    return sb.toString();
  }

  private static String testDeckDeal(Deck deck, final Card expect)
  {
    StringBuilder sb = new StringBuilder();
    Card delt = deck.deal();
    if (delt.equals(expect))
    {
      sb.append("    PASSED").append(System.lineSeparator());
      ++passed;
    }
    else
    {
      sb.append("    FAILED: Expected `").append(expect).append("`, got `")
      .append(delt).append("`").append(System.lineSeparator());
      ++failed;
    }
    return sb.toString();
  }


  private static void testDeck()
  {
    StringBuilder sb = new StringBuilder();
    Deck deck = new Deck();
    sb.append("TEST: Deck").append(System.lineSeparator());
    sb.append("  Deck.size()").append(System.lineSeparator());
    sb.append(testDeckSize(deck, 52));
    sb.append("  Deck.deal()").append(System.lineSeparator());
    sb.append(testDeckDeal(deck, new Card(Card.Suit.SPADES, Card.Rank.ACE)));
    sb.append("  Deck.size() 2").append(System.lineSeparator());
    sb.append(testDeckSize(deck, 51));
    System.out.println(deck);
    for (Card c : deck)
      System.out.print(c + " ");
    System.out.print(System.lineSeparator());
    deck.shuffle();
    System.out.println(deck);
    System.out.println(sb);
  }


  public static void main(String[] args)
  {
    testDeck();

    int tot = passed + failed;
    System.out.printf("%d tests done, passed %d, failed: %d", tot, passed, failed);
    System.out.print(System.lineSeparator());
  }
}
