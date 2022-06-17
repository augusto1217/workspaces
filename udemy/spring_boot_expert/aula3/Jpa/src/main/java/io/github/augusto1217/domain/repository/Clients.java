package io.github.augusto1217.domain.repository;

import io.github.augusto1217.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Repository
public class Clients implements Serializable {

    private static final long serialVersionUID = 3413137384422993826L;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Client save(Client client) {
        entityManager.persist(client);
        return client;
    }

    @Transactional
    public Client update(Client client) {
      entityManager.merge(client);
        return client;
    }

    @Transactional
    public void delete(Client client) {
        if(!entityManager.contains(client)) {
            client = entityManager.merge(client);
        }
        entityManager.remove(client);
    }

    @Transactional
    public void delete(Integer id) {
        Client client = entityManager.find(Client.class, id);
        delete(client);
    }

    @Transactional(readOnly = true)
    public List<Client> searchForName(String name) {
        String jpql = " select c from Client c where c.name like :name ";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return entityManager.createQuery("from Client", Client.class).getResultList();
    }

}
