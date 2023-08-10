package com.investec.clientapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {


    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String idNumber;

    private String physicalAddress;

}
