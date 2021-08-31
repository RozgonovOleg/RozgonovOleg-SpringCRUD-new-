package com.SpringCrud.repository;

import com.SpringCrud.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Customers, Long> {


}
