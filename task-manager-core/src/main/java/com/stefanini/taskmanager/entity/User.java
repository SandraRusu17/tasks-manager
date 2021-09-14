package com.stefanini.taskmanager.entity;


import com.stefanini.taskmanager.annotations.ActionEmailConfirmation;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "users")
@ActionEmailConfirmation(email = "sandra.rusu17@gmail.com")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )

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

    public User(final String firstName, final String lastName, final String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;

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

    public User(Set<Task> tasks, Long id, String userName, String firstName, String lastName) {
        this.tasks = tasks;
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "User {" + id +
                "," + firstName +
                ", " + lastName +
                " , " + userName +
                ", nr. of tasks=" + tasks.size() +
                '}';
    }
}
