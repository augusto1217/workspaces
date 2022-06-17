package io.github.augusto1217.domain.repository;

import io.github.augusto1217.domain.entity.Cliente;
import io.github.augusto1217.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosDao extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
