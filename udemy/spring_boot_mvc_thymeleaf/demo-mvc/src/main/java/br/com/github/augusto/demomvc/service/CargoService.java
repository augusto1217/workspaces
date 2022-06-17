package br.com.github.augusto.demomvc.service;

import java.util.List;

import br.com.github.augusto.demomvc.domain.Cargo;
import br.com.github.augusto.demomvc.util.PaginacaoUtil;

public interface CargoService {

    void salvar(Cargo cargo);
    void editar(Cargo cargo);
    void excluir(Long id);
    Cargo buscarPorId(Long id);
    List<Cargo> buscarTodos();
    boolean cargoTemFuncionario(Long id);
    PaginacaoUtil<Cargo> buscaPorPagina(int pagina, String direcao);
    
}
