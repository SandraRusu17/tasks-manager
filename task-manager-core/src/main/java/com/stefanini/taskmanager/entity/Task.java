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

}
