package com.example.jsonex.productshop.entities.categories;

import com.example.jsonex.productshop.entities.users.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    @ManyToMany
    private Set<Category> categories;

    public Category() {
        categories = new HashSet<>();
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
