package io.github.augusto1217.domain.repository;

import io.github.augusto1217.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesDao extends JpaRepository<Cliente, Integer>, MyQuerysClients {

    @Query(value = ENCONTRAR_POR_NOME)
    List<Cliente> encontrarPorNome( @Param("nome") String nome );

    @Query(" delete from Cliente c where c.nome =:nome  ")
    @Modifying
    void deleteByNome(String nome);

   @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
   Cliente findClientFetchOrders(@Param("id") Integer id);


}
