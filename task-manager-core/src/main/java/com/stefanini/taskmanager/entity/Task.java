package com.stefanini.taskmanager.entity;


import com.stefanini.taskmanager.annotations.ActionEmailConfirmation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tasks")
@ActionEmailConfirmation(email = "sandra.rusu17@gmail.com")
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;


    @ManyToMany(mappedBy = "tasks", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

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

    public void addUser(User user) {
        users.add(user);
        user.getTasks().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getTasks().remove(this);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task(Set<User> users, Long id, String title, String description) {
        this.users = users;
        this.id = id;
        this.title = title;
        this.description = description;
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

