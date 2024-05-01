package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.TypeError;
import cs5004.core.Value;

import cs5004.evalExceptions.ArityMismatchException;
import java.util.List;

/**
 * This class implements the Primitive interface and represents the '/' operation
 * which performs integer division between two integer values.
 */
public class DivideOperator extends AbstractPrimitive {

  /**
   * Applies the division operation on the provided arguments.
   *
   * @param args A list of Values which should contain exactly two integers.
   * @return An IntValue object representing the quotient of the division.
   * @throws ArityMismatchException if the number of arguments is not two.
   * @throws TypeError if the arguments are not integers
   * @throws ArithmeticException if the second integer (divisor) is zero.
   */
  @Override
  public Value apply(List<Value> args) {
    // Check that exactly two arguments are provided
    if (args == null || args.size() != 2) {
      throw new ArityMismatchException("DivideOperator", 2, args.size());
    }

    // Check that both arguments are of type IntValue
    if (!(args.get(0) instanceof IntValue) || !(args.get(1) instanceof IntValue)) {
      throw new TypeError("Both arguments for the division operator must be integers");
    }

    // Retrieve the integer values from the arguments
    int numerator = ((IntValue) args.get(0)).getValue();
    int denominator = ((IntValue) args.get(1)).getValue();

    // Check for division by zero
    if (denominator == 0) {
      throw new ArithmeticException("Division by zero is not allowed");
    }

    // Perform the division and return the result
    return new IntValue(numerator / denominator);
  }
}
