package com.conversorexcel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.conversorexcel.controller.AlunoController;
import com.conversorexcel.domain.Aluno;
import com.conversorexcel.repository.AlunoRepository;

@SpringBootApplication
public class TesteJavaExcelApplication implements CommandLineRunner {
	
	@Autowired
	private AlunoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteJavaExcelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Objeto para manipulação de arquivos
		AlunoController controle = new AlunoController(repository);
		
		// extrai os dados da base importação para uma lista
		List<Aluno> listaAlunos = controle.geraAlunos();
		
		//Salva a lista no banco dedados
		//repository.saveAll(listaAlunos);
		
		//Gera arquivo da aba 1 com base no banco de dados
		controle.geraAba01();
		
		//Gera arquivo da aba 2 com base no banco de dados
		controle.geraAba02();
		
		//Gera arquivo da aba 3 com base no banco de dados
		controle.geraAba03();
	}
	
	

}
