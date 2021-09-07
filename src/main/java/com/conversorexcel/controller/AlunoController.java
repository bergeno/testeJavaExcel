package com.conversorexcel.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.conversorexcel.domain.Aluno;
import com.conversorexcel.repository.AlunoRepository;

public class AlunoController {

	@Autowired
	private AlunoRepository repository;

	private String texto;
	private List<String> lista;
	private String path = "baseImportacao.csv";
	private String path01 = "aba01.csv";
	private String path02 = "aba02.csv";
	private String path03 = "aba03.csv";
	private List<Aluno> alunos;

	private FileReader reader;

	public AlunoController(AlunoRepository repository) {
		this.repository = repository;
	}

	private String extrairTexto() throws IOException {

		reader = new FileReader(new File(path));
		StringBuilder sb = new StringBuilder();
		int dados = reader.read();

		while (dados != -1) {
			sb.append((char) dados);
			dados = reader.read();
		}

		reader.close();
		texto = sb.toString();
		return texto;
	}

	private List<String> geraLinhas() throws IOException {

		extrairTexto();

		String[] linhas = texto.split("\n");
		lista = new ArrayList<>();

		for (String linha : linhas) {
			lista.add(linha);
		}
		return lista;
	}

	public List<Aluno> geraAlunos() throws IOException {

		geraLinhas();

		alunos = new ArrayList<>();

		for (String l : lista) {

			try {
				String[] colunas = geraColunas(l);

				Aluno aluno = new Aluno();
				Long id = Long.valueOf(colunas[0].trim());
				aluno.setIdentificacao(id);
				aluno.setNome(colunas[1]);
				char c = colunas[2].charAt(0);
				aluno.setSexo(c);
				aluno.setDataNascimento(converteData(colunas[3]));
				aluno.setNotaPrimeiroTrimestre(Double.valueOf(colunas[4]));
				aluno.setNotaSegundoTrimestre(Double.valueOf(colunas[5]));
				aluno.setNotaTerceiroTrimestre(Double.valueOf(colunas[6]));

				alunos.add(aluno);
			} catch (Exception e) {
				continue;
			}

		}

		return alunos;
	}

	public void geraAba01() throws IOException {

		// recuoera a lista de alunos da banco de dados
		List<Aluno> alunos = repository.findAll();

		// cria uma nova lista ordenada por nome de aluno
		List<Aluno> lista = new ArrayList<Aluno>(alunos);
		lista.sort(Comparator.comparing(Aluno::getNome));

		StringBuilder sb = new StringBuilder();

		sb.append("Identificação;");
		sb.append("Nome;");
		sb.append("Sexo;");
		sb.append("Data Nascimento;");
		sb.append("Nota 1º Trim;");
		sb.append("Nota 2º Trim;");
		sb.append("Nota 3º Trim\n");

		for (Aluno aluno : lista) {
			sb.append(aluno + "\n");
		}

		String texto = sb.toString();

		FileWriter writer = new FileWriter(new File(path01));
		PrintWriter printer = new PrintWriter(writer);
		printer.write(texto);
		printer.close();
	}

	public void geraAba02() throws IOException {

		// recuoera a lista de alunos da banco de dados
		List<Aluno> alunos = repository.findAll();

		// cria uma nova lista ordenada por nome de aluno
		List<Aluno> lista = new ArrayList<Aluno>(alunos);
		lista.sort(Comparator.comparing(Aluno::calculaIdade));

		StringBuilder sb = new StringBuilder();

		sb.append("Identificação;");
		sb.append("Nome;");
		sb.append("Idade;");
		sb.append("Média das notas\n");

		for (Aluno aluno : lista) {
			sb.append(aluno.toStringAba02() + "\n");
		}

		String texto = sb.toString();

		FileWriter writer = new FileWriter(new File(path02));
		PrintWriter printer = new PrintWriter(writer);
		printer.write(texto);
		printer.close();
	}

	public void geraAba03() throws IOException {

		StringBuilder sb = new StringBuilder();

		// recuoera a lista de alunos da banco de dados
		List<Aluno> alunos = repository.findAll();

//		System.out.println("quantidade alunos: " + alunos.size());

		// Percentual alunos sexo masculino

		int quantidadeM = 0;
		for (Aluno aluno : alunos) {
//			System.out.println("quantidadeM: " + quantidadeM);
			if (aluno.getSexo() == 'M') {
//				System.out.println(aluno);
				quantidadeM++;
			}
		}

//		System.out.println("quantidadeM: " + quantidadeM);

		double percentualM = ((double) quantidadeM / (double) alunos.size()) * 100;

		double percentualF = 100 - percentualM;

//		System.out.println(String.format("%.2f", percentualM));
//		System.out.println(String.format("%.2f", percentualF));

		sb.append("Percentual de alunos do sexo maasculino;" + String.format("%.2f", percentualM) + "%\n");

		sb.append("Percentual de alunos do sexo feminino;" + String.format("%.2f", percentualF) + "%\n");

		int quantidadeMenos30 = 0;
		for (Aluno aluno : alunos) {
			if (aluno.calculaIdade() < 30) {
				quantidadeMenos30++;
			}
		}

		double percentualAbaixo30 = ((double) quantidadeMenos30 / (double) alunos.size()) * 100;

		sb.append("Percentual de alunos com menos de 30 anos;" + String.format("%.2f", percentualAbaixo30) + "%\n");

		int quantidadeAprovados = 0;
		for (Aluno aluno : alunos) {
			double notaTotal = aluno.getNotaPrimeiroTrimestre() + aluno.getNotaSegundoTrimestre()
					+ aluno.getNotaTerceiroTrimestre();
			if (notaTotal > 70) {
				quantidadeAprovados++;
			}
		}

		double percentualAprovados = ((double) quantidadeAprovados / (double) alunos.size()) * 100;

		sb.append("Percentual de alunos aprovados;" + String.format("%.2f", percentualAprovados) + "%\n");
		
		int quantidadeAlunosMaisDe30 = 0;
		double somaNotasAlunosMaisDe30 = 0;
		for (Aluno aluno : alunos) {
			if (aluno.calculaIdade() > 30) {
				quantidadeAlunosMaisDe30++;
				somaNotasAlunosMaisDe30 += aluno.calculaSomaNotas();
			}
		}
		
		double mediaNotaAlunosMais30Anos = 
				somaNotasAlunosMaisDe30 / (double) quantidadeAlunosMaisDe30;
		
		sb.append("Média de nota dos alunos com mais de 30 anos;" + String.format("%.2f", mediaNotaAlunosMais30Anos) + "%\n");
		
		String texto = sb.toString();

		FileWriter writer = new FileWriter(new File(path03));
		PrintWriter printer = new PrintWriter(writer);
		printer.write(texto);
		printer.close();

//		System.out.println(sb.toString());
	}

	private String[] geraColunas(String l) {
		String[] colunas = l.split(";");
		return colunas;
	}

	private LocalDate converteData(String data) {
		String[] tokens = data.split("/");
		int[] nums = new int[3];
		nums[0] = Integer.valueOf(tokens[0]);
		nums[1] = Integer.valueOf(tokens[1]);
		nums[2] = Integer.valueOf(tokens[2]);

		return LocalDate.of(nums[2], nums[1], nums[0]);
	}

}
