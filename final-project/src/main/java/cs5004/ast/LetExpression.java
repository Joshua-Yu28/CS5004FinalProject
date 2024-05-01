package cs5004.ast;

import java.util.Objects;

/**
 * Represents an expression that introduces a new variable definition.
 */
public class LetExpression extends Expression {
  private final String varName;
  private final Expression rhs;
  private final Expression body;
  /**
   * Constructs a new <code>let</code> expression.
   * @param varName the name of the variable being defined
   * @param rhs the expression giving the value of the variable
   * @param body the expression within which the variable is defined
   */
  public LetExpression(String varName, Expression rhs, Expression body) {
    this.varName = varName;
    this.rhs = rhs;
    this.body = body;
  }

  public String getVarName() {
    return varName;
  }

  public Expression getRhs() {
    return rhs;
  }

  public Expression getBody() {
    return body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LetExpression that = (LetExpression) o;
    return Objects.equals(varName, that.varName)
      && Objects.equals(rhs, that.rhs) && Objects.equals(body,
      that.body);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visit(this);
  }

  /**
   * Formats the let-expression, following the pattern of a variable binding
   * in a let-in construct. The variable name, right-hand side (RHS), and body
   * of the expression are formatted into a let-in pattern: let varName = rhs in body.
   *
   * @return a formatted string representation of the let-expression.
   */
  @Override
  public String format() {
    return String.format("let %s = %s in %s", varName, rhs.format(), body.format());
  }
}
