package com.odcspring.web_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;
    
}
