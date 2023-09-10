package com.acontreras.springcloud.msvc.courses.services;

import com.acontreras.springcloud.msvc.courses.clients.UserClientRest;
import com.acontreras.springcloud.msvc.courses.models.User;
import com.acontreras.springcloud.msvc.courses.models.entity.Course;
import com.acontreras.springcloud.msvc.courses.models.entity.CourseUser;
import com.acontreras.springcloud.msvc.courses.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Course> listAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<User> assignUser(User user, Long courseId) {

        Optional<Course> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            User userMsvc = client.getById(user.getId());

            Course course = o.get();

            CourseUser courseUser = new CourseUser();

            courseUser.setUserId(userMsvc.getId());

            course.addCourseUser(courseUser);

            courseRepository.save(course);

            return Optional.of(userMsvc);

        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long courseId) {
        Optional<Course> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            //se crea nuevo usuario
            User userMsvc = client.save(user);

            Course course = o.get();

            CourseUser courseUser = new CourseUser();

            courseUser.setUserId(userMsvc.getId());

            course.addCourseUser(courseUser);

            courseRepository.save(course);

            return Optional.of(userMsvc);

        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> removeUser(User user, Long courseId) {
        Optional<Course> o = courseRepository.findById(courseId);
        if (o.isPresent()) {
            User userMsvc = client.getById(user.getId());

            Course course = o.get();

            CourseUser courseUser = new CourseUser();

            courseUser.setUserId(userMsvc.getId());

            course.removeCourseUser(courseUser);

            courseRepository.save(course);

            return Optional.of(userMsvc);

        }
        return Optional.empty();
    }
}
