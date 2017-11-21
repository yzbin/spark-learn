package com.robin.dao.impl;

import com.robin.dao.ITaskDAO;

/**
 * DAO工厂类
 */
public class DAOFactory {
    public static ITaskDAO getTaskDAO(){
        return new TaskDAOImpl();
    }
}
