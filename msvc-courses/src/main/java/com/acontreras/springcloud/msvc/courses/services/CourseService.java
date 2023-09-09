package com.acontreras.springcloud.msvc.courses.services;

import com.acontreras.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> listAll();

    Optional<Course> getById(Long id);

    Course save(Course course);

    void delete(Long id);
}
