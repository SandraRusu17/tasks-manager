package com.stefanini.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String userName;
    private String firstName;
    private String lastName;

    private List<Task> tasks;

    public User(Long id) {
        this.id = id;
    }

    public User(final Long id, final String userName, final String firstName, final String lastName) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tasks = new ArrayList<>();
    }

    public User(final String userName, final String firstName, final String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public void addTask(Task task){
        tasks.add(task);
        tasks.forEach(t -> t.setUser(this));
    }

    public String getFormattedDetails(){
        return "User {" + firstName +
                ", " + lastName +
                ", nr. of tasks=" + tasks.size() +
                '}';
    }
}
