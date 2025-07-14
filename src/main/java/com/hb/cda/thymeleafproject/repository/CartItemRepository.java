package com.hb.cda.thymeleafproject.repository;

import com.hb.cda.thymeleafproject.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CartItemRepository extends JpaRepository <CartItem, String> {
    List<CartItem> findByCartId(String id);
}
