package ru.job4j.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    int id;

    @Column(name= "desc")
    String desc;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    Date created;

    @Column(name = "done")
    Date done;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task that = (Task) o;
        return getDesc().equals(that.getDesc()) &&
                getCreated().equals(that.getCreated()) &&
                getDone().equals(that.getDone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDesc(), getCreated(), getDone());
    }
}