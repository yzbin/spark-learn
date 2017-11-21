package com.robin.test;

import com.robin.dao.ITaskDAO;
import com.robin.dao.impl.DAOFactory;
import com.robin.domain.Task;

public class TaskDAOTest {
    public static void main(String []args){
        ITaskDAO taskDAO = DAOFactory.getTaskDAO();
        Task task = taskDAO.findByID(2);
        System.out.print(task.getTaskName());
    }
}
