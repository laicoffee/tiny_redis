import com.github.awstan.cache.api.ICache;
import com.github.awstan.cache.core.bs.CacheBs;
import com.github.awstan.cache.core.core.Cache;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Author pw7563
 * @Date 2024/7/8 14:50
 * usage
 */
public class CacheTest {

    @Test
    public void test() {
        ICache<String, String> build = new CacheBs<String,String>().build();

        build.put("key1", "value1");
        build.put("key2", "value2");
        build.put("key3", "value3");
        build.put("key4", "value4");

    }

    @Test
    public void expireTest() throws InterruptedException {
        ICache<String, String> build = new CacheBs<String,String>().build();

        build.put("key1", "value1");
        build.put("key2", "value2");

        build.expire("key1",100l);


        System.out.println(build.keySet());

        Thread.sleep(1000l);

        System.out.println(build.keySet());
    }


}
