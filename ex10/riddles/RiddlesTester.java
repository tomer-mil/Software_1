package riddles;

import enumRiddles.TesterUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RiddlesTester {
  private static final String EXPECTED = String.format(
      "success!%n"
    + "success!%n"
    + "success!%n"
    + "success!%n"
    + "success!%n"
    + "success!%n"
    + "success!%n");

  @SuppressWarnings("unchecked")
  @Test
  public void testTeachingAssistantsTest() {
    String output = TesterUtil.testOutput(Riddle::main, new String[]{ }, "Riddle.main");
    assertEquals(EXPECTED, output);
  }
}
