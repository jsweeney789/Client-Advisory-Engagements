package com.skillstorm.jsweeney_proj1.services;

import java.util.List;
import java.util.Optional;

import com.skillstorm.jsweeney_proj1.models.Client;
import com.skillstorm.jsweeney_proj1.repositories.ClientRepository;

public class ClientService {
    
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // for most basic CRUD we want 4 methods
    // findAll() clients
    // findById() client
    // saveClient() works for both create and update of crud
    // deleteClient()

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Client getClientById(Long id) throws IllegalArgumentException {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new IllegalArgumentException("No client with id: " + id);
        }
    }

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    public boolean deleteClient(Long id) throws IllegalArgumentException {
        if (getClientById(id) == null) {
            throw new IllegalArgumentException("No client with id: " + id + " to remove.");
        }
        repository.deleteById(id);
        return true;
    }


    // overloading in case I ever need to delete a client with the client object, don't think it'll ever happen
    public boolean deleteClient(Client client) throws IllegalArgumentException {
        Long id = client.getClientId();
        if (getClientById(id) == null) {
            throw new IllegalArgumentException("No client with id: " + id + " to remove.");
        }
        repository.delete(client);
        return true;
    }
}
