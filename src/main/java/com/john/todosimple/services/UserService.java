package com.john.todosimple.services;

import com.john.todosimple.models.User;
//import com.john.todosimple.repositories.TaskRepository;
import com.john.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service // Diz para o spring subir esta classe junto com o servidor
public class UserService {
    // Basicamente não se usa getter and setter em services

    @Autowired // serve para instanciar como se fosse um constructor
    private UserRepository userRepository;

//    @Autowired // serve para instanciar como se fosse um constructor
//    private TaskRepository taskRepository;

   public User findById(Long id){
       Optional<User> user = this.userRepository.findById(id);
       return user.orElseThrow(() -> new RuntimeException( // retornar user, caso esteja vazio/não exista retornar a exceção
               "User not found" + id + ", Type: " + User.class.getName()
       ));
   }

   @Transactional
   public User create(User obj){
       obj.setId(null);
       obj = this.userRepository.save(obj); // Cria e salva o novo usuário
//       this.taskRepository.saveAll(obj.getTasks());
       return obj;
   }

    @Transactional
   public User update(User obj){ // Serve para atualizar a senha pelo buscando o correspondente pelo Id
       User newObj = findById(obj.getId()); //  ver se existe o user
       newObj.setPassword(obj.getPassword()); // Para atualizar o id sem mexer no nome
       return this.userRepository.save(newObj);
   }

   public void delete(Long id){ // Função para deletar, enconstra o usuário pelo Id
       findById(id);
       try{
            this.userRepository.deleteById(id);
       } catch (Exception e){
           throw new RuntimeException("Cannot delete as there are related entities");
       }

   }

}
