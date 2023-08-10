package com.investec.clientapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


import java.util.Optional;

import javax.validation.ValidationException;

import com.investec.clientapp.domain.Client;
import com.investec.clientapp.dto.ClientDto;
import com.investec.clientapp.exception.NotFoundException;
import com.investec.clientapp.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private ClientService clientService;

    ClientDto testClientDto = new ClientDto("mike", "chiloane", "0727388632", "0006035646089", "Mood");

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.clientService = new ClientService(clientRepository);
    }

    @Test
    void insertClientSuccess() {
        ClientDto clientDto = new ClientDto("mike", "chiloane", "0727388632", "0006035646089", "Morolong");

        ClientDto testDto = clientService.createClient(clientDto);

        assertEquals(clientDto, testDto);

    }

    @Test
    void insertClientFail() {
        ClientDto clientDto = new ClientDto("mike", "chiloane", "07273632", "000635646089", "Morolong");
        assertThrows(ValidationException.class, () -> clientService.createClient(clientDto));
    }

    @Test
    void deleteClientSuccess() {
        ClientDto newClient = clientService.createClient(testClientDto);
        assertThrows(NotFoundException.class, () -> clientService.findById(newClient.getIdNumber()));
        assertThrows(NotFoundException.class, () -> clientService.deleteClientById(newClient.getIdNumber()));
    }

    @Test
    void updateClient() {

        Client savedClient = new Client();
        savedClient.setId(1L); // Set ID
        savedClient.setFirstName("Mike");
        savedClient.setLastName("Chiloane");
        savedClient.setMobileNumber("0727388632");
        savedClient.setIdNumber("0006035646089");
        savedClient.setPhysicalAddress("Morolong");

        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(savedClient);
        when(clientRepository.findByIdNumber(Mockito.anyString())).thenReturn(Optional.of(savedClient));

        savedClient.setPhysicalAddress(testClientDto.getPhysicalAddress());
        when(clientRepository.findByIdNumber(Mockito.anyString())).thenReturn(Optional.of(savedClient));

        var updatedClient = clientService.updateClient(savedClient.getIdNumber(),testClientDto) ;
        
        assertEquals(updatedClient.getPhysicalAddress(), testClientDto.getPhysicalAddress());

    }

}
