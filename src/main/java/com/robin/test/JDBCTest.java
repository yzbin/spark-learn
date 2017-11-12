package com.robin.test;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * JDBC测试类
 */
public class JDBCTest {
    public static final String url = "jdbc:mysql://localhost:9991/test?";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "root";

    public static int lport = 9991;//本地端口（随便取）
    public static String rhost = "192.168.134.50";//远程MySQL服务器
    public static int rport = 3306;//远程MySQL服务端口

    public static void connectSession() {
        String user = "hadoop";//SSH连接用户名
        String password = "hadoop";//SSH连接密码
        String host = "192.168.134.50";//SSH服务器
        int port = 22;//SSH访问端口
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnect() {
        connectSession();
        Connection conn = null;
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url,user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }



    public static void main(String arg[]) throws Exception{
       insert();
    }

    private static  void insert(){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnect();
            stmt = conn.createStatement();
            String sql="insert into user_test(name,age) values('张三',23)";
            int i = stmt.executeUpdate(sql);
            System.out.println("影响了:+["+i+"]行");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (stmt!=null){
                    stmt.close();
                }
                if (conn!=null){
                    conn.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

    }
}
