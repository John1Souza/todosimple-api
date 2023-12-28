package com.john.todosimple.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity // Diz que é uma tabela
@Table(name = User.TABLE_NAME) // Especifica qual a tabela
public class User {

    public interface CreateUser{}
    public interface  UpdateUser{}
    public static final String TABLE_NAME = "user"; // Nome da tabela

    @Id // Diz que é do tipo ID -> indica que é uma chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // diz que vai ser gerada automaticamente || auto-incrementa o valor de ID 0,1,2,3...
    @Column(name = "id", unique = true) // Indica que é uma coluna, e seu nome
    private Long id; // utilizar sempre a a classe (Integer, Long, Double -> (Aceitam valores nulos)) ao inves do tipo primitivo (int, long, double), para prevenir B.O.

    @Column(name = "username", length = 100, nullable = false)// Indica que é uma coluna, e seu nome
    @NotNull(groups = CreateUser.class)// indica que não aceita valores nulos
    @NotEmpty(groups = CreateUser.class) // indica que não aceita valores vazios
    @Size(groups = CreateUser.class, min = 2, max = 100) // estipula um tamanho para o método
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Define que é apenas escrita, não permite ver a senha ao digitar
    @Column(name = "password", length = 60, nullable = false) // indica que é uma coluna
    @NotNull(groups = {CreateUser.class, UpdateUser.class}) // indica que não aceita valores nulos
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class}) // indica que não aceita valores vazios
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60) // estipula um tamanho para o método
    private String password;

    //private List<Task> tasks = new ArrayList<>();


    public User() {

    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this){
            return true;
        }
        if(o == null){
            return false;
        }
        if(!(o instanceof User)){
            return false;
        }

        User other = (User) o;

        if(this.id == null){
            if(other.id != null){
                return false;
            } else if(!this.id.equals(other.id)){
                return false;
            }
        }
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
