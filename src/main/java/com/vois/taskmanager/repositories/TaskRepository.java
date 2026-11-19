
package com.vois.taskmanager.repositories;

import com.vois.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

// 1. We extend JpaRepository. This is a magic interface from Spring.
// 2. <Task, Long> means "This repository manages Task objects, and their ID type is Long."
// 3. Because we extend it, we get methods like save(), findAll(), deleteById() FOR FREE.
public interface TaskRepository extends JpaRepository<Task, Long> {
}