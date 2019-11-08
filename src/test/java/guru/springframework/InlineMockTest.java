package guru.springframework;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author Alessandro Arosio - 03/11/2019 17:07
 */
public class InlineMockTest {

  @Test
  void testInlineMock() {
    Map mapMock = mock(Map.class);

    assertEquals(mapMock.size(), 0);
  }
}
