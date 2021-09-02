package com.stefanini.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor

@Entity(name = "tasks")
public class Task implements Serializable {


    @Transient
    @ManyToMany
    @JoinTable(name = "users_tasks",
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    nullable = false,
                    updatable = false),
            joinColumns = @JoinColumn(name = "task_id",
                    nullable = false,
                    updatable = false))

    private Set<User> users = new HashSet<>();

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "task_title", length = 100)
    private String title;

    @Column(name = "task_description", length = 100)
    private String description;


    protected Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;

    }

    public void addUser(User user){
        users.add(user);
        user.getTasks().add(this);
    }

    public void removeUser(User user){
        users.remove(user);
        user.getTasks().remove(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
