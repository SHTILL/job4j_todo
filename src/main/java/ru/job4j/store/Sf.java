package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Sf {
    private final static SessionFactory sf;

    static {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static Session getSession() {
        return sf.openSession();
    }
}
