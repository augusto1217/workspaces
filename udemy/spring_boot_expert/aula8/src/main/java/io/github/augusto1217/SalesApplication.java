package io.github.augusto1217;

import io.github.augusto1217.domain.entity.Cliente;
import io.github.augusto1217.domain.entity.Produto;
import io.github.augusto1217.domain.repository.ClientesDao;
import io.github.augusto1217.domain.repository.ProdutosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class SalesApplication extends SpringBootServletInitializer {

    @Bean
    public CommandLineRunner commandLineRunnerClient(@Autowired ClientesDao clientesDao){
        return args -> {
            Cliente c = new Cliente("Jane Maia");
            c.setCpf("28508949073");
            clientesDao.save(c);
        };
    }

    @Bean
    public CommandLineRunner commandLineRunnerProduto(@Autowired ProdutosDao produtosDao){
        return args -> {
            Produto p = new Produto();
            p.setNome("Teclado");
            p.setDescricao("101 Teclas ABTN2");
            p.setPreco(new BigDecimal(240.00));
            produtosDao.save(p);
        };
    }

   public static void main(String[] args) {
       SpringApplication.run(SalesApplication.class, args);
   }

}
