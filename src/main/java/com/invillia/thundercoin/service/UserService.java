package com.invillia.ThunderCoin.service;

import com.invillia.ThunderCoin.domain.request.UserSaveRequest;
import com.invillia.ThunderCoin.domain.response.UserResponse;

import java.util.List;


public interface UserService {

    List<UserResponse> findAll();
    UserResponse findById(final Long id);
    Long save(final UserSaveRequest userSaveRequest);
    void delete(final Long id);
}
