package io.github.augusto1217;

import io.github.augusto1217.domain.entity.Cliente;
import io.github.augusto1217.domain.entity.Pedido;
import io.github.augusto1217.domain.repository.ClientesDao;
import io.github.augusto1217.domain.repository.PedidosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SalesApplication {

   public static void main(String[] args) {
       SpringApplication.run(SalesApplication.class, args);
    }

    @Bean
    public CommandLineRunner execute(
            @Autowired ClientesDao clientesDao,
            @Autowired PedidosDao pedidosDao
    ) {
        return args -> {
            Cliente client = new Cliente("Amélia");
            saveClients(clientesDao, client);
            savePedidos(pedidosDao, client);

           Cliente clientRet = clientesDao.findClientFetchOrders(client.getId());
           System.out.println(clientRet);
           System.out.println(clientRet.getPedidos());

           pedidosDao.findByCliente(client).forEach(System.out::println);

        };
    }

    private void saveClients(ClientesDao clients, Cliente client) {
        printTitle("Salvando Clientes:");
        clients.save(client);
    }

    private List<Cliente> getClients(ClientesDao clients) {
        List<Cliente> getAllClients = clients.findAll();
        getAllClients.forEach(System.out::println);
        return getAllClients;
    }

    private void encontrarPorNome(ClientesDao clients) {
        printTitle("Encontrar por nome sql nativo: ");
        clients.encontrarPorNome("%A%").forEach(System.out::println);
    }

    private void savePedidos(PedidosDao dao, Cliente client){
       printTitle("Saving Orders");

        Pedido o = new Pedido();
        o.setCliente(client);
        o.setDataPedido(LocalDate.now());
        o.setTotal(new BigDecimal("10000.00"));
        dao.save(o);
    }

    private void printTitle(String title) {
        System.out.println("\n"+title);
    }
}
