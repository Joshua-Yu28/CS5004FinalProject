package cs5004.primitives;

import cs5004.core.BooleanValue;
import cs5004.core.IntValue;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import cs5004.core.TypeError;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class DivideOperatorTest {

  private final DivideOperator divOp = new DivideOperator();

  @Test
  public void testApplySuccessful() {
    IntValue numerator = new IntValue(10);
    IntValue denominator = new IntValue(5);
    Value result = divOp.apply(Arrays.asList(numerator, denominator));

    assertNotNull(result, "Result should not be null");
    assertTrue(result instanceof IntValue, "Result should be an instance of IntValue");
    assertEquals(2, ((IntValue) result).getValue(), "10 / 5 should equal 2");
  }

  @Test
  public void testDivisionByZero() {
    IntValue numerator = new IntValue(10);
    IntValue denominator = new IntValue(0);

    Exception exception = assertThrows(ArithmeticException.class,
        () -> divOp.apply(Arrays.asList(numerator, denominator)),
        "Division by zero should throw ArithmeticException");
    assertEquals("Division by zero is not allowed", exception.getMessage());
  }

  @Test
  public void testApplyWithNonIntValues() {
    IntValue numerator = new IntValue(10);
    Value nonIntValue = new BooleanValue(true); // Incorrect type

    Exception exception = assertThrows(TypeError.class,
        () -> divOp.apply(Arrays.asList(numerator, nonIntValue)),
        "Using non-IntValue should throw TypeError");
    assertEquals("Both arguments for the division operator must be integers", exception.getMessage());
  }

  @Test
  public void testApplyIncorrectNumberOfArgs() {
    IntValue numerator = new IntValue(10);

    Exception exception = assertThrows(ArityMismatchException.class,
        () -> divOp.apply(Collections.singletonList(numerator)),
        "Incorrect number of arguments should throw ArityMismatchException");
    assertTrue(exception.getMessage().contains("DivideOperator"), "Exception message should include the operation name");
    assertTrue(exception.getMessage().contains("2"), "Exception message should include the expected number of arguments '2'");
    assertTrue(exception.getMessage().contains("1"), "Exception message should include the received number of arguments '1'");
  }
}
