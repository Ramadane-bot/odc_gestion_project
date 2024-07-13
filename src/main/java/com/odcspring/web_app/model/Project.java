package com.odcspring.web_app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @OneToMany(mappedBy = "project")
    private List<Task> taches;

    @ManyToMany
    private List<User> membresProjet;
}
