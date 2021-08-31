package com.SpringCrud.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor

@Entity
@Table(name = "customers")

public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date created = new Date();

    @Column(nullable = false)
    private Date updated = new Date();

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email", unique = true, updatable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "isActive")
    private boolean isActive = true;

}
