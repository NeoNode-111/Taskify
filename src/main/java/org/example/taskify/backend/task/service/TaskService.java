package org.example.taskify.backend.task.service;

import lombok.RequiredArgsConstructor;
import org.example.taskManager.BackEnd.exceptions.DuplicateTitleException;
import org.example.taskify.backend.appData.DataReader;
import org.example.taskify.backend.appData.TaskStatistic;
import org.example.taskify.backend.exceptions.NoTaskExistsException;
import org.example.taskify.backend.task.model.Task;
import org.example.taskify.backend.task.model.TaskStatus;
import org.example.taskify.backend.task.repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public void saveTask(Task task) {
        if (repository.existsByTitle(task.getTitle()))
            throw new DuplicateTitleException();
        repository.save(task);
        updateTaskStatistics();
    }

    public void deleteTask(int taskId) {
        if (!repository.existsById(taskId))
            throw new NoTaskExistsException("Task with ID: " + taskId + " does not exists!");
        repository.deleteById(taskId);
        updateTaskStatistics();
    }

    public void deleteAllTasks() {
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        repository.deleteAll();
        updateTaskStatistics();
    }

    public void deleteAllByStatus(TaskStatus status){
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        repository.deleteAllByStatus(status);
    }

    public Task getTaskById(int taskId) {
        return repository.findById(taskId)
                .orElseThrow(()-> new NoTaskExistsException("task with ID: " + taskId + " does not exists!"));
    }

    public List<Task> getAllTasks() {
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        return repository.findAll();
    }

    public Task getTasksByTitle(String title) {
        if (!repository.existsByTitle(title))
            throw new NoTaskExistsException("Task with title: " + title + " does not exists!");
        return repository.findByTitle(title);
    }

    public List<Task>getTasksByCreationDate(LocalDate creationDate) {
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        return repository.findAllByCreatedAt(creationDate);
    }

    public List<Task>getTasksByLastModifiedDate(LocalDate lastModifiedDate) {
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        return repository.findAllByModifiedAt(lastModifiedDate);
    }

    public List<Task>getTasksByDeadLine(LocalDate deadLine){
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        return repository.findAllByDeadLine(deadLine);
    }

    public List<Task>getTasksByStatus(TaskStatus status){
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        return repository.findAllByStatus(status);
    }

    public List<Task>getTasksSortedByDeadLine(){
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        return repository.findAllSortedByDeadLine();
    }

    public List<Task>getTasksSortedByTitle(){
        if (repository.isEmpty())
            throw new NoTaskExistsException();
        return repository.findAllSortedByTitle();
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void markForgottenTasks(){
        repository.markForgottenTasks(LocalDateTime.now());
    }

    private void updateTaskStatistics(){
        var dataReader = new DataReader<>(TaskStatistic.class, "task_statistics.json");
        var record = TaskStatistic.builder()
                .doneTasks(repository.countAllByStatus(TaskStatus.DONE))
                .undoneTasks(repository.countAllByStatus(TaskStatus.UNDONE))
                .forgottenTasks(repository.countAllByStatus(TaskStatus.FORGOTTEN))
                .totalTasks(repository.countAll()).build();
        dataReader.writeData(record);
    }
}
