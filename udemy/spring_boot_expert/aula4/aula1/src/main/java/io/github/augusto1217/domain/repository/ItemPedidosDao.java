package io.github.augusto1217.domain.repository;

import io.github.augusto1217.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidosDao extends JpaRepository<ItemPedido, Integer> {
}
