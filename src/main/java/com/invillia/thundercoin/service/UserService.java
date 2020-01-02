package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.request.userRequest.UserSaveRequest;
import com.invillia.thundercoin.domain.response.UserResponse;

import java.util.List;


public interface UserService {

    List<UserResponse> findAll();
    UserResponse findById(final Long id);
    Long save(final UserSaveRequest userSaveRequest);
    void delete(final Long id);
}
