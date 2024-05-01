package cs5004.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrExpressionTest {

  @Test
  public void testConstructorAndGetters() {
    Expression left = new BooleanLiteral(false);
    Expression right = new BooleanLiteral(true);
    OrExpression orExpr = new OrExpression(left, right);

    assertNotNull(orExpr, "OrExpression object should not be null");
    assertSame(left, orExpr.getLeft(), "Left operand should be the same as the one passed to the constructor");
    assertSame(right, orExpr.getRight(), "Right operand should be the same as the one passed to the constructor");
  }

  @Test
  public void testEquals() {
    Expression left = new BooleanLiteral(false);
    Expression right = new BooleanLiteral(true);
    OrExpression orExpr1 = new OrExpression(left, right);
    OrExpression orExpr2 = new OrExpression(left, right);
    OrExpression orExpr3 = new OrExpression(right, left); // Switch the operands

    assertEquals(orExpr1, orExpr2, "Two OrExpressions with the same operands should be equal");
    assertNotEquals(orExpr1, orExpr3, "Two OrExpressions with their operands switched should not be equal");
    assertNotEquals(orExpr1, null, "OrExpression should not be equal to null");
    assertNotEquals(orExpr1, new Object(), "OrExpression should not be equal to a different type of object");
  }

  @Test
  public void testFormat() {
    Expression left = new BooleanLiteral(false);
    Expression right = new BooleanLiteral(true);
    OrExpression orExpr = new OrExpression(left, right);

    assertEquals("(false or true)", orExpr.format(), "format() should return the correctly formatted string representation of the OrExpression");
  }

  @Test
  public void testAccept() {
    Expression left = new BooleanLiteral(false);
    Expression right = new BooleanLiteral(true);
    OrExpression orExpr = new OrExpression(left, right);
    ExpressionVisitor<String> visitor = new MockExpressionVisitor();

    assertEquals("Visited OrExpression", orExpr.accept(visitor),
        "accept() should pass the OrExpression to the visitor's visit method correctly");
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

