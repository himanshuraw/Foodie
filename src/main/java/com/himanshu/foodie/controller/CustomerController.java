package com.himanshu.foodie.controller;

import com.himanshu.foodie.dto.CustomerResponse;
import com.himanshu.foodie.service.CustomerService;
import com.himanshu.foodie.dto.CustomerRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer((request)));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String authHeader, @RequestBody CustomerRequest request) throws BadRequestException {
        String email = customerService.validateAndExtractUsername(authHeader);

        return ResponseEntity.ok(customerService.updateCustomer(email, request));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String authHeader) throws BadRequestException {
        String email = customerService.validateAndExtractUsername(authHeader);
        customerService.deleteCustomer(email);
        return ResponseEntity.ok(customerService.deleteCustomer(email));
    }
}
