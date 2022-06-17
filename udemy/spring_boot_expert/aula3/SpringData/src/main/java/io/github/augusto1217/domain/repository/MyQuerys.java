package io.github.augusto1217.domain.repository;

public interface MyQuerys  {
    String ENCONTRAR_POR_NOME = " select c from Client c where c.name like :name ";
    String ENCONTRAR_POR_NOME_SQL_NATIVO = " select * from Client c where c.name like '%:name%' ";
}
