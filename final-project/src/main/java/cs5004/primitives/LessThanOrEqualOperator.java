package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.BooleanValue;
import cs5004.core.Value;

import java.util.List;

/**
 * This class implements the Primitive interface for the &lt;= operator.
 * It checks if the first integer argument is less than or equal to the second integer argument.
 */
public class LessThanOrEqualOperator extends AbstractPrimitive {

  /**
   * Applies the less than or equal to operation on the provided arguments.
   *
   * @param args A list of Values which should contain exactly two integers.
   * @return A BooleanValue object representing the result of the comparison.
   * @throws IllegalArgumentException if the number of arguments is not two or if any argument is not an IntValue.
   */
  @Override
  public Value apply(List<Value> args) {
    // Validate the arguments: exactly two are required, both must be integers
    if (args == null || args.size() != 2) {
      throw new IllegalArgumentException("Less than or equal to operator requires exactly two integer arguments");
    }

    if (!(args.get(0) instanceof IntValue) || !(args.get(1) instanceof IntValue)) {
      throw new IllegalArgumentException("Both arguments for the less than or equal to operator must be integers");
    }

    // Extract the integer values from the arguments
    int first = ((IntValue) args.get(0)).getValue();
    int second = ((IntValue) args.get(1)).getValue();

    // Perform the comparison and return the result
    return new BooleanValue(first <= second);
  }
}
