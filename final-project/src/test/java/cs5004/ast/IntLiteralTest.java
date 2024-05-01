package cs5004.ast;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntLiteralTest {

  @Test
  public void testConstructorAndGetValue() {
    IntLiteral literal = new IntLiteral(42);
    assertNotNull(literal, "IntLiteral object should not be null");
    assertEquals(42, literal.getValue(), "getValue() should return the integer value set at construction");
  }

  @Test
  public void testEquals() {
    IntLiteral literal1 = new IntLiteral(42);
    IntLiteral literal2 = new IntLiteral(42);
    IntLiteral literal3 = new IntLiteral(24);

    assertEquals(literal1, literal2, "Two IntLiterals with the same value should be equal");
    assertNotEquals(literal1, literal3, "Two IntLiterals with different values should not be equal");
    assertNotEquals(literal1, null, "IntLiteral should not be equal to null");
    assertNotEquals(literal1, new Object(), "IntLiteral should not be equal to a different type of object");
  }

  @Test
  public void testFormat() {
    IntLiteral literal = new IntLiteral(42);
    assertEquals("42", literal.format(), "format() should return the string representation of the value");
  }

  @Test
  public void testAccept() {
    IntLiteral literal = new IntLiteral(42);
    ExpressionVisitor<String> visitor = new MockExpressionVisitor();
    assertEquals("Visited IntLiteral: 42", literal.accept(visitor),
        "accept() should pass the IntLiteral to the visitor's visit method correctly");
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

