package com.invillia.thundercoin.service.impl;

import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.request.OriginRequest;
import com.invillia.thundercoin.domain.response.OriginResponse;

import com.invillia.thundercoin.enums.StatusEnum;
import com.invillia.thundercoin.exception.DataAlreadyRegistred;
import com.invillia.thundercoin.exception.ObjectNotFoundException;
import com.invillia.thundercoin.exception.OriginTypeNotFoundException;
import com.invillia.thundercoin.mapper.OriginMapper;
import com.invillia.thundercoin.repository.OriginRepository;
import com.invillia.thundercoin.service.OriginService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OriginServiceImpl implements OriginService {
    final private OriginRepository originRepository;

    final private OriginMapper originMapper;

    public OriginServiceImpl(OriginRepository originRepository, OriginMapper originMapper) {
        this.originRepository = originRepository;
        this.originMapper = originMapper;
    }

    @Transactional(readOnly = true)
    public List<OriginResponse> findAll() {
        final List<Origin> origins = originRepository.findAll();

        return originMapper.originToOriginResponse(origins);
    }

    @Transactional(readOnly = true)
    public OriginResponse findById(final Long id) {
        final Origin origin = originRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Origem não encontrada!"
        ));

        return originMapper.originToOriginResponse(origin);
    }

    public Long create(final OriginRequest originRequest) {
        if(originRepository.existsByName(originRequest.getName()))
            throw new DataAlreadyRegistred("Origem já cadastrada!");

        Origin origin = originMapper.originRequestToOrigin(originRequest);
        originRepository.save(origin);

        return origin.getId();
    }

    @Transactional
    public void update(final Long id, final OriginRequest originRequest) {
        final Origin origin = originRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Origem não encontrada!"
        ));

        if(!origin.getName().equals(originRequest.getName())){
            if(originRepository.existsByName(originRequest.getName()))
                throw new DataAlreadyRegistred("Origem já cadastrada!");
        }

        originMapper.updateOriginByAccountRequest(origin, originRequest);

        originRepository.save(origin);
    }

    @Transactional
    public void delete(final Long id) {
        final Origin origin = originRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Origem não encontrada!"
        ));

        origin.setStatus(StatusEnum.DISABLED);

        originRepository.save(origin);
    }
}
