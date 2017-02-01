package question2;

import question2.Strategy;
import question2.BasicStrategy;
import question2.HumanStrategy;
import question2.ThinkerStrategy;
import question2.MyStrategy;

import java.util.Random;

public class StrategyFactory
{
  public enum StrategyType
  {
    BASIC,
    THINKER,
    MY,
    HUMAN
  }

  public StrategyFactory()
  {
    rg = new Random();
  }

  public StrategyType choose(boolean human)
  {
    switch (rg.nextInt(human ? 4 : 3))
    {
      case 0:
        return StrategyType.BASIC;
      case 1:
        return StrategyType.THINKER;
      case 2:
        return StrategyType.MY;
      case 3:
        return StrategyType.HUMAN;
    }
    return null;
  }

  public static Strategy produce(StrategyType type)
  {
    switch (type)
    {
      case BASIC:
        return new BasicStrategy();
      case THINKER:
        return new ThinkerStrategy();
      case MY:
        return new MyStrategy();
      case HUMAN:
        return new HumanStrategy();
    }
    return null;
  }

  private Random rg;
}
