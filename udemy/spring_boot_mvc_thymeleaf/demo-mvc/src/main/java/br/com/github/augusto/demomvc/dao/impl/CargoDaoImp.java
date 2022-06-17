package br.com.github.augusto.demomvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;


import br.com.github.augusto.demomvc.dao.AbastractDao;
import br.com.github.augusto.demomvc.dao.CargoDao;
import br.com.github.augusto.demomvc.domain.Cargo;
import br.com.github.augusto.demomvc.util.PaginacaoUtil;

@Repository
public class CargoDaoImp extends AbastractDao<Cargo, Long>  implements CargoDao {

    public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao) {

        int tamanho = 3;
        int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10

        List<Cargo> cargos = gEntityManager()
                                .createQuery("select c from Cargo c order by c.nome " + direcao, Cargo.class)
                                .setFirstResult(inicio)
                                .setMaxResults(tamanho)
                                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
        
        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, cargos);    
    }

    public long count() {
        return gEntityManager()
                .createQuery("select count(*) from Cargo", Long.class)
                .getSingleResult();

    }
    
    
}
