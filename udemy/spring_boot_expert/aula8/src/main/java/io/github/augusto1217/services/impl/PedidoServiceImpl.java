package io.github.augusto1217.services.impl;

import java.util.List;
import io.github.augusto1217.domain.entity.Cliente;
import io.github.augusto1217.domain.entity.ItemPedido;
import io.github.augusto1217.domain.entity.Pedido;
import io.github.augusto1217.domain.entity.Produto;
import io.github.augusto1217.domain.enums.StatusPedido;
import io.github.augusto1217.domain.repository.ClientesDao;
import io.github.augusto1217.domain.repository.ItemPedidosDao;
import io.github.augusto1217.domain.repository.PedidosDao;
import io.github.augusto1217.domain.repository.ProdutosDao;
import io.github.augusto1217.exception.PedidoNaoEncontradoException;
import io.github.augusto1217.exception.RegraNegocioException;
import io.github.augusto1217.rest.dto.ItemPedidoDTO;
import io.github.augusto1217.rest.dto.PedidoDTO;
import io.github.augusto1217.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosDao pedidosDao;
    private final ProdutosDao produtosDao;
    private final ClientesDao clientesDao;
    private final ItemPedidosDao itemPedidosDao;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idClient = dto.getIdCliente();
        Cliente client = clientesDao
                .findById(idClient)
                .orElseThrow(() -> returnMensagemNegocial("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(client);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItens(pedido, dto.getItems());
        pedidosDao.save(pedido);
        itemPedidosDao.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosDao.findByFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosDao
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosDao.save(pedido);
                }).orElseThrow(
                        () -> new PedidoNaoEncontradoException("Pedido não encontrado")
                );
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if(itens.isEmpty()) {
            throw returnMensagemNegocial("Não é possível realizar um pedido sem itens");
        }
        return itens
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getIdProduto();
                    Produto produto = produtosDao
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> returnMensagemNegocial("Código de produto inválido: " + idProduto)
                            );

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

    private RegraNegocioException returnMensagemNegocial(String message){
       return new RegraNegocioException(message);
    }
}
