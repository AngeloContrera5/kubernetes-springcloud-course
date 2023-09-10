package com.acontreras.springcloud.msvc.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses", url = "localhost:8002")
public interface CourseClientRest {

    @DeleteMapping("/deleteCourseUserById/{id}")
    void deleteCourseUserById(@PathVariable Long id);
}
