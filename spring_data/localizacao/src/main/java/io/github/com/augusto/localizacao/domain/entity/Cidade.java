package io.github.com.augusto.localizacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

    @Id
    @Column(name = "id_cidade")
    private Long id;

    @Column(name = "desc_nome", length = 50)
    private String nome;

    @Column(name = "qtd_habitantes")
    private Long habitantes;
}
