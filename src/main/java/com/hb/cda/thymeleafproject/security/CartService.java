package com.hb.cda.thymeleafproject.security;

import com.hb.cda.thymeleafproject.entity.Cart;
import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.CartRepository;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import com.hb.cda.thymeleafproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
private final CartRepository cartRepo;
private final ProductRepository productRepo;
private final UserRepository userRepo;

public CartService(CartRepository cartRepo, ProductRepository productRepo, UserRepository userRepo) {
    this.cartRepo = cartRepo;
    this.productRepo = productRepo;
    this.userRepo = userRepo;
}

    public Cart getCart(User user) {
        return cartRepo.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart(user);
                    cartRepo.save(cart);
                    return cart;
                });
    }

}
