package com.investec.clientapp.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.investec.clientapp.domain.Client;
import com.investec.clientapp.dto.ClientDto;
import com.investec.clientapp.exception.NotFoundException;
import com.investec.clientapp.service.ClientService;
import com.investec.clientapp.utils.validatidateClientDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;
    @GetMapping
    public  ResponseEntity<List<Client>>  getAllClients(){
        return ResponseEntity.status(200).body(clientService.getAllClients());
    } 

    @GetMapping("/lastName")
    public ResponseEntity<Client> getClientByFirstName (@RequestParam String lastName){
        try{
        return ResponseEntity.status(200).body(clientService.findByLastName(lastName));
        }
        catch(Exception ex){
            throw ex;
        }
    }
     @GetMapping("/phone")
    public ResponseEntity<Client> getClientByPhoneNumber (@RequestParam String phoneNumber){
        try{
        return ResponseEntity.status(200).body(clientService.findByPhoneNumber(phoneNumber));
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @GetMapping("/idnumber")
    public ResponseEntity<Client> getClientByLastName (@RequestParam String idnumber){
        try{
        return ResponseEntity.status(200).body(clientService.findById(idnumber));
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientDto clientDto){
        try{
            validatidateClientDto.validateClientDto(clientDto);
            return ResponseEntity.status(201).body(clientService.createClient(clientDto));
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable String id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

     @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody @Valid ClientDto updatedClientDto) {
        try {
            Client updatedClient = clientService.updateClient(id, updatedClientDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedClient);
        } catch (ValidationException ex) {
            throw ex;
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

   
}
