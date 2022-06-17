package io.github.augusto1217.services;

import io.github.augusto1217.domain.entity.Pedido;
import io.github.augusto1217.domain.enums.StatusPedido;
import io.github.augusto1217.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
