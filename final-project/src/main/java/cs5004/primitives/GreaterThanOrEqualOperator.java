package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.BooleanValue;
import cs5004.core.TypeError;
import cs5004.core.Value;

import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * This class implements the Primitive interface and represents the '>=' operation
 * which checks if the first integer argument is greater than or equal to the second integer argument.
 */
public class GreaterThanOrEqualOperator extends AbstractPrimitive {

  /**
   * Applies the greater than or equal to operation on the provided arguments.
   *
   * @param args A list of Values which should contain exactly two integers.
   * @return A BooleanValue object representing the result of the comparison.
   * @throws ArityMismatchException if the number of arguments is not two.
   * @throws TypeError if any argument is not an IntValue.
   */
  @Override
  public Value apply(List<Value> args) {
    // Check that exactly two arguments are provided
    if (args == null || args.size() != 2) {
      throw new ArityMismatchException("GreaterEqual", 2, args.size());
    }

    // Check that both arguments are of type IntValue
    if (!(args.get(0) instanceof IntValue) || !(args.get(1) instanceof IntValue)) {
      throw new TypeError("Both arguments for the greater than operator must be integers");
    }

    // Retrieve the integer values from the arguments
    int first = ((IntValue) args.get(0)).getValue();
    int second = ((IntValue) args.get(1)).getValue();

    // Perform the comparison and return the result
    return new BooleanValue(first >= second);
  }
}
