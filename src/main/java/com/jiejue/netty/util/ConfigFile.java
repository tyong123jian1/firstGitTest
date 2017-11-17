package com.jiejue.netty.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by jianbin on 2017-11-08.
 */
public class ConfigFile {
    private static Properties prop = new Properties();

    static {
        InputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(ConfigFile.class.getClassLoader().getResource("system.property").getFile()));
            prop.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String getValue(String key) {
        return prop.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        String value = prop.getProperty(key);
        return Boolean.getBoolean(value);
    }

    public static Integer getInteger(String key) {
        return Integer.valueOf(prop.getProperty(key));
    }

    public static Double getDouble(String key) {
        return Double.valueOf(prop.getProperty(key));
    }
}
