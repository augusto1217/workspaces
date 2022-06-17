package br.com.github.augusto.demomvc.dao;

import java.util.List;

import br.com.github.augusto.demomvc.domain.Cargo;
import br.com.github.augusto.demomvc.util.PaginacaoUtil;

public interface CargoDao {
    
    void save(Cargo cargo);
    void update(Cargo cargo);
    void delete(Long id);
    Cargo findById(Long id);
    List<Cargo> findAll();
    PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}
