import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author bing_huang
 */
@Slf4j
public class PasswordEncoderTest {

    @Test
    public void encodeTest() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = encoder.encode("secret");
        log.info(secret);
    }
}
