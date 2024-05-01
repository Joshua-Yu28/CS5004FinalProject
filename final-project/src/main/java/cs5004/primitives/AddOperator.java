package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;

import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * This class implements the Primitive interface and represents the '+' operation
 * which performs addition between two integer values.
 */
public class AddOperator extends AbstractPrimitive {

  /**
   * Applies the addition operation on the provided arguments.
   *
   * @param args A list of Values which should contain exactly two integers.
   * @return An IntValue object representing the sum of the two integers.
   * @throws ArityMismatchException if the number of arguments is not two.
   * @throws TypeError if any argument is not an IntValue
   */
  @Override
  public Value apply(List<Value> args) {
    // Check that exactly two arguments are provided
    if (args == null || args.size() != 2) {
      throw new ArityMismatchException("AddOperator", 2, args.size());
    }

    // Check that both arguments are of type IntValue
    if (!(args.get(0) instanceof IntValue) || !(args.get(1) instanceof IntValue)) {
      throw new TypeError("Both arguments for the add operator must be integers");
    }

    // Retrieve the integer values from the arguments
    int first = ((IntValue) args.get(0)).getValue();
    int second = ((IntValue) args.get(1)).getValue();

    // Calculate the sum of the two integers
    return new IntValue(first + second);
  }
}
