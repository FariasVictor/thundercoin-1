package com.invillia.thundercoin.mapper;

import com.invillia.thundercoin.domain.User;
import com.invillia.thundercoin.domain.request.UserSaveRequest;
import com.invillia.thundercoin.domain.request.UserUpdateRequest;
import com.invillia.thundercoin.domain.response.UserResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public UserResponse userToUserResponse(final User user) {
        return UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .cpf(user.getCpf())
                    .status(user.getStatus().toString())
                    .createdAt(user.getCreatedAt().format(formatter))
                    .updatedAt(user.getUpdatedAt().format(formatter))
                .build();
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

    public void updateUserToUserRequest(final User user, final UserUpdateRequest userUpdateRequest){
        user.setName(userUpdateRequest.getName());
        user.setCpf(userUpdateRequest.getCpf());
    }
}
