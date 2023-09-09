package com.acontreras.springcloud.msvc.users.controllers;

import com.acontreras.springcloud.msvc.users.models.User;
import com.acontreras.springcloud.msvc.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/")
    public List<User> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Optional<User> bean = service.getById(id);
        if (bean.isPresent()) {
            return ResponseEntity.ok(bean.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) {


        if (result.hasErrors()) {
            return validateObj(result);
        }
        if (!user.getEmail().isEmpty() && service.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "That email already exists"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {


        if (result.hasErrors()) {
            return validateObj(result);
        }

        Optional<User> bean = service.getById(id);

        if (bean.isPresent()) {
            User userDB = bean.get();

            //we can use Collections.singletonMap lib to create a object map to return a msg

            if (!user.getEmail().isEmpty() && !user.getEmail().equalsIgnoreCase(userDB.getEmail()) && service.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("message", "That email already exists"));
            }
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDB));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> bean = service.getById(id);
        if (bean.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validateObj(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "Field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }


}
