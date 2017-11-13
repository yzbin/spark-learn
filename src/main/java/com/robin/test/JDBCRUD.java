package com.robin.test;


import java.security.spec.ECField;
import java.sql.*;

/**
 * JDBC测试类
 */
public class JDBCRUD {
    public static void main(String [] args){
//        insert();
//        delete();
//        update();
//        select();
        preparedStatement();
    }

    private static void preparedStatement() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/spark_test","root","root");
            String sql = "insert into test(name,age) values(?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"李四");
            pstmt.setInt(2,26);
            int rtn = pstmt.executeUpdate();
            System.out.println("SQL语句影响了["+rtn+"]行。");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(conn != null){
                    conn.close();
                }
                if(pstmt!=null){
                    pstmt.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }


    private static void insert() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spark_test","root","root");
            stmt = conn.createStatement();
            String sql = "insert into test(name,age) values('李四',24)";
            int rtn = stmt.executeUpdate(sql);
            System.out.println("SQL影响了["+rtn+"]行");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(stmt!=null){
                    stmt.close();
                }
                if(conn!=null){
                    conn.close();
                }

            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

    }


    private static void delete() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spark_test","root","root");
            stmt = conn.createStatement();
            String sql = "delete from test where name='李四'";
            int rtn = stmt.executeUpdate(sql);
            System.out.println("SQL影响了["+rtn+"]行");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(stmt!=null){
                    stmt.close();
                }
                if(conn!=null){
                    conn.close();
                }

            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    private static void update() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spark_test","root","root");
            stmt = conn.createStatement();
            String sql = "update test set age=27 where name='李四'";
            int rtn = stmt.executeUpdate(sql);
            System.out.println("SQL影响了["+rtn+"]行");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(stmt!=null){
                    stmt.close();
                }
                if(conn!=null){
                    conn.close();
                }

            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    private static void select() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/spark_test","root","root");
            stmt=conn.createStatement();
            String sql = "select * from test;";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                System.out.println("id="+id+",name="+name+",age="+age);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(stmt!=null){
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
