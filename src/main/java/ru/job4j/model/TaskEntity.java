package ru.job4j.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name= "desc")
    String desc;
    @Column(name = "created")
    Date created;
    @Column(name = "done")
    Date done;
}