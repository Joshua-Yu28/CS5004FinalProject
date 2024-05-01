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

public class GreaterThanOrEqualOperatorTest {

  private final GreaterThanOrEqualOperator geOp = new GreaterThanOrEqualOperator();

  @Test
  public void testApplySuccessful() {
    IntValue first = new IntValue(10);
    IntValue second = new IntValue(5);
    Value result = geOp.apply(Arrays.asList(first, second));

    assertNotNull(result, "Result should not be null");
    assertTrue(result instanceof BooleanValue, "Result should be an instance of BooleanValue");
    assertTrue(((BooleanValue) result).getValue(), "10 >= 5 should return true");

    IntValue third = new IntValue(5);
    Value resultFalse = geOp.apply(Arrays.asList(third, first));
    assertFalse(((BooleanValue) resultFalse).getValue(), "5 >= 10 should return false");
  }

  @Test
  public void testApplyWithNonIntValue() {
    IntValue first = new IntValue(10);
    Value second = new BooleanValue(false);

    Exception exception = assertThrows(TypeError.class,
        () -> geOp.apply(Arrays.asList(first, second)),
        "Using non-IntValue should throw TypeError");
    assertEquals("Both arguments for the greater than operator must be integers", exception.getMessage());
  }

  @Test
  public void testApplyIncorrectNumberOfArgs() {
    IntValue first = new IntValue(10);

    Exception exception = assertThrows(ArityMismatchException.class,
        () -> geOp.apply(Collections.singletonList(first)),
        "Incorrect number of arguments should throw ArityMismatchException");
    assertTrue(exception.getMessage().contains("GreaterEqual"), "Exception message should include the operation name");
    assertTrue(exception.getMessage().contains("2"), "Exception message should include the expected number of arguments '2'");
    assertTrue(exception.getMessage().contains("1"), "Exception message should include the received number of arguments '1'");
  }
}
