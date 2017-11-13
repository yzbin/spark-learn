package com.robin.jdbc;

import com.robin.conf.ConfigurationManager;
import com.robin.constant.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

public class JDBCHelper {
    //1.在静态代码块中直接加载驱动
    static {
        try {
            String driver = ConfigurationManager.getProperty(Constants.JDBC_DRIVER);
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //2.实现JDBCHelper的单例化
    private static JDBCHelper instance = null;

    /**
     * 获取单例
     * @return 单例
     */
    public static JDBCHelper getInstance(){
        if (instance==null){
            synchronized (JDBCHelper.class){
                if (instance==null){
                    instance=new JDBCHelper();
                }
            }
        }
        return instance;
    }

    //数据库连接池
    private LinkedList<Connection> datasource = new LinkedList<Connection>();

    //3.实现单例的过程中创建唯一的数据库连接池
    private JDBCHelper(){
        int datasourcesize = ConfigurationManager.getInteger(Constants.JDBC_DATASOURCE_SIZE);
        for(int i =0; i<datasourcesize;i++){
            String url = ConfigurationManager.getProperty(Constants.JDBC_URL);
            String user = ConfigurationManager.getProperty(Constants.JDBC_USER);
            String passwoed = ConfigurationManager.getProperty(Constants.JDBC_PASSWORD);
            try {
                Connection conn = DriverManager.getConnection(url,user,passwoed);
                datasource.push(conn);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //4.提供获取连接的方法
    public synchronized Connection getConnection(){
        while (datasource.size()==0){
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return datasource.poll();
    }


}
