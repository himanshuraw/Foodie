package com.himanshu.foodie.service;

import com.himanshu.foodie.dto.CustomerRequest;
import com.himanshu.foodie.dto.CustomerResponse;
import com.himanshu.foodie.dto.LoginRequest;
import com.himanshu.foodie.entity.Customer;
import com.himanshu.foodie.exception.CustomerNotFoundException;
import com.himanshu.foodie.helper.EncryptionService;
import com.himanshu.foodie.helper.JWTHelper;
import com.himanshu.foodie.mapper.CustomerMapper;
import com.himanshu.foodie.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
        customer.setPassword(encryptionService.encode(customer.getPassword()));
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
                        format("Cannot find Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public String login(@Valid LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if (!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }
        return jwtHelper.generateToken(request.email());
    }

    public String deleteCustomer(String email) {
        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customerRepo.delete(customer);
        return "Customer ( " + email + " ) deleted";
    }

    public String validateAndExtractUsername(String authHeader) throws BadRequestException {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadRequestException("Invalid Authorization header");
        }
        String token = authHeader.substring(7);

        if (!jwtHelper.validateToken(token)) {
            throw new BadRequestException("Invalid JWT token");
        }

        return jwtHelper.extractUsername(token);
    }

    public String updateCustomer(String email, @Valid CustomerRequest request) {
        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        if (request.firstName() != null) {
            customer.setFirstName(request.firstName());
        }
        if (request.lastName() != null) {
            customer.setLastName(request.lastName());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
        if (request.pincode() != null) {
            customer.setPincode(request.pincode());
        }

        customerRepo.save(customer);
        return "Customer details updated";
    }
}
