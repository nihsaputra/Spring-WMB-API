package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.Customer;
import com.enigma.warungmakanbahariapi.model.request.CustomerRequest;
import com.enigma.warungmakanbahariapi.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    void create(Customer request);

    CustomerResponse update(CustomerRequest request);

    String delete(String id);

    List<CustomerResponse> getAll();

    Customer getById(String id);
}
