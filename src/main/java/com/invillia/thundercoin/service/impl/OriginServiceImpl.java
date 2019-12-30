package com.invillia.ThunderCoin.service.impl;

import com.invillia.ThunderCoin.domain.Origin;
import com.invillia.ThunderCoin.domain.request.OriginRequest;
import com.invillia.ThunderCoin.domain.response.OriginResponse;

import com.invillia.ThunderCoin.exception.OriginTypeNotFoundException;
import com.invillia.ThunderCoin.mapper.OriginMapper;
import com.invillia.ThunderCoin.repository.OriginRepository;
import com.invillia.ThunderCoin.service.OriginService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OriginServiceImpl implements OriginService {

    final private OriginRepository originRepository;

    final private OriginMapper originMapper;


    @Autowired
    public OriginServiceImpl(OriginRepository oringinRepository, OriginMapper originMapper) {
        this.originRepository = oringinRepository;
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

    public void delete(final Long id) {
        final Origin origin = originRepository.findById(id).orElseThrow(() -> new OriginTypeNotFoundException(
                "Id: " + id + " não encontrado!"
        ));

        originRepository.deleteById(id);
    }

    public void update(final Long id, final OriginRequest originRequest) {
        final Origin origin = originRepository.findById(id).orElseThrow(() -> new OriginTypeNotFoundException(
                "Id: " + id + " não encontrado!"
        ));

        originMapper.updateOriginByAccountRequest(origin, originRequest);

        originRepository.save(origin);
    }
}
