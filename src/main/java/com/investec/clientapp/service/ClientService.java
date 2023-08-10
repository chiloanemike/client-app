package com.investec.clientapp.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.investec.clientapp.domain.Client;
import com.investec.clientapp.dto.ClientDto;
import com.investec.clientapp.exception.NotFoundException;
import com.investec.clientapp.repository.ClientRepository;
import com.investec.clientapp.utils.SouthAfricanIdNumberValidator;
import com.investec.clientapp.utils.SouthAfricanPhoneNumberValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

    private SouthAfricanIdNumberValidator southAfricanIdNumberValidator;
    private final ClientRepository clientRepository;

    public ClientDto createClient(ClientDto clientDto) {

        try {

            Optional<Client> existingClient = clientRepository.findByIdNumber(clientDto.getIdNumber());
            SouthAfricanIdNumberValidator.verifyIdNumber(clientDto.getIdNumber());

            if (clientDto.getMobileNumber().length() > 0)
                SouthAfricanPhoneNumberValidator.isValidSouthAfricanPhoneNumber(clientDto.getMobileNumber());

            if (existingClient.isPresent())
                throw new ValidationException("Client already exists in our records");

            Client client = Client.builder()
                    .firstName(clientDto.getFirstName())
                    .lastName(clientDto.getLastName())
                    .mobileNumber(clientDto.getMobileNumber())
                    .idNumber(clientDto.getIdNumber())
                    .physicalAddress(clientDto.getPhysicalAddress())
                    .build();

            clientRepository.save(client);
        } catch (ValidationException exception) {
            throw exception;
        }

        return clientDto;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client updateClient(String id, ClientDto updateDto) {
        Optional<Client> existingClientOptional = clientRepository.findByIdNumber(id);

        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();

            if (updateDto.getFirstName() != null && !updateDto.getFirstName().isEmpty()) {
                existingClient.setFirstName(updateDto.getFirstName());
            }

            if (updateDto.getLastName() != null && !updateDto.getLastName().isEmpty()) {
                existingClient.setLastName(updateDto.getLastName());
            }

            if (updateDto.getMobileNumber() != null && !updateDto.getMobileNumber().isEmpty()) {
                SouthAfricanPhoneNumberValidator.isValidSouthAfricanPhoneNumber(updateDto.getMobileNumber());
                existingClient.setMobileNumber(updateDto.getMobileNumber());
            }

            if (updateDto.getIdNumber() != null && !updateDto.getIdNumber().isEmpty()) {
                southAfricanIdNumberValidator.verifyIdNumber(updateDto.getIdNumber());
                existingClient.setIdNumber(updateDto.getIdNumber());
            }

            if (updateDto.getPhysicalAddress() != null && !updateDto.getPhysicalAddress().isEmpty()) {
                existingClient.setPhysicalAddress(updateDto.getPhysicalAddress());
            }

            return clientRepository.save(existingClient);
        } else {
            throw new NotFoundException("Client not found");
        }
    }

    public Client findByLastName(String lastName) {
        Optional<Client> existingUser = clientRepository.findByLastName(lastName);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        throw new ValidationException("User doesnt Doesnt't Exist");
    }

    public Client findByPhoneNumber(String phoneNumber) {
        Optional<Client> existingUser = clientRepository.findByMobileNumber(phoneNumber);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        throw new ValidationException("User doesnt Doesnt't Exist");
    }

    public Client findById(String idNumber) {
        Optional<Client> existingUser = clientRepository.findByIdNumber(idNumber);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        throw new NotFoundException("Client not found");
    }

    public void deleteClientById(String id) {
        Optional<Client> existingClient = clientRepository.findByIdNumber(id);

        if (existingClient.isPresent()) {
            clientRepository.delete(existingClient.get());
        } else {
            throw new NotFoundException("Client not found");
        }

    }

}
