package ru.job4j.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Task {
    private final int id;
    private final String desc;
    private final Date created;
    private final boolean done;

    public Task(int id, String desc, Date created, boolean done) {
        this.id = id;
        this.desc = desc;
        this.created = created;
        this.done = done;
    }
}
