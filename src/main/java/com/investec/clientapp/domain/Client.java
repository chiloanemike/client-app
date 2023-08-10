package com.investec.clientapp.domain;

import java.time.LocalDateTime;

import javax.validation.ValidationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private String firstName;
  
    private String lastName;
   
    private String mobileNumber;
 
    private String idNumber;

    private String physicalAddress;
    private LocalDateTime dateAdded;

    @PrePersist
    private void setDateAdded(){
        this.dateAdded = LocalDateTime.now();
    }

  
}
