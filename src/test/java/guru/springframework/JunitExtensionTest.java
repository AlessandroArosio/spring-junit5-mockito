package guru.springframework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

/**
 * @author Alessandro Arosio - 03/11/2019 17:13
 */
@ExtendWith(MockitoExtension.class)
public class JunitExtensionTest {

  @Mock
  Map<String, Object> mapMocks;

  @Test
  void testMock() {
    mapMocks.put("www", "ss");
  }
}
