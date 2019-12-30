package com.invillia.ThunderCoin.controller;

import com.invillia.ThunderCoin.domain.User;
import com.invillia.ThunderCoin.domain.request.UserSaveRequest;
import com.invillia.ThunderCoin.domain.response.UserResponse;
import com.invillia.ThunderCoin.service.impl.UserServiceImp;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImp userService;

    public UserController(final UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<UserResponse> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    private UserResponse findById(@PathVariable final Long id){
        return userService.findById(id);
    }

    @PostMapping
    private HttpEntity<?> save(@RequestBody final UserSaveRequest userSaveRequest){
        Long idUser = userService.save(userSaveRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/users/{id}")
                            .build(idUser);

        return ResponseEntity.created(location).build();
    }
}
