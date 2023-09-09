package com.acontreras.springcloud.msvc.courses.controllers;

import com.acontreras.springcloud.msvc.courses.models.entity.Course;
import com.acontreras.springcloud.msvc.courses.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService service;


    @GetMapping("/")
    public ResponseEntity<List<Course>> listAll() {

        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Course> bean = service.getById(id);
        if (bean.isPresent()) {
            return ResponseEntity.ok(bean.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return validateObj(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(course));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Course course, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validateObj(result);
        }
        Optional<Course> bean = service.getById(id);

        if (bean.isPresent()) {
            Course courseDB = bean.get();
            courseDB.setName(course.getName());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDB));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> bean = service.getById(id);
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
