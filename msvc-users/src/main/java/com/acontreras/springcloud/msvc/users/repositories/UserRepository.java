package com.acontreras.springcloud.msvc.users.repositories;

import com.acontreras.springcloud.msvc.users.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {


    //select u User u where email = ?1
    Optional<User> findByEmail(String email);

}
