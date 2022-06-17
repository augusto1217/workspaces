package io.github.augusto1217.domain.repository;

import io.github.augusto1217.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clients extends JpaRepository<Client, Integer>, MyQuerys {

    Client findOneByName(String name);
    List<Client> findByNameLike(String name);
    List<Client> findByNameOrId(String name, Integer id);
    List<Client> findByNameOrIdOrderById(String name, Integer id);
    boolean existsByName(String name);

    @Query(value = ENCONTRAR_POR_NOME)
    List<Client> encontrarPorNome( @Param("name") String name );

    @Query(value = ENCONTRAR_POR_NOME_SQL_NATIVO, nativeQuery = true)
    List<Client> encontrarPorNomeNative( @Param("name") String name );

    @Query(" delete from Client c where c.name =:name  ")
    @Modifying
    void deleteByName(String name);
}
