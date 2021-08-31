package com.SpringCrud.controller;

import com.SpringCrud.dto.CustomersDto;
import com.SpringCrud.dto.Visibility;
import com.SpringCrud.model.Customers;
import com.SpringCrud.repository.Repository;
import com.SpringCrud.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ModelMapper modelMapper;
    Repository repository;
    CustomerService customerService;

    public Controller(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @JsonView(Visibility.Details.class)
    @GetMapping("/customers")
    public List<CustomersDto> getAllCustomers() {

        return customerService.getAllCustomers().stream().map(customers ->
                modelMapper.map(customers, CustomersDto.class)).collect(Collectors.toList());
    }

    @JsonView(Visibility.Details.class)
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomersDto> getCustomerById(@PathVariable("id") long id) {
        Customers customers = customerService.getCustomerById(id);
        CustomersDto customerResponce = modelMapper.map(customers, CustomersDto.class);
        return ResponseEntity.ok().body(customerResponce);
    }

    @JsonView(Visibility.Details.class)
    @PostMapping("/customers")
    public ResponseEntity<CustomersDto> createCustomers(@Validated(Visibility.New.class)
                                                            @RequestBody CustomersDto customersDto) {
        Customers newCustomers = modelMapper.map(customersDto, Customers.class);
        Customers customers = customerService.createCustomers(newCustomers);
        CustomersDto customerResponce = modelMapper.map(customers, CustomersDto.class);
        return new ResponseEntity<>(customerResponce, HttpStatus.CREATED);
    }

    @JsonView(Visibility.Details.class)
    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomersDto> updateCustomer(@Validated(Visibility.Update.class)
                                                           @PathVariable("id") long id,
                                                       @RequestBody CustomersDto customersDto) {
        Customers updatedCustomer = modelMapper.map(customersDto, Customers.class);
        Customers customers = customerService.updateCustomer(id, updatedCustomer);
        CustomersDto customerResponce = modelMapper.map(customers, CustomersDto.class);
        return ResponseEntity.ok().body(customerResponce);

    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<CustomersDto> deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
