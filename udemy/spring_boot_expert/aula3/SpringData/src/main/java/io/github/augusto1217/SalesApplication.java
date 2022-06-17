package io.github.augusto1217;

import io.github.augusto1217.domain.entity.Client;
import io.github.augusto1217.domain.repository.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SalesApplication {

   public static void main(String[] args) {
       SpringApplication.run(SalesApplication.class, args);
    }

    @Bean
    public CommandLineRunner execute(@Autowired Clients clients) {
        return args -> {
            saveClients(clients);

            List<Client> getAllClients = getClients(clients);

            updateClients(clients, getAllClients);

            getClients(clients);

            findByNameLike(clients);

            findByNameOrId(clients);

            findByNameOrIdOrderById(clients);

            findOneByName(clients);

            encontrarPorNome(clients);

            encontrarPorNomeNativeSql(clients);

            excludingClients(clients);

            excludingClientsQuery(clients,"Deb");

            gettingClients(clients);

            System.out.println(isExistsClient(clients, "Amanda Brazil"));

        };
    }

    private void saveClients(Clients clients) {
        printTitle("Salvando Clientes:");
        clients.save(new Client("Amélia"));
        clients.save(new Client("Amanda"));
        clients.save(new Client("Deborah"));
    }

    private List<Client> getClients(Clients clients) {
        List<Client> getAllClients = clients.findAll();
        getAllClients.forEach(System.out::println);
        return getAllClients;
    }

    private void encontrarPorNome(Clients clients) {
        printTitle("Encontrar por nome sql nativo: ");
        clients.encontrarPorNome("%A%").forEach(System.out::println);
    }

    private void encontrarPorNomeNativeSql(Clients clients) {
        printTitle("Encontrar por nome: ");
        clients.encontrarPorNomeNative("A").forEach(System.out::println);
    }

    private boolean isExistsClient(Clients clients, String name) {
       return clients.existsByName(name);
    }

    private void gettingClients(Clients clients) {
        printTitle("Listando clientes: ");
        getClients(clients);
    }

    private void findByNameLike(Clients clients) {
        printTitle("Buscando clientes like: ");
        clients.findByNameLike("%é%").forEach(System.out::println);
    }

    private void findByNameOrId(Clients clients) {
        printTitle("Buscando clientes findByNameOrId: ");
        clients.findByNameOrId(null,1).forEach(System.out::println);
    }

    private void findByNameOrIdOrderById(Clients clients) {
        printTitle("Buscando clientes findByNameOrIdOrderById: ");
        clients.findByNameOrIdOrderById("Deborah Brazil",null).forEach(System.out::println);
    }

    private void findOneByName(Clients clients) {
        printTitle("Buscando clientes findOneByName: ");
        System.out.println(clients.findOneByName("Deborah Brazil"));
    }

    private void updateClients(Clients clients, List<Client> getAllClients) {
        printTitle("Atualizando Clientes");
        getAllClients.forEach(client -> {
            client.setName(client.getName() + " Brazil");
            clients.save(client);
        });
    }

    private void excludingClients(Clients clients) {
        printTitle("Deletando clientes: ");
        clients.findAll().forEach(client -> {
            if(client.getName().contains("é")) {
                clients.delete(client);
            }
        });
    }

    private void excludingClientsQuery(Clients clients, String name) {
        printTitle("Deletando clientes Query: ");
        clients.findAll().forEach(client -> {
            if(client.getName().contains(name)) {
                clients.deleteByName(client.getName());
            }
        });
    }


    private void printTitle(String title) {
        System.out.println("\n"+title);
    }
}
