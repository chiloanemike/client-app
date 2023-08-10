package com.investec.clientapp.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ValidationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PhoneNumberTest {

    private SouthAfricanPhoneNumberValidator southAfricanPhoneNumberValidator;

    @Test
    void invalidPhoneNumber(){

        assertThrows(ValidationException.class, ()-> {
            southAfricanPhoneNumberValidator.isValidSouthAfricanPhoneNumber("0838324");
    });

}
    
    @Test
    void validPhoneNumber(){

        Boolean isValid = southAfricanPhoneNumberValidator.isValidSouthAfricanPhoneNumber("0727388632");

        assertTrue(isValid);
    }



    
}
