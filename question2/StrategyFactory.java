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
    HUMAN,
    THINKER,
    MY
  }

  public StrategyFactory()
  {
    rg = new Random();
  }

  public StrategyType choose()
  {
    switch (rg.nextInt(4))
    {
      case 0:
        return StrategyType.BASIC;
      case 1:
        //return StrategyType.HUMAN;
      case 2:
        return StrategyType.THINKER;
      case 3:
        return StrategyType.MY;
    }
    return null;
  }

  public static Strategy produce(StrategyType type)
  {
    switch (type)
    {
      case BASIC:
        return new BasicStrategy();
      case HUMAN:
        return new HumanStrategy();
      case THINKER:
        return new ThinkerStrategy();
      case MY:
        return new MyStrategy();
    }
    return null;
  }

  private Random rg;
}
