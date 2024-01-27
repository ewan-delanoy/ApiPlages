package com.ewan.apiplages.request;

import com.ewan.apiplages.entity.Task;
import com.ewan.apiplages.entity.TaskStatusEnum;

import java.util.Date;

public record CreateTaskInput(String name, String description, TaskStatusEnum status, Date dueDate) {
    public Task toTask() {
        Task task = new Task();

        task.setName(name);
        task.setDescription(description);
        task.setStatus(status);
        task.setDueDate(dueDate);

        return task;
    }
}