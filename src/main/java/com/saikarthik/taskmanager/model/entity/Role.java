package com.saikarthik.taskmanager.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    //getters and setters
    public Long getId(){
        return id;
    }
    public void setId(Long Id){
        this.id = Id;
    }

    public RoleName getName(){
        return name;
    }
    public void setName(RoleName name){
        this.name = name;
    }

}
