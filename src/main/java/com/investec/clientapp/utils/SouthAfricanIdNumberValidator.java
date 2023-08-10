package com.investec.clientapp.utils;

import org.asynchttpclient.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.validation.ValidationException;

public  class SouthAfricanIdNumberValidator {

    private static final String API_URL = "https://bdupreez-south-african-id-no-validator-v1.p.rapidapi.com/";
    private static final String API_KEY = "3feacd49c0msh2f286232c26a432p177160jsn167eac3e79bb";

    public static Boolean verifyIdNumber(String idNumber) {
        AsyncHttpClient client = new DefaultAsyncHttpClient();

        String requestBody = "{\"idno\": \"" + idNumber + "\"}";

        CompletableFuture<String> resultFuture = client.prepare("POST", API_URL)
                .setHeader("content-type", "application/json")
                .setHeader("X-RapidAPI-Key", API_KEY)
                .setHeader("X-RapidAPI-Host", "bdupreez-south-african-id-no-validator-v1.p.rapidapi.com")
                .setBody(requestBody)
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody);

        Boolean isValid = resultFuture.join().equals("{\"valid\":true}");

        resultFuture.whenComplete((result, throwable) -> {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        if(isValid){
            return true;
        }
        else throw new ValidationException("The provided ID number is not valid");

    }
}
