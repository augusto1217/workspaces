package io.github.augusto1217.respository;

import io.github.augusto1217.model.Client;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class ClientRepository implements Serializable {

    public void persist(Client client) {
        // access base and save client
    }
}
