package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.entity.DiningTable;
import com.enigma.warungmakanbahariapi.model.request.DiningTableRequest;
import com.enigma.warungmakanbahariapi.repository.DiningTableRepository;
import com.enigma.warungmakanbahariapi.service.DiningTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class DiningTableServiceImpl implements DiningTableService {

    private final DiningTableRepository diningTableRepository;
    @Override
    public DiningTable create(DiningTableRequest request) {
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
}
