package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.BooleanValue;
import cs5004.core.Value;

import java.util.List;

/**
 * This class implements the Primitive interface for the &lt; operation
 * which checks if the first integer argument is less than the second integer argument.
 */
public class LessThanOperator extends AbstractPrimitive {

  /**
   * Applies the less than operation on the provided arguments.
   *
   * @param args A list of Values which should contain exactly two integers.
   * @return A BooleanValue object representing whether the first argument is less than the second argument.
   * @throws IllegalArgumentException if the number of arguments is not two or if any argument is not an IntValue.
   */
  @Override
  public Value apply(List<Value> args) {
    // Ensure that exactly two arguments are provided
    if (args == null || args.size() != 2) {
      throw new IllegalArgumentException("Less than operator requires exactly two integer arguments");
    }

    // Ensure both arguments are IntValues
    if (!(args.get(0) instanceof IntValue) || !(args.get(1) instanceof IntValue)) {
      throw new IllegalArgumentException("Both arguments for the less than operator must be integers");
    }

    // Retrieve the integer values from the arguments
    int first = ((IntValue) args.get(0)).getValue();
    int second = ((IntValue) args.get(1)).getValue();

    // Perform the comparison and return the result
    return new BooleanValue(first < second);
  }
}
