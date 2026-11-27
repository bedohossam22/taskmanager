package com.vois.taskmanager.controllers;

import com.vois.taskmanager.entities.Task;
import com.vois.taskmanager.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // create task
    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskRepository.save(task);
    }

    // get all tasks
    @GetMapping
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // read one task by id
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id);
    }

    // update
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        task.setDeadline(taskDetails.getDeadline());
        task.setPriority(taskDetails.getPriority());
        task.setCategory(taskDetails.getCategory());
        return taskRepository.save(task);
    }

    // delete
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "Task deleted successfully!";
    }

    // delete all completed tasks
    @DeleteMapping("/completed")
    public String deleteCompleted() {
        Iterable<Task> all = taskRepository.findAll();
        for (Task t : all) {
            if (t.isCompleted()) {
                taskRepository.deleteById(t.getId());
            }
        }
        return "Completed tasks deleted!";
    }
}
