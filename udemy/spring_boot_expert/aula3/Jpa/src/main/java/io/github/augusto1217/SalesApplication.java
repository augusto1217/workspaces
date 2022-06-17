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

            searchClients(clients);

            excludingClients(clients);

            gettingClients(clients);

        };
    }

    private void gettingClients(Clients clients) {
        printTitle("Listando clientes: ");
        getClients(clients);
    }

    private void excludingClients(Clients clients) {
        printTitle("Deletando clientes: ");
        clients.getAllClients().forEach(client -> {
            if(client.getName().contains("é")) {
                clients.delete(client.getId());
            }
        });
    }

    private void searchClients(Clients clients) {
        printTitle("Buscando clientes: ");
        clients.searchForName("é").forEach(System.out::println);
    }

    private void updateClients(Clients clients, List<Client> getAllClients) {
        printTitle("Atualizando Clientes");
        getAllClients.forEach(client -> {
            client.setName(client.getName() + " Brazil");
            clients.update(client);
        });
    }

    private void saveClients(Clients clients) {
        printTitle("Salvando Clientes:");
        clients.save(new Client("Amélia"));
        clients.save(new Client("Amanda"));
    }

    private void printTitle(String title) {
        System.out.println("\n"+title);
    }

    private List<Client> getClients(Clients clients) {
        List<Client> getAllClients = clients.getAllClients();
        getAllClients.forEach(System.out::println);
        return getAllClients;
    }
}
