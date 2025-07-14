package com.hb.cda.thymeleafproject.controller;

import com.hb.cda.thymeleafproject.entity.Cart;
import com.hb.cda.thymeleafproject.entity.CartItem;
import com.hb.cda.thymeleafproject.entity.User;
import com.hb.cda.thymeleafproject.repository.CartItemRepository;
import com.hb.cda.thymeleafproject.repository.CartRepository;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import com.hb.cda.thymeleafproject.repository.UserRepository;
import com.hb.cda.thymeleafproject.security.CartService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    private final CartService cartService;
    private final CartRepository cartRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final CartItemRepository cartItemRepo;


    public CartController(CartRepository repo, UserRepository userRepo, CartService cartService, ProductRepository productRepo, CartItemRepository cartItemRepo) {
        this.cartRepo = repo;
        this.userRepo = userRepo;
        this.cartService = cartService;
        this.productRepo = productRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        Cart cart = cartService.getCart(user);
        List<CartItem> cartItems = cartItemRepo.findByCartId(cart.getId());
        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        model.addAttribute("items", cartItems );
        model.addAttribute("total", cart.getTotalPrice(user));
        return "cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(Model model, @PathVariable String productId, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("Username not found"));
        cartService.addProductToCart(user, productId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart(Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("Username not found"));
        cartService.clearCart(user);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{productId}")
    public String removeProductFromCart(@PathVariable String productId, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("Username not found"));
        cartService.removeProductFromCart(user, productId);
        return "redirect:/cart";
    }

    @GetMapping("/")
    public String index (Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("Username not found"));
        model.addAttribute("user", user);
        model.addAttribute("cart", cartService.getCart(user));
        model.addAttribute("products", productRepo.findAll());
        return "index";
    }

}
