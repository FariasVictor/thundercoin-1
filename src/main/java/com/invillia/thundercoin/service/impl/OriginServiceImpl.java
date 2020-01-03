package com.invillia.thundercoin.service.impl;

import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.request.OriginRequest;
import com.invillia.thundercoin.domain.response.OriginResponse;

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


    @Autowired
    public OriginServiceImpl(OriginRepository originRepository, OriginMapper originMapper) {
        this.originRepository = originRepository;
        this.originMapper = originMapper;
    }

    public Long create(final OriginRequest originRequest) {
        Origin origin = originMapper.originRequestToOrigin(originRequest);
        originRepository.save(origin);

        return origin.getId();
    }

    @Transactional(readOnly = true)
    public List<OriginResponse> findAll() {
        final List<Origin> origins = originRepository.findAll();

        return originMapper.originToOriginResponse(origins);
    }

    @Transactional(readOnly = true)
    public OriginResponse findById(final Long id) {
        final Origin origin = originRepository.findById(id).orElseThrow(() -> new OriginTypeNotFoundException(
                "Id: " + id + " não encontrado!"
        ));

        return originMapper.originToOriginResponse(origin);
    }

    @Transactional
    public void delete(final Long id) {
        final Origin origin = originRepository.findById(id).orElseThrow(() -> new OriginTypeNotFoundException(
                "Id: " + id + " não encontrado!"
        ));

        originRepository.deleteById(id);
    }

    @Transactional
    public void update(final Long id, final OriginRequest originRequest) {
        final Origin origin = originRepository.findById(id).orElseThrow(() -> new OriginTypeNotFoundException(
                "Id: " + id + " não encontrado!"
        ));

        originMapper.updateOriginByAccountRequest(origin, originRequest);

        originRepository.save(origin);
    }
}
