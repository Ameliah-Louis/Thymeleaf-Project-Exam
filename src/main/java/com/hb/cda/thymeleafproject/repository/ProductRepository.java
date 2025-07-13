package com.hb.cda.thymeleafproject.repository;

import com.hb.cda.thymeleafproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, String> {
}
