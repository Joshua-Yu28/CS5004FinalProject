package cs5004.ast;

import java.util.Objects;

/**
 * Represents a use of the <code>or</code> operator, with short-circuiting.
 */
public class OrExpression extends Expression {
  private final Expression left;
  private final Expression right;

  /**
   * Constructs a new OR expression.
   * @param left OR expression's left-hand operand
   * @param right OR expression's right-hand operand
   */
  public OrExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  public Expression getLeft() {
    return left;
  }

  public Expression getRight() {
    return right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrExpression that = (OrExpression) o;
    return Objects.equals(left, that.left) && Objects.equals(
      right, that.right);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Formats the logical OR expression as a string.
   * Similar to the AND expression formatter, this method converts the abstract syntax tree
   * representation of the OR expression into a string in infix notation, with the '||' operator
   * between the formatted left and right operands. The entire expression is enclosed in parentheses
   * to maintain the correct order of operations when used in a larger expression context.
   *
   * @return A string representing the formatted OR expression in infix notation.
   */
  @Override
  public String format() {
    return String.format("(%s or %s)", left.format(), right.format());
  }
}
