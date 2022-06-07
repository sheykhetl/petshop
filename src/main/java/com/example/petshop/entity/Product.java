package com.example.petshop.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "price")
    private double price;

    @ManyToMany(mappedBy = "products")
    private Set<User> users = new HashSet<>();

    public Product() {
    }

    public Product(Category category, String name, String description, String photoUrl, double price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.photoUrl = photoUrl;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", price=" + price +
                ", users=" + users +
                '}';
    }
}
