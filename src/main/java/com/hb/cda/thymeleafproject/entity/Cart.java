package com.hb.cda.thymeleafproject.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Liste des articles dans le panier
    @ElementCollection
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    // Utilisateur lié à ce panier (si applicable)
    @OneToOne(mappedBy = "persistentCart")
    @JoinColumn(name = "user_id") //Clé étrangère
    private User user;

    // Ajoute des méthodes pour manipuler les items (ajouter, retirer, etc.)
    // Ajoute les getters, setters, constructeurs
    public Cart() {

    }

    public Cart(User user) {
        this.user = user;
    }

    public Cart(User user, String id) {
        this.user = user;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}