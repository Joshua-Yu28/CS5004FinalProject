package cs5004.ast;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a function-call expression, such as <code>f(3, y, g(x))</code>
 */
public class FunctionCall extends Expression {
  public final String functionName;
  public final List<Expression> arguments;

  /**
   * Constructs a function-call expression
   * @param functionName the name of the function
   * @param arguments the argument expressions, in order from left to right.
   *                  Must not be null; can be empty.
   */
  public FunctionCall(String functionName, List<Expression> arguments) {
    this.functionName = functionName;
    this.arguments = arguments;
  }

  public String getFunctionName() {
    return functionName;
  }

  public List<Expression> getArguments() {
    return arguments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FunctionCall that = (FunctionCall) o;
    return Objects.equals(functionName, that.functionName)
      && Objects.equals(arguments, that.arguments);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }


  /**
   * Formats this function call or operator expression as a string.
   * If the function name is an operator and there are exactly two arguments,
   * it formats the expression using infix notation, wrapping the entire expression in parentheses.
   * For example, if the function is "+" and the arguments are "1" and "2",
   * it will return the string "(1 + 2)".
   * If the function is not an operator, or does not have two arguments,
   * it formats the expression as a regular function call with arguments separated by commas.
   * For example, "f(1, 2, 3)".
   *
   * @return A string representation of the function call or operator expression.
   */
  @Override
  public String format() {
    if (arguments.size() == 2 && isOperator(functionName)) {
      return String.format("(%s %s %s)", arguments.get(0).format(), functionName, arguments.get(1).format());
    } else {
      return functionName + "(" + arguments.stream().map(Expression::format).collect(Collectors.joining(", ")) + ")";
    }
  }

  /**
   * Determines if a given function name corresponds to an operator.
   * This method checks if the function name is one of the predefined operators
   * like "+", "-", "*", "/", "%", "==", "!=", "<", ">", "<=", or ">=".
   *
   * @param functionName The name of the function to check.
   * @return true if the functionName is an operator, false otherwise.
   */
  private boolean isOperator(String functionName) {
    return "+-*/%==!=<><=>=".contains(functionName);
  }

}
