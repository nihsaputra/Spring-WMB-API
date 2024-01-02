package com.enigma.warungmakanbahariapi.controller;

import com.enigma.warungmakanbahariapi.entity.DiningTable;
import com.enigma.warungmakanbahariapi.model.request.DiningTableRequest;
import com.enigma.warungmakanbahariapi.model.response.WebResponse;
import com.enigma.warungmakanbahariapi.service.DiningTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/tables")
@RestController
@RequiredArgsConstructor
public class DiningTableController {

    private final DiningTableService diningTableService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DiningTableRequest request){
        DiningTable diningTable = diningTableService.create(request);
        WebResponse<DiningTable> response = WebResponse.<DiningTable>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfuly create new table")
                .data(diningTable)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
