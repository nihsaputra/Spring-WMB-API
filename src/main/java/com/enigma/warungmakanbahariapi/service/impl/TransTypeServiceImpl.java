package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.entity.TransType;
import com.enigma.warungmakanbahariapi.repository.TransTyperepository;
import com.enigma.warungmakanbahariapi.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransTypeServiceImpl implements TransTypeService {
    private final TransTyperepository transTyperepository;

    @Override
    public TransType getOrSave(String type) {
        Optional<TransType> optionalType = transTyperepository.findByType(type);
        if (!optionalType.isPresent()) {
                TransType buildType = TransType.builder()
                        .type(type)
                        .build();
                return transTyperepository.save(buildType);
            }else {
                return optionalType.get();
            }
    }
}
