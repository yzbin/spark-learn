package com.robin.test;

import com.robin.conf.ConfigurationManager;

public class ConfigurationManagerTest {
    public static void main(String [] args){
        String value1 = ConfigurationManager.getProperty("key1");
        String value2 = ConfigurationManager.getProperty("key2");
        System.out.println(value1);
        System.out.println(value2);
    }
}
