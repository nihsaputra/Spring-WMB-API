package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.entity.DiningTable;
import com.enigma.warungmakanbahariapi.model.request.DiningTableRequest;
import com.enigma.warungmakanbahariapi.repository.DiningTableRepository;
import com.enigma.warungmakanbahariapi.service.DiningTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class DiningTableServiceImpl implements DiningTableService {

    private final DiningTableRepository diningTableRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DiningTable getOrSave(DiningTableRequest request) {
        Optional<DiningTable> optionalTable = diningTableRepository.findByTableName(request.getTableName());
        if (!optionalTable.isPresent()) {
            DiningTable buildTable = DiningTable.builder()
                    .tableName(request.getTableName())
                    .build();
            return diningTableRepository.save(buildTable);
        }else {
            return optionalTable.get();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<DiningTable> getAll() {
        List<DiningTable> findALl = diningTableRepository.findAll();
        return findALl;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DiningTable getById(String id) {
        Optional<DiningTable> optionalDiningTable = diningTableRepository.findById(id);
        if (!optionalDiningTable.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"not found");
        return optionalDiningTable.get();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DiningTable update(DiningTable request) {
        getById(request.getId());
        DiningTable buildDiningTable = DiningTable.builder()
                .id(request.getId())
                .tableName(request.getTableName())
                .build();
        return buildDiningTable;
    }

    @Override
    public String delete(String id) {
        DiningTable diningTable = getById(id);
        diningTableRepository.delete(diningTable);
        return "OK";
    }
}
