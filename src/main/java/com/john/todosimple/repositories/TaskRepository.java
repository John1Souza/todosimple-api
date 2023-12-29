package com.john.todosimple.repositories;

import com.john.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser_Id(Long id); // Buscar pelo user id, task não tem id, mas tem user e user tem ID

    //@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")// Faz a chamada no banco de dados utilizando o parametro passado em @Param
    //List<Task> findByUser_Id(@Param("id") Long id); // Faz a chamada no banco de dados utilizando o parametro passado em @Param

    //@Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    //List<Task> findByUser_Id(@Param("id") Long id);
}
