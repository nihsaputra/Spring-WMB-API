package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.Customer;

import java.util.List;

public interface CustomerService {
    void create(Customer customer);

    List<Customer> getAll();
}
