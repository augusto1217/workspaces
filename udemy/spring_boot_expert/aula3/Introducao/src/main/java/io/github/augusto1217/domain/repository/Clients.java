package io.github.augusto1217.domain.repository;

import io.github.augusto1217.domain.comands.ClientsCommands;
import io.github.augusto1217.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clients extends ClientsCommands implements Serializable {

    private static final long serialVersionUID = 3413137384422993826L;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public Client update(Client client) {
        jdbcTemplate.update(UPDATE, new Object[]{client.getName(), client.getId()});
        return client;
    }

    public void delete(Client client) {
        delete(client.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Client> searchForName(String name) {
        return jdbcTemplate.query(SELECT_FOR_NAME.concat(" where name like ? "), new Object[]{
                "%" + name + "%"}, getRowMapper());
    }

    private RowMapper<Client> getRowMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Client(resultSet.getInt("id"), resultSet.getString("name"));
            }
        };
    }

    public List<Client> getAllClients() {
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

}
