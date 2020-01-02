package com.invillia.thundercoin.service.impl;

import br.com.caelum.stella.validation.CPFValidator;
import com.invillia.thundercoin.domain.User;
import com.invillia.thundercoin.domain.request.userRequest.UserSaveRequest;
import com.invillia.thundercoin.domain.response.UserResponse;
import com.invillia.thundercoin.exception.CPFNotValidException;
import com.invillia.thundercoin.exception.ObjectNotFoundException;
import com.invillia.thundercoin.mapper.UserMapper;
import com.invillia.thundercoin.repository.UserRepository;
import com.invillia.thundercoin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                    .map(userMapper::userToUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(final Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserResponse)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }

    @Override
    public Long save(final UserSaveRequest userSaveRequest) {
        if (!validateCPF(userSaveRequest.getCpf()))
            throw new CPFNotValidException("CPF Inválido!", HttpStatus.BAD_REQUEST);

        if(userRepository.existsByCpf(userSaveRequest.getCpf())){
            throw new CPFNotValidException("CPF já cadastrado!", HttpStatus.CONFLICT);
        }

        User user = userMapper.userSaveRequestToUser(userSaveRequest);

        return userRepository.save(user).getId();
    }

    @Override
    public void delete(final Long id) {

    }

    public boolean validateCPF(final String cpf){
        CPFValidator cpfValidator = new CPFValidator();

        try {
            cpfValidator.assertValid(cpf);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
