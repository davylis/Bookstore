package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.bookstore.domain.Category;
import com.example.bookstore.repository.CategoryRepository;

@DataJpaTest
public class CategoryRepoTest {
    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByNameShouldReturnCategory(){
        List<Category> categories = crepository.findByName("Romance");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Romance");
    }

    @Test
    public void testCreateNewCategory(){
        Category category = new Category("Fantasy");
        crepository.save(category);
        assertThat(category.getId()).isNotNull();
    }

    @Test 
    public void testDeleteCategory(){
        List<Category> categories = crepository.findByName("Romance");
        crepository.deleteById(categories.get(0).getId());
        categories = crepository.findByName("Romance");
        assertThat(categories).hasSize(0);
    }
}
