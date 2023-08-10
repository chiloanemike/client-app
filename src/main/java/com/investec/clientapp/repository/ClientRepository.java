package com.investec.clientapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.investec.clientapp.domain.Client;

@Repository
public interface ClientRepository  extends JpaRepository<Client,Long>{
    
    Optional<Client> findByIdNumber(String idNumber);

    Optional<Client> findByLastName(String lastName);

    Optional<Client> findByMobileNumber(String mobileNumber);
}
