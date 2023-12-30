package com.john.todosimple.services;

import com.john.todosimple.models.Task;
import com.john.todosimple.models.User;
import com.john.todosimple.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
                "task not found! id: " + id + ", type: " + User.class.getName()
        ));
    }

    @Transactional // importante para persistências/inserções
    public Task create(Task obj){
        User user = this.userService.findById(obj.getUser().getId()); // Verificar se o usuário dessa task existe
        obj.setId(null); // define que é pra criar um novo Id
        obj.setUser(user); // garantir que os dados correspondem ao usuário do banco
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e){

            throw new RuntimeException("Cannot delete as there are related entities");
        }
    }

}
