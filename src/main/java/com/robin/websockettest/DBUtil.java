package com.robin.websockettest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    public static List<AlarmMessage>  getFromDB() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<AlarmMessage> list=new ArrayList<AlarmMessage>();

        String dirver="com.mysql.jdbc.Driver";
        String user="root";
        String psd="root";
        String database="streamingproblem";
        String tablename="problem";
        String url="jdbc:mysql://172.17.11.120:3306/"+database+"?user="+user+"&password="+psd;
        Class.forName(dirver).newInstance();
        Connection conn= DriverManager.getConnection(url);
        Statement stat=conn.createStatement();
        String sql="select * from "+tablename;
        ResultSet rs=stat.executeQuery(sql);
        while (rs.next()){
            AlarmMessage alarmMessage=new AlarmMessage(rs.getString(2),rs.getString(3),rs.getString(4));
            list.add(alarmMessage);
        }
        rs.close();
        stat.close();
        conn.close();
        return list;
    }
}
