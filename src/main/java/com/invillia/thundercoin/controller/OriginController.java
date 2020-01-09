package com.invillia.thundercoin.controller;

import com.invillia.thundercoin.domain.request.OriginRequest;
import com.invillia.thundercoin.domain.response.OriginResponse;
import com.invillia.thundercoin.service.OriginService;
import com.invillia.thundercoin.service.impl.OriginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/origins")
public class OriginController {

    private final OriginServiceImpl originService;

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

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable final Long id, @Valid @RequestBody final OriginRequest originRequest) {
        originService.update(id, originRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable final Long id) {
        originService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public List<OriginResponse> findByCategory(@RequestParam(name = "category") final String category){
        return originService.findByCategory(category);
    }
}
