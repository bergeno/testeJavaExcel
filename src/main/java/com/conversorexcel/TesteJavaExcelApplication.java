package com.conversorexcel;

import java.time.LocalDate;
import java.util.Iterator;
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
		LocalDate date = LocalDate.now();
		Aluno aluno1 = new Aluno(12L, "Rafael", 'M',date , 10.00,8.00 , 9.00);
//		repository.save(aluno1); 
		List<Aluno> alunos = repository.findAll();
		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
		
		AlunoController controle = new AlunoController();
		List<String> listaLinhas = controle.geraLinhas();
		for (String linha : listaLinhas) {
			System.out.print(linha);
		}
		
	}
	
	

}
