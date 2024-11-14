package com.himanshu.foodie.service;

import com.himanshu.foodie.dto.CustomerRequest;
import com.himanshu.foodie.dto.CustomerResponse;
import com.himanshu.foodie.entity.Customer;
import com.himanshu.foodie.exception.CustomerNotFoundException;
import com.himanshu.foodie.helper.EncryptionService;
import com.himanshu.foodie.helper.JWTHelper;
import com.himanshu.foodie.mapper.CustomerMapper;
import com.himanshu.foodie.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        customerRepo.save(customer);
        return "Customer created";
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    private Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }
}
