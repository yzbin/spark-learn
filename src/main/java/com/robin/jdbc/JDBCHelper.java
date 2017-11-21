package com.robin.jdbc;

import com.robin.conf.ConfigurationManager;
import com.robin.constant.Constants;
import org.apache.calcite.adapter.enumerable.RexImpTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * 5.开发增删改查的方法
     * 1)执行增删改SQL语句的方法
     * 2）执行查询SQL语句的方法
     * 3) 批量执行SQL语句的方法
     */


    /**
     * 执行增删改SQL语句
     * @param sql
     * @param params
     * @return
     */
    public int exexuteUpdate(String sql, Object[] params){
        int rtn = 0;
        Connection conn = null;
        PreparedStatement pstmt =null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i=0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
            rtn = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn!=null){
                datasource.push(conn);
            }
        }
        return rtn;
    }

    public void executeQuery(String sql, Object[]params,QueryCallBack callBack){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
            rs = pstmt.executeQuery();
            callBack.procees(rs);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn !=null){
                datasource.push(conn);
            }
        }

    }

    public int [] exexuteBath(String sql, List<Object[]> paramsList){
        int[] rtn = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            // 第一步：使用Connection对象，取消自动提交
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);

            // 第二步：使用PreparedStatement.addBatch()方法加入批量的SQL参数
            for(Object[] params : paramsList) {
                for(int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                pstmt.addBatch();
            }

            // 第三步：使用PreparedStatement.executeBatch()方法，执行批量的SQL语句
            rtn = pstmt.executeBatch();

            // 最后一步：使用Connection对象，提交批量的SQL语句
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rtn;
    }

    /**
     * 静态内部类：查询回调接口
     */
    public static interface QueryCallBack {
        void procees(ResultSet rs) throws Exception;
    }
}
