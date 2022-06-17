package io.github.augusto1217.service;

import io.github.augusto1217.model.Client;
import io.github.augusto1217.respository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class ClientServices implements Serializable {

    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) {
        validateClient(client);
        clientRepository.persist(client);
    }

    public void validateClient(Client client) {
        //aplica validações
    }

}
