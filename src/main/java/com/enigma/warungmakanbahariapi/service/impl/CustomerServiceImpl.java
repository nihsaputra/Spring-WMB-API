package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.constan.ERole;
import com.enigma.warungmakanbahariapi.entity.Customer;
import com.enigma.warungmakanbahariapi.entity.UserCredential;
import com.enigma.warungmakanbahariapi.model.request.CustomerRequest;
import com.enigma.warungmakanbahariapi.model.response.CustomerResponse;
import com.enigma.warungmakanbahariapi.repository.CustomerRepository;
import com.enigma.warungmakanbahariapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Customer request) {
        customerRepository.save(request);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerResponse update(CustomerRequest request) {
        Customer customerId = getById(request.getId());
        Customer buildCustomer = Customer.builder()
                .id(customerId.getId())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .userCredential(customerId.getUserCredential())
                .build();
        Customer saveCustomer = customerRepository.saveAndFlush(buildCustomer);

        return CustomerResponse.builder()
                .id(saveCustomer.getId())
                .name(saveCustomer.getName())
                .phoneNumber(saveCustomer.getPhoneNumber())
                .email(customerId.getUserCredential().getEmail())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delete(String id) {
        Customer customerId = getById(id);
        customerRepository.delete(customerId);
        return "OK";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CustomerResponse> getAll() {
        List<Customer> findAll = customerRepository.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : findAll) {
            CustomerResponse buildCustomerResponse = CustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .phoneNumber(customer.getPhoneNumber())
                    .email(customer.getUserCredential().getEmail())
                    .build();
            customerResponses.add(buildCustomerResponse);
        }
        return customerResponses;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer getById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"customer not found");

        UserCredential principal =(UserCredential) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> listRole = principal.getRoles().stream().map(role -> role.getRole().name()).toList();
        List<String> roleCustomer = List.of(ERole.ROLE_CUSTOMER.toString());
        if (listRole.equals(roleCustomer)) {
            if (!principal.getId().equals(optionalCustomer.get().getUserCredential().getId()))
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "forbidden");
            return optionalCustomer.get();
        }
       return optionalCustomer.get();
    }
}
