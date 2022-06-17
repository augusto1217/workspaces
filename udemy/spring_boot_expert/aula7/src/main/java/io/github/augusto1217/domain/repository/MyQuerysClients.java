package io.github.augusto1217.domain.repository;

public interface MyQuerysClients {
    String ENCONTRAR_POR_NOME = " select c from Cliente c where c.nome like :nome ";
    String ENCONTRAR_POR_NOME_SQL_NATIVO = " select * from Cliente c where c.nome like '%:nome%' ";
}
