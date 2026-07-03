package com.solverminds.projectmanagement.entity;


import javax.persistence.*;

@Entity
@Table(name = "project_sequence")
public class ProjectSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}