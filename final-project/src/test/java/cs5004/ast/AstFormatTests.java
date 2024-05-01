package cs5004.ast;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

/**
 * Tests for the formatting methods of AST nodes in the cs5004.ast package.
 */
public class AstFormatTests {

  private IntLiteral intLiteral;
  private BooleanLiteral booleanLiteral;
  private FunctionCall functionCallSimple;
  private FunctionCall functionCallComplex;
  private IfExpression ifExpression;
  private LetExpression letExpression;

  @BeforeEach
  public void setUp() {
    intLiteral = new IntLiteral(42);
    booleanLiteral = new BooleanLiteral(true);
    functionCallSimple = new FunctionCall("increment", List.of(new VariableReference("x")));
    functionCallComplex = new FunctionCall("+", List.of(new IntLiteral(3), new VariableReference("y")));

    FunctionCall condition = new FunctionCall(">", List.of(new VariableReference("x"), new IntLiteral(0)));

    ifExpression = new IfExpression(
        condition,
        new FunctionCall("sqrt", List.of(new VariableReference("x"))),
        new IntLiteral(0)
    );
    letExpression = new LetExpression(
        "x",
        new FunctionCall("square", List.of(new VariableReference("y"))),
        new FunctionCall("*", List.of(new VariableReference("x"), new IntLiteral(2)))
    );
  }

  @Test
  public void testIntLiteralFormat() {
    assertEquals("42", intLiteral.format());
  }

  @Test
  public void testBooleanLiteralFormat() {
    assertEquals("true", booleanLiteral.format());
  }

  @Test
  public void testFunctionCallSimpleFormat() {
    assertEquals("increment(x)", functionCallSimple.format());
  }

  @Test
  public void testFunctionCallComplexFormat() {
    assertEquals("(3 + y)", functionCallComplex.format());
  }

  @Test
  public void testIfExpressionFormat() {
    assertEquals("((x > 0) ? sqrt(x) : 0)", ifExpression.format());
  }

  @Test
  public void testLetExpressionFormat() {
    assertEquals("let x = square(y) in (x * 2)", letExpression.format());
  }
}


