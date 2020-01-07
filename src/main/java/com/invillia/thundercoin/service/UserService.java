package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.request.UserSaveRequest;
import com.invillia.thundercoin.domain.request.UserUpdateRequest;
import com.invillia.thundercoin.domain.response.UserResponse;

import java.util.List;


public interface UserService {

    List<UserResponse> findAll();
    UserResponse findById(final Long id);
    Long create(final UserSaveRequest userSaveRequest);
    void delete(final Long id);
    void update(final Long id, final UserUpdateRequest userUpdateRequest);
}
