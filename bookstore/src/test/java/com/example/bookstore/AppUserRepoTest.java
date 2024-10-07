package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.bookstore.domain.AppUser;
import com.example.bookstore.repository.AppUserRepository;

@DataJpaTest
public class AppUserRepoTest {

    @Autowired
    private AppUserRepository arepository;

    @Test
    public void findByUsernameShouldReturnUser(){
        AppUser user = arepository.findByUsername("admin");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("admin");
    }

    @Test
    public void testCreateNewUser(){
        AppUser user = new AppUser("user2", "user2", "user", "user2@email.com");
        arepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void testDeleteExistingUser(){
        AppUser user = arepository.findByUsername("user");
        arepository.deleteById(user.getId());
        user = arepository.findByUsername("user");
        assertThat(user).isNull();

    }

}
