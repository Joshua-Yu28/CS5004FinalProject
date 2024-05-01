package cs5004.primitives;

import cs5004.core.IntValue;
import cs5004.core.BooleanValue;
import cs5004.core.Value;
import cs5004.evalExceptions.ArityMismatchException;
import cs5004.core.TypeError;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class GreaterThanOperatorTest {

  private final GreaterThanOperator gtOp = new GreaterThanOperator();

  @Test
  public void testApplySuccessful() {
    IntValue first = new IntValue(10);
    IntValue second = new IntValue(5);
    Value result = gtOp.apply(Arrays.asList(first, second));

    assertNotNull(result, "Result should not be null");
    assertTrue(result instanceof BooleanValue, "Result should be an instance of BooleanValue");
    assertTrue(((BooleanValue) result).getValue(), "10 > 5 should return true");

    IntValue third = new IntValue(10);
    Value resultFalse = gtOp.apply(Arrays.asList(second, third));
    assertFalse(((BooleanValue) resultFalse).getValue(), "5 > 10 should return false");
  }

  @Test
  public void testApplyWithNonIntValues() {
    IntValue first = new IntValue(10);
    Value nonIntValue = new BooleanValue(true); // Incorrect type

    Exception exception = assertThrows(TypeError.class,
        () -> gtOp.apply(Arrays.asList(first, nonIntValue)),
        "Using non-IntValue should throw TypeError");
    assertEquals("Both arguments for the greater than operator must be integers", exception.getMessage());
  }

  @Test
  public void testApplyIncorrectNumberOfArgs() {
    IntValue first = new IntValue(10);

    Exception exception = assertThrows(ArityMismatchException.class,
        () -> gtOp.apply(Collections.singletonList(first)),
        "Incorrect number of arguments should throw ArityMismatchException");
    assertTrue(exception.getMessage().contains("Greater"), "Exception message should include the operation name");
    assertTrue(exception.getMessage().contains("2"), "Exception message should include the expected number of arguments '2'");
    assertTrue(exception.getMessage().contains("1"), "Exception message should include the received number of arguments '1'");
  }
}

