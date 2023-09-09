package com.acontreras.springcloud.msvc.users.services;

import com.acontreras.springcloud.msvc.users.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> getById(Long id);

    User save(User user);

    void delete(Long id);

    Optional<User> findByEmail(String email);

}

