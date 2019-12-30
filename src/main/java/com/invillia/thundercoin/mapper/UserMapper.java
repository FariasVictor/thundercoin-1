package com.invillia.ThunderCoin.mapper;

import com.invillia.ThunderCoin.domain.User;
import com.invillia.ThunderCoin.domain.request.UserSaveRequest;
import com.invillia.ThunderCoin.domain.request.UserUpdateRequest;
import com.invillia.ThunderCoin.domain.response.UserResponse;
import com.invillia.ThunderCoin.enums.UserStatusEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse userToUserResponse(final User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setCpf(user.getCpf());

        return userResponse;
    }

    public List<UserResponse> userToUserResponse(final List<User> users) {
        return users.stream()
                .map(this::userToUserResponse)
                .collect(Collectors.toList());

    }

    public User userSaveRequestToUser(UserSaveRequest userSaveRequest){
        User user = new User();

        user.setName(userSaveRequest.getName());
        user.setCpf(userSaveRequest.getCpf());

        return user;
    }

//    public void updateUserByUserUpdateRequest(User user, UserUpdateRequest userUpdateRequest) {
//
//        user.setName(userUpdateRequest.getName());
//        user.setBalance(userUpdateRequest.getBalance());
//        user.setStatus(UserStatusEnum.valueOf(userUpdateRequest.getStatus()));
//    }
}
