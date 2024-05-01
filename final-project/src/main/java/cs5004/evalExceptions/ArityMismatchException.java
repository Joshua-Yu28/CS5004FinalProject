package cs5004.evalExceptions;

import java.util.Objects;

/**
 * Exception thrown to indicate an arity mismatch: a function or primitive
 * is applied to the wrong number of arguments.
 */
public class ArityMismatchException extends EvaluationException {
  private final String functionName;
  private final int expectedArity;
  private final int actualArity;

  public ArityMismatchException(
    String functionName,
    int expectedArity,
    int actualArity
  ) {
    super(
      String.format(
        "arity error: %s expects %d argument%s, got %d",
        functionName,
        expectedArity,
        expectedArity == 1 ? "" : "s",
        actualArity
      )
    );
    this.functionName = Objects.requireNonNull(functionName);
    this.expectedArity = expectedArity;
    this.actualArity = actualArity;
  }

  public String getFunctionName() {
    return functionName;
  }

  public int getExpectedArity() {
    return expectedArity;
  }

  public int getActualArity() {
    return actualArity;
  }
}
