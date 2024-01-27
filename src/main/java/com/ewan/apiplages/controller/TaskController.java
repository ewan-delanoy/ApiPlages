package com.ewan.apiplages.controller;

import com.ewan.apiplages.entity.Task;
import com.ewan.apiplages.request.CreateTaskInput;
import com.ewan.apiplages.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskInput createTaskInput) {
        Task taskCreated = taskService.create(createTaskInput.toTask());

        return new ResponseEntity<Task>(taskCreated, HttpStatus.CREATED);
    }


}