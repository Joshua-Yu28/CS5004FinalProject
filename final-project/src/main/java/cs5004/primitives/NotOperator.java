package cs5004.primitives;

import cs5004.core.BooleanValue;
import cs5004.core.Value;
import java.util.List;

/**
 * Implements the NOT operator.
 */
public class NotOperator extends AbstractPrimitive {

  @Override
  public Value apply(List<Value> arguments) {
    arityCheck("not", 1, arguments.size());
    return new BooleanValue(!(arguments.get(0).asBoolean()));
  }
}
