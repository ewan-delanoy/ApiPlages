package com.ewan.apiplages.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ewan.apiplages.dto.PaysDto;
import com.ewan.apiplages.dto.LienDeParenteDto;
import com.ewan.apiplages.dto.ParasolDto;
import com.ewan.apiplages.entity.Pays;
import com.ewan.apiplages.entity.LienDeParente;
import com.ewan.apiplages.entity.Task;
import com.ewan.apiplages.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);

        return tasks;
    }

    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

    public Task update(Task taskToUpdate) {
        return taskRepository.save(taskToUpdate);
    }

    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}