package com.enigma.warungmakanbahariapi.controller;

import com.enigma.warungmakanbahariapi.entity.Customer;
import com.enigma.warungmakanbahariapi.model.request.CustomerRequest;
import com.enigma.warungmakanbahariapi.model.response.CustomerResponse;
import com.enigma.warungmakanbahariapi.model.response.WebResponse;
import com.enigma.warungmakanbahariapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CustomerRequest request){
        CustomerResponse updateCustomer = customerService.update(request);
        WebResponse<CustomerResponse> response = WebResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get all customers")
                .data(updateCustomer)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        String deleteCustomer = customerService.delete(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get all customers")
                .data(deleteCustomer)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Customer optionalCustomer = customerService.getById(id);
        CustomerResponse findByIdCustomer = CustomerResponse.builder()
                .id(optionalCustomer.getId())
                .name(optionalCustomer.getName())
                .phoneNumber(optionalCustomer.getPhoneNumber())
                .email(optionalCustomer.getUserCredential().getEmail())
                .build();


        WebResponse<CustomerResponse> response = WebResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get all customers")
                .data(findByIdCustomer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<CustomerResponse> customerAll = customerService.getAll();
        WebResponse<List<CustomerResponse>> response = WebResponse.<List<CustomerResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get all customers")
                .data(customerAll)
                .build();
        return ResponseEntity.ok(response);
    }

}
