package task.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import task.model.Task;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdAndIsDeleteFalse(Long userId);

    List<Task> findByUserIdAndIsCompleteFalseAndIsDeleteFalse(Long userId);

    List<Task> findByIsDeleteFalse();

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.isdelete = true WHERE t.taskid = ?1")
    void markAsDeleted(Long id);
}
