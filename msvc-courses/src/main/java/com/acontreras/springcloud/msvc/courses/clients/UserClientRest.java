package com.acontreras.springcloud.msvc.courses.clients;

import com.acontreras.springcloud.msvc.courses.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//cuando se implemente spring cloud con kubernetes la propiedad url se quitará
@FeignClient(name = "msvc-users", url = "${msvc.users.url}")
public interface UserClientRest {

    @GetMapping("/{id}")
    User getById(@PathVariable Long id);

    @PostMapping("/save")
    User save(@RequestBody User user);

    @GetMapping("/listUsersByIds")
    List<User> listUsersByIds(@RequestParam Iterable<Long> ids);


}
