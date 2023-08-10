package com.investec.clientapp.utils;

import java.util.regex.Pattern;

import javax.validation.ValidationException;


public class SouthAfricanPhoneNumberValidator {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(
            "^\\+27\\d{9}$|^0[1-9]\\d{8}$"
    );

    public static boolean isValidSouthAfricanPhoneNumber(String phoneNumber) {
        
        if( PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches() ){
            return true;
        }
        else{
            throw new ValidationException("The phone number provided is not valid");
        }
    }
}

