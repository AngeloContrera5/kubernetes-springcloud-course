package com.acontreras.springcloud.msvc.courses.clients;

import com.acontreras.springcloud.msvc.courses.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//cuando se implemente spring cloud con kubernetes la propiedad url se quitará
@FeignClient(name = "mscv-users", url = "localhost:8001")
public interface UserClientRest {

    @GetMapping("/{id}")
    User getById(@PathVariable Long id);

    @PostMapping("/save")
    User save(@RequestBody User user);


}
