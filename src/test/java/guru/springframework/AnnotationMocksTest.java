package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

/**
 * @author Alessandro Arosio - 03/11/2019 17:10
 */
public class AnnotationMocksTest {

  @Mock
  Map<String, Object> mapMocks;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testMock() {
    mapMocks.put("www", "ss");
  }

}
