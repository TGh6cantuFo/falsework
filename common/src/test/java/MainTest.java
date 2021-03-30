import com.jfinal.kit.StrKit;
import org.junit.Test;

import java.util.UUID;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class MainTest {

    @Test
    public void StrUUId() {

        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString().length());
        System.out.println(StrKit.getRandomUUID());
        System.out.println(StrKit.getRandomUUID().length());
    }
}
