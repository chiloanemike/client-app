package com.investec.clientapp.utils;

import com.investec.clientapp.dto.ClientDto;

import javax.validation.ValidationException;

import org.springframework.util.StringUtils;

public class validatidateClientDto {
    public static void validateClientDto(ClientDto clientDto) throws IllegalArgumentException {
        if (!StringUtils.hasText(clientDto.getFirstName())) {
            throw new ValidationException("First name must not be null or empty");
        }
        if (!StringUtils.hasText(clientDto.getLastName())) {
            throw new IllegalArgumentException("Last name must not be null or empty");
        }
        
    }
}
