package com.SpringCrud.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@RequiredArgsConstructor


public class CustomersDto {



    @NotNull(groups = {Visibility.New.class})
    @Null(groups = {Visibility.Update.class})
    @JsonView({Visibility.Details.class})
    private long id;

    @NotNull(groups = {Visibility.New.class, Visibility.Update.class})
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @JsonView({Visibility.Details.class})
    private String fullName;

    @NotNull(groups = {Visibility.New.class})
    @Null(groups = {Visibility.Update.class})
    @Email(groups = {Visibility.New.class})
    @JsonView({Visibility.Details.class})
    private String email;

    @NotNull(groups = {Visibility.New.class, Visibility.Update.class})
    @Size(max = 13)
    @Pattern(regexp = "^$|\\+\\d{6,12}")
    @JsonView({Visibility.Details.class})
    private String phone;

    private boolean isActive = true;

    @LastModifiedDate
    private Date updated = new Date();


}
