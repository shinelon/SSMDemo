package redis.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class FileUtil {
    public static Properties loadProps(String file) throws IOException {
        InputStream stream = FileUtil.class.getResourceAsStream(file);
        Properties p = null;
        BufferedReader r = null;
        try {
            r = new BufferedReader(new InputStreamReader(stream));
            p = new Properties();
            p.load(r);
        } finally {
            stream.close();
            r.close();
        }
        return p;
    }

}
