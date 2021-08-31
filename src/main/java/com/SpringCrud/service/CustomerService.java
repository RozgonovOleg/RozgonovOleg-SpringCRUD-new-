package com.SpringCrud.service;

import com.SpringCrud.exception.IdNotFoundException;
import com.SpringCrud.model.Customers;
import com.SpringCrud.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final Repository repository;

    public CustomerService(Repository repository) {
        super();
        this.repository = repository;
    }

    public List<Customers> getAllCustomers() {
        if (repository.findAll().isEmpty()){
            throw new IdNotFoundException("No one id not found");
        }

        return repository.findAll();
    }

    public Customers getCustomerById(long id) {

        Customers customers = repository.findById(id).orElseThrow(() -> new IdNotFoundException("id not found"));
        return customers;

    }

    public Customers createCustomers(Customers customers) {
        return repository.save(customers);
    }

    public Customers updateCustomer(long id, Customers customersRequest) {
        Customers customers = repository.findById(id).orElseThrow(() -> new IdNotFoundException("id not found"));
        customers.setFullName(customersRequest.getFullName());
        customers.setPhone(customersRequest.getPhone());
        customers.setUpdated(customersRequest.getUpdated());

        return repository.save(customers);
    }

    public void deleteCustomer(long id){
        Customers customers = repository.findById(id).orElseThrow(() -> new IdNotFoundException("id not found"));
        repository.delete(customers);
    }


}
