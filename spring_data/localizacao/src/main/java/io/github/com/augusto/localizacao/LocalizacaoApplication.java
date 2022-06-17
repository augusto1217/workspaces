package io.github.com.augusto.localizacao;

import io.github.com.augusto.localizacao.domain.entity.Cidade;
import io.github.com.augusto.localizacao.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {
		cidadeService.setDadosCidade();
		cidadeService.listarTodasCidades();
		cidadeService.listarCidadesPorNome("Natal");
		cidadeService.listarCidadesPorQtdHabitantes(10540000L);
		cidadeService.listarCidadesPorNome("Rio");
		cidadeService.listarCidadesPorNomeInicio("B");
		cidadeService.listarCidadesPorNomeFim("a");
		cidadeService.listarCidadesPorNomeContem("sí");
		cidadeService.listarCidadesPorNomeLike("%sí%");
		cidadeService.listarCidadesPorQtdHabitantesLess(10540000L);
		cidadeService.listarCidadesPorQtdHabitantesGreater(10540000L);
		cidadeService.listarCidadesPorQtdHabitantesLessEqual(10540000L);
		cidadeService.listarCidadesPorQtdHabitantesLessAndNomeLike(10540000L,"bra");
		cidadeService.listarCidadesPorQtdHabitantesGreaterAndNomeLike(10540000L, "bra");
		cidadeService.listarCidadesPorNomeLikePage("%a%");
		var cidade = new Cidade(null,"Porto",null);
		cidadeService.filtroDinamico(cidade).forEach(System.out::println);

	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
