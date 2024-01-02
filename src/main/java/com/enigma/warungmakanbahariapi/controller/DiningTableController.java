package com.enigma.warungmakanbahariapi.controller;

import com.enigma.warungmakanbahariapi.entity.DiningTable;
import com.enigma.warungmakanbahariapi.model.request.DiningTableRequest;
import com.enigma.warungmakanbahariapi.model.response.WebResponse;
import com.enigma.warungmakanbahariapi.service.DiningTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/tables")
@RestController
@RequiredArgsConstructor
public class DiningTableController {

    private final DiningTableService diningTableService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DiningTableRequest request){
        DiningTable diningTable = diningTableService.getOrSave(request);
        WebResponse<DiningTable> response = WebResponse.<DiningTable>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfuly create new table")
                .data(diningTable)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DiningTable request){
        DiningTable diningTable = diningTableService.update(request);
        WebResponse<DiningTable> response = WebResponse.<DiningTable>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly update table")
                .data(diningTable)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        String delete = diningTableService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly delete")
                .data(delete)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        DiningTable getById = diningTableService.getById(id);
        WebResponse<DiningTable> response = WebResponse.<DiningTable>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly delete")
                .data(getById)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<DiningTable> findAll = diningTableService.getAll();
        WebResponse<List<DiningTable> > response = WebResponse.<List<DiningTable> >builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get all table")
                .data(findAll)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
