package com.robin.websockettest;

import javax.websocket.Session;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 写一个线程用于发送新数据到页面，run中开启无限循环，用一个变量 currentIndex 记录当前数据量，当有新数据时，发送新数据
 */
public class OneThread  extends  Thread{
    private Session session;
    private List<AlarmMessage> currentMessage;
    private DBUtil dbUtil;
    private int currentIndex;

    public OneThread(Session session) {
        this.session = session;
        currentMessage = new ArrayList<AlarmMessage>();
        dbUtil = new DBUtil();
        currentIndex = 0;//此时是0条消息
    }

    @Override
    public void run() {
        while (true) {
            List<AlarmMessage> list = null;
            try {
                try {
                    list = DBUtil.getFromDB();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (list != null && currentIndex < list.size()) {
                for (int i = currentIndex; i < list.size(); i++) {
                    try {
                        session.getBasicRemote().sendText(list.get(i).getFanNo()
                                + "," + list.get(i).getTime()
                                + "," + list.get(i).getDescription());
//                            session.getBasicRemote().sendObject(list.get(i)); //No encoder specified for object of class [class AlarmMessage]
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                currentIndex = list.size();
            }
            try {
                //一秒刷新一次
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
