package com.example.bookstore.repository;

import com.example.bookstore.domain.Category;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);
}

