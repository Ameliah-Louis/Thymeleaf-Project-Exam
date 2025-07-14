package com.hb.cda.thymeleafproject.config;

import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.CartRepository;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import com.hb.cda.thymeleafproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final CartRepository cartRepo;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(ProductRepository productRepo, UserRepository userRepo, CartRepository cartRepo, PasswordEncoder passwordEncoder) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepo.count()==0){
            Product product1 = new Product("Nintendo Switch", 300.0, "Console de jeu Nintendo", 6);
            productRepo.save(product1);
            Product product2 = new Product("PS5", 550.0, "Console de jeu PlayStation", 2);
            productRepo.save(product2);
            Product product3 = new Product("Xbox", 500.0, "Console de jeu Microsoft", 10);
            productRepo.save(product3);
        }
        if (userRepo.count()==0){
            User user1 = new User();
                    user1.setUsername("améliah");
                    user1.setPassword(passwordEncoder.encode("amy123"));
                    user1.setRole("ROLE_USER");
                    userRepo.save(user1);
            User user2 = new User();
                    user2.setUsername("malak");
                    user2.setPassword(passwordEncoder.encode("malak123"));
                    user2.setRole("ROLE_USER");
                    userRepo.save(user2);
            User user3 = new User();
                    user3.setUsername("mat");
                    user3.setPassword(passwordEncoder.encode("mat123"));
                    user3.setRole("ROLE_USER");
                    userRepo.save(user3);
        }
    }
}
