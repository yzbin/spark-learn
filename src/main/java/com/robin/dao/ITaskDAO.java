package com.robin.dao;

import com.robin.domain.Task;

public interface ITaskDAO {
    /**
     * 根据主键查询任务
     */
    Task findByID(long taskid);
}
