package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.BooleanValue;
import cs5004.core.TypeError;
import cs5004.core.Value;

import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * This class implements the Primitive interface and represents the '!=' operation
 * which checks for inequality between two integer values.
 */
public class NotEqualsOperator extends AbstractPrimitive {

  /**
   * Applies the inequality operation on the provided arguments.
   *
   * @param args A list of Values which should contain exactly two integers.
   * @return A BooleanValue object representing the result of the inequality check.
   * @throws ArityMismatchException if the number of arguments is not two.
   * @throws TypeError if any argument is not an IntValue.
   */
  @Override
  public Value apply(List<Value> args) {
    // Validate the number of arguments
    if (args == null || args.size() != 2) {
      throw new ArityMismatchException("NotEquals", 2, args.size());
    }

    // Validate that both arguments are IntValues
    if (!(args.get(0) instanceof IntValue) || !(args.get(1) instanceof IntValue)) {
      throw new TypeError("Both arguments for the not equals operator must be integers");
    }

    // Extract the integer values from the arguments
    int first = ((IntValue) args.get(0)).getValue();
    int second = ((IntValue) args.get(1)).getValue();

    // Perform the inequality check and return the result
    return new BooleanValue(first != second);
  }
}
