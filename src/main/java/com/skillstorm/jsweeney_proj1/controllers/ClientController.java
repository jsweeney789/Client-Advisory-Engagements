package com.skillstorm.jsweeney_proj1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.jsweeney_proj1.models.Client;
import com.skillstorm.jsweeney_proj1.services.ClientService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService service; // TODO: check on making other services/repos and stuff final because they just should be

    public ClientController(ClientService service) {
        this.service = service;
    }

    // haven't made tester for this yet because this isn't the most basic CRUD operation, will look into as beginning of creating real functionalities.
    @GetMapping()
    public ResponseEntity<List<Client>> getAllClients() {
        List clients = service.getAllClients();
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        // TODO: USE GLOBAL EXCEPTION HANDLER INSTEAD OF TRY CATCHING HERE
        Client client;
        try {
            client = service.getClientById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }    

    @PostMapping()
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client newClient) {
        Client client = service.saveClient(newClient);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(client, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody Client newClient) {
        Client client = service.saveClient(newClient);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(client, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable Long id) {
        boolean foundClient;
        try {
            foundClient = service.deleteClient(id);
        } catch (NoSuchElementException e) {
            foundClient = false;
            return new ResponseEntity<>(foundClient, HttpStatus.NOT_FOUND);
        }
        if (!foundClient) {
            return new ResponseEntity<>(foundClient, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundClient, HttpStatus.NO_CONTENT);
    }
    
}
