package com.invillia.thundercoin.service.impl;

import br.com.caelum.stella.validation.CPFValidator;
import com.invillia.thundercoin.domain.User;
import com.invillia.thundercoin.domain.request.UserSaveRequest;
import com.invillia.thundercoin.domain.request.UserUpdateRequest;
import com.invillia.thundercoin.domain.response.UserResponse;
import com.invillia.thundercoin.enums.StatusEnum;
import com.invillia.thundercoin.exception.CPFNotValidException;
import com.invillia.thundercoin.exception.ObjectNotFoundException;
import com.invillia.thundercoin.mapper.UserMapper;
import com.invillia.thundercoin.repository.UserRepository;
import com.invillia.thundercoin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccountServiceImpl accountService;

    public UserServiceImpl(final UserRepository userRepository, final UserMapper userMapper, final AccountServiceImpl accountService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.accountService = accountService;
    }

    @Transactional
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponse findById(final Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserResponse)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }

    @Transactional
    public Long create(final UserSaveRequest userSaveRequest) {
        if (!validateCPF(userSaveRequest.getCpf()))
            throw new CPFNotValidException("CPF Inválido!", HttpStatus.BAD_REQUEST);

        if(userRepository.existsByCpf(userSaveRequest.getCpf())){
            throw new CPFNotValidException("CPF já cadastrado!", HttpStatus.CONFLICT);
        }

        User user = userMapper.userSaveRequestToUser(userSaveRequest);

        userRepository.save(user);

        accountService.create(user);

        return user.getId();
    }

    @Transactional
    public void update( final Long id, final UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        if(!user.getCpf().equals(userUpdateRequest.getCpf())){
            if(!validateCPF(userUpdateRequest.getCpf())){
                throw new CPFNotValidException("CPF Inválido!", HttpStatus.BAD_REQUEST);
            }else{
                if(userRepository.existsByCpf(userUpdateRequest.getCpf())){
                    throw new CPFNotValidException("CPF já cadastrado!", HttpStatus.CONFLICT);
                }
            }
        }

        userMapper.updateUserToUserRequest(user, userUpdateRequest);

        userRepository.save(user);
    }

    @Transactional
    public void delete(final Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        user.setStatus(StatusEnum.DISABLED);

        userRepository.save(user);
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
