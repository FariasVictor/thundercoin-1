package com.invillia.ThunderCoin.controller;

import com.invillia.ThunderCoin.domain.request.OriginRequest;
import com.invillia.ThunderCoin.domain.response.OriginResponse;
import com.invillia.ThunderCoin.service.OriginService;
import com.invillia.ThunderCoin.service.impl.OriginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/origin")
public class OriginController {

    private final OriginService originService;

    @Autowired
    public OriginController(OriginServiceImpl originService) {
        this.originService = originService;
    }


    @GetMapping
    public List<OriginResponse> findAll() {
        return originService.findAll();
    }

    @PostMapping
    public HttpEntity<?> create(@Valid @RequestBody final OriginRequest originRequest) {
        final Long id = originService.create(originRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/origin/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public OriginResponse findById(@PathVariable final Long id) {
        return originService.findById(id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable final Long id) {
        originService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updete(@PathVariable final Long id, @Valid @RequestBody final OriginRequest originRequest) {
        originService.update(id, originRequest);

        return ResponseEntity.noContent().build();
    }
}
