package cs5004.ast;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents an IF expression.
 */
public class IfExpression extends Expression {
  private final Expression condition;
  private final Expression consequent;
  private final Expression alternative;

  /**
   * Constructs an <code>if</code> expression.
   * @param condition the condition expression
   * @param consequent the expression to be evaluated when condition is true
   * @param alternative the expression to be evaluated when condition is false
   */
  public IfExpression(
    Expression condition,
    Expression consequent,
    Expression alternative
  ) {
    this.condition = condition;
    this.consequent = consequent;
    this.alternative = alternative;
  }

  public Expression getCondition() {
    return condition;
  }

  public Expression getConsequent() {
    return consequent;
  }

  public Expression getAlternative() {
    return alternative;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IfExpression that = (IfExpression) o;
    return Objects.equals(condition, that.condition)
      && Objects.equals(consequent, that.consequent)
      && Objects.equals(alternative, that.alternative);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Generates a formatted string representing a conditional expression in a syntax similar to the ternary operator in Java.
   * It encapsulates the condition, the consequent expression if the condition is true, and the alternative expression if the condition is false.
   *
   * @return a string formatted as "(condition ? consequent : alternative)" where 'condition', 'consequent', and 'alternative'
   * are the formatted strings of the respective parts of the conditional expression.
   */
  @Override
  public String format() {
    return String.format("(%s ? %s : %s)", condition.format(), consequent.format(), alternative.format());
  }
}
