package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * This class implements the Primitive interface and represents the subtraction ('-') operation
 * which subtracts the second integer from the first.
 */
public class SubtractOperator extends AbstractPrimitive {

  /**
   * Applies the subtraction operation on the provided arguments.
   *
   * @param args A list of Values which should contain exactly two integers.
   * @return An IntValue object representing the result of the subtraction.
   * @throws ArityMismatchException if the number of arguments is not two.
   * @throws TypeError if any argument is not an IntValue.
   */
  @Override
  public Value apply(List<Value> args) {
    // Check that exactly two arguments are provided
    if (args == null || args.size() != 2) {
      throw new ArityMismatchException("Subtract", 2, args.size());
    }

    // Check that both arguments are of type IntValue
    if (!(args.get(0) instanceof IntValue) || !(args.get(1) instanceof IntValue)) {
      throw new TypeError("Both arguments for the subtract operator must be integers");
    }

    // Retrieve the integer values from the arguments
    int first = ((IntValue) args.get(0)).getValue();
    int second = ((IntValue) args.get(1)).getValue();

    // Perform the subtraction and return the result
    return new IntValue(first - second);
  }
}
