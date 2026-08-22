package com.hrmcollab.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static
    {
        try{
            FileInputStream inputStream=
                    new FileInputStream("src/main/resources/config.properties");
            properties=new Properties();
            properties.load(inputStream);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key)
    {
        return properties.getProperty(key);
    }
}
