package cs5004.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AndExpressionTest {

  @Test
  public void testConstructorAndGetters() {
    Expression left = new BooleanLiteral(true);
    Expression right = new BooleanLiteral(false);
    AndExpression andExpr = new AndExpression(left, right);

    assertNotNull(andExpr, "AndExpression object should not be null");
    assertSame(left, andExpr.getLeftOperand(), "Left operand should be the same as the one passed to the constructor");
    assertSame(right, andExpr.getRightOperand(), "Right operand should be the same as the one passed to the constructor");
  }

  @Test
  public void testEquals() {
    Expression left = new BooleanLiteral(true);
    Expression right = new BooleanLiteral(false);
    AndExpression andExpr1 = new AndExpression(left, right);
    AndExpression andExpr2 = new AndExpression(left, right);
    AndExpression andExpr3 = new AndExpression(right, left);

    assertEquals(andExpr1, andExpr2, "Two AndExpressions with the same operands should be equal");
    assertNotEquals(andExpr1, andExpr3, "Two AndExpressions with different operands should not be equal");
    assertNotEquals(andExpr1, null, "AndExpression should not be equal to null");
    assertNotEquals(andExpr1, new Object(), "AndExpression should not be equal to a different type of object");
  }

  @Test
  public void testFormat() {
    Expression left = new BooleanLiteral(true);
    Expression right = new BooleanLiteral(false);
    AndExpression andExpr = new AndExpression(left, right);

    assertEquals("(true and false)", andExpr.format(), "format() should return the formatted string representation of the AndExpression");
  }

  @Test
  public void testAccept() {
    Expression left = new BooleanLiteral(true);
    Expression right = new BooleanLiteral(false);
    AndExpression andExpr = new AndExpression(left, right);
    ExpressionVisitor<String> visitor = new MockExpressionVisitor();

    assertEquals("Visited AndExpression", andExpr.accept(visitor),
        "accept() should pass the AndExpression to the visitor's visit method correctly");
  }

  // Mock ExpressionVisitor for testing purposes
  static class MockExpressionVisitor implements ExpressionVisitor<String> {
    @Override
    public String visit(IntLiteral expr) {
      return "Visited IntLiteral: " + expr.getValue();
    }

    @Override
    public String visit(BooleanLiteral expr) {
      return "Visited BooleanLiteral: " + expr.isValue();
    }

    @Override
    public String visit(VariableReference expr) {
      return "Visited VariableReference: " + expr.getVariableName();
    }

    @Override
    public String visit(FunctionCall expr) {
      return "Visited FunctionCall: " + expr.getFunctionName();
    }

    @Override
    public String visit(AndExpression expr) {
      return "Visited AndExpression";
    }

    @Override
    public String visit(OrExpression expr) {
      return "Visited OrExpression";
    }

    @Override
    public String visit(IfExpression expr) {
      return "Visited IfExpression";
    }

    @Override
    public String visit(LetExpression expr) {
      return "Visited LetExpression";
    }
  }
}
