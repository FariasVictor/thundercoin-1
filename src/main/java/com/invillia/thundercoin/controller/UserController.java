package com.invillia.thundercoin.controller;

import com.invillia.thundercoin.domain.request.UserSaveRequest;
import com.invillia.thundercoin.domain.request.UserUpdateRequest;
import com.invillia.thundercoin.domain.response.UserResponse;
import com.invillia.thundercoin.service.impl.UserServiceImp;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable final Long id){
        return userService.findById(id);
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody final UserSaveRequest userSaveRequest){
        Long idUser = userService.save(userSaveRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/users/{id}")
                            .build(idUser);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable final Long id,
                                @RequestBody @Valid final UserUpdateRequest userUpdateRequest){
        userService.update(id, userUpdateRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable final Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
