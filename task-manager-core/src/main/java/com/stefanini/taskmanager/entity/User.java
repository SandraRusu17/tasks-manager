package com.stefanini.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor

@Entity(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "users_tasks",
            inverseJoinColumns = @JoinColumn(name = "task_id",
                    nullable = false,
                    updatable = false),
            joinColumns = @JoinColumn(name = "user_id",
                    nullable = false,
                    updatable = false))


    private Set<Task> tasks = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name", unique = true, length = 50)
    private String userName;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;


    public User(Long id) {
        this.id = id;
    }

    public User(final String userName, final String firstName, final String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    protected User() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.getUsers().add(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.getUsers().remove(this);
    }

    public String toString() {
        return "User {" + firstName +
                ", " + lastName +
                ", nr. of tasks=" + tasks.size() +
                '}';
    }
}
