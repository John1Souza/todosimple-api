package com.john.todosimple.repositories;

import com.john.todosimple.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //User findByUsername(String username); // Busca algo em user pelos parametros passados, nesse caso Username
}
