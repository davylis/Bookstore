package com.example.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

public class AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
