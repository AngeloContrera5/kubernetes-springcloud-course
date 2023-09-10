package com.acontreras.springcloud.msvc.courses.services;

import com.acontreras.springcloud.msvc.courses.models.User;
import com.acontreras.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> listAll();

    Optional<Course> getById(Long id);

    Course save(Course course);

    void delete(Long id);

    void deleteCourseUserById(Long id);

    Optional<User> assignUser(User user, Long courseId);

    Optional<User> createUser(User user, Long courseId);

    Optional<User> removeUser(User user, Long courseId);

    Optional<Course> getByIdWithUsers(Long id);

}
