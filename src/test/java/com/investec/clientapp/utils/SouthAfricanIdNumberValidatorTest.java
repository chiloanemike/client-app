package com.investec.clientapp.utils;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ValidationException;

import org.junit.jupiter.api.Test;


public class SouthAfricanIdNumberValidatorTest {

    @Test
    public void testValidIdNumber() {
       

        Boolean isValid = SouthAfricanIdNumberValidator.verifyIdNumber("0006035646089");

        assertTrue(isValid);
    }

    @Test
    public void testInvalidIdNumber() {
        String idNumber = "1234567890123";

     assertThrows(ValidationException.class, () -> {
            SouthAfricanIdNumberValidator.verifyIdNumber(idNumber);
        });
    }
}
