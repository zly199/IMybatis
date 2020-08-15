package cn.elite.io;

import java.io.InputStream;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 14:55
 * @Version: 1.0
 */
public class Resources {
    public static InputStream getResourceAsStream(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
