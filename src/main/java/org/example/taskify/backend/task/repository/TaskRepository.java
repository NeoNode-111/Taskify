package org.example.taskify.backend.task.repository;

import jakarta.transaction.Transactional;
import org.example.taskify.backend.task.model.Task;
import org.example.taskify.backend.task.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task>findAllByStatus(TaskStatus status);
    @Query("select t from Task t order by t.deadLine")
    List<Task>findAllSortedByDeadLine();
    @Query("select t from Task t order by t.title")
    List<Task>findAllSortedByTitle();
    Task findByTitle(String title);
    @Query(value = "select t from Task t where cast(t.createdAt as DATE) = :created")
    List<Task> findAllByCreatedAt(LocalDate created);
    @Query(value = "select t from Task t where cast(t.modifiedAt as DATE) = :modified")
    List<Task> findAllByModifiedAt(LocalDate modified);
    @Query(value = "select t from Task t where cast(t.deadLine as DATE) = :deadLine")
    List<Task> findAllByDeadLine(LocalDate deadLine);

    void deleteAllByStatus(TaskStatus status);

    int countAllByStatus(TaskStatus status);

    @Query("select count(t.id) from Task t")
    int countAll();
    boolean existsByTitle(String title);
    @Query("select not exists (select 1 from Task t)")
    boolean isEmpty();
    @Transactional @Modifying
    @Query("update Task t set t.status = 'FORGOTTEN' where t.status = 'UNDONE' and t.deadLine <:time ")
    void markForgottenTasks(LocalDateTime time);


}
