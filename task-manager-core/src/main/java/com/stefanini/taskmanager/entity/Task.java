package com.stefanini.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String description;
    private Long userId;
//
//    public Task(Long id, String title, String description, Long user_id) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.user_id = user_id;
//    }


    public Task(String title, String description) {
        this.title = title;
        this.description = description;

    }

    public Task(){};
}
