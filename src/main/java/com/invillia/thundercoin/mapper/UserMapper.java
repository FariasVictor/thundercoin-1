package com.invillia.thundercoin.mapper;

import com.invillia.thundercoin.domain.User;
import com.invillia.thundercoin.domain.request.userRequest.UserSaveRequest;
import com.invillia.thundercoin.domain.response.UserResponse;
import org.springframework.stereotype.Component;

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
}
