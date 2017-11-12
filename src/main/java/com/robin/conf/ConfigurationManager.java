package com.robin.conf;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    //创建prop对象，该对象是Configuration类的私有属性
    private static Properties prop = new Properties();

    //静态代码块
    static {
        try {
            //通过类加载器读取my.properties
            InputStream in = ConfigurationManager.class
                    .getClassLoader().getResourceAsStream("my.properties");
            prop.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 功能：通过key值查询出value值
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return prop.getProperty(key);
    }

}
