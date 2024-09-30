package com.example.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.bookstore.domain.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
