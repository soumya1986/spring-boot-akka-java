package com.example.repository;

import com.example.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by soumya on 5/18/2015.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
