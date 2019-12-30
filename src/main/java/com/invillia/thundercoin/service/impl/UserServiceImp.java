package com.invillia.ThunderCoin.service.impl;

import br.com.caelum.stella.validation.CPFValidator;
import com.invillia.ThunderCoin.domain.User;
import com.invillia.ThunderCoin.domain.request.UserSaveRequest;
import com.invillia.ThunderCoin.domain.response.UserResponse;
import com.invillia.ThunderCoin.exception.CPFNotValidException;
import com.invillia.ThunderCoin.exception.ObjectNotFoundException;
import com.invillia.ThunderCoin.mapper.UserMapper;
import com.invillia.ThunderCoin.repository.UserRepository;
import com.invillia.ThunderCoin.service.UserService;
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
            throw new CPFNotValidException("CPF Inválido!");

        if(userRepository.existsByCpf(userSaveRequest.getCpf())){
            throw new CPFNotValidException("CPF já cadastrado!");
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
