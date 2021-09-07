package com.conversorexcel.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Aluno {

	@Id 
	private Long identificacao;
	private String nome;
	private Character sexo;
	private LocalDate dataNascimento;
	private Double notaPrimeiroTrimestre;
	private Double notaSegundoTrimestre;
	private Double notaTerceiroTrimestre;
	
	public Aluno() {}

	public Aluno(Long identificacao, String nome, Character sexo, LocalDate dataNascimento,
			Double notaPrimeiroTrimestre, Double notaSegundoTrimestre, Double notaTerceiroTrimestre) {
		this.identificacao = identificacao;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.notaPrimeiroTrimestre = notaPrimeiroTrimestre;
		this.notaSegundoTrimestre = notaSegundoTrimestre;
		this.notaTerceiroTrimestre = notaTerceiroTrimestre;
	}
	
	public int calculaIdade() {
		LocalDate dataAtual = LocalDate.now();
		int idade = dataAtual.compareTo(dataNascimento);
		return idade;
	}
	
	public double calculaSomaNotas() {
		return notaPrimeiroTrimestre + notaSegundoTrimestre + notaTerceiroTrimestre;
	}
	
	public double calculaMedia() {
		return 
			(notaPrimeiroTrimestre + notaSegundoTrimestre + notaTerceiroTrimestre) / 3;
	}

	public Long getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(Long identificacao) {
		this.identificacao = identificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Double getNotaPrimeiroTrimestre() {
		return notaPrimeiroTrimestre;
	}

	public void setNotaPrimeiroTrimestre(Double notaPrimeiroTrimestre) {
		this.notaPrimeiroTrimestre = notaPrimeiroTrimestre;
	}

	public Double getNotaSegundoTrimestre() {
		return notaSegundoTrimestre;
	}

	public void setNotaSegundoTrimestre(Double notaSegundoTrimestre) {
		this.notaSegundoTrimestre = notaSegundoTrimestre;
	}

	public Double getNotaTerceiroTrimestre() {
		return notaTerceiroTrimestre;
	}

	public void setNotaTerceiroTrimestre(Double notaTerceiroTrimestre) {
		this.notaTerceiroTrimestre = notaTerceiroTrimestre;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = dtf.format(dataNascimento);
		
		return identificacao + ";" + nome + ";" + sexo + ";"
				+ data + ";" + notaPrimeiroTrimestre + ";"
				+ notaSegundoTrimestre + ";" + notaTerceiroTrimestre;
	}
	
	public String toStringAba02() {
		
		return identificacao + ";" + nome + ";" + calculaIdade() 
		+ ";" + String.format("%.2f", calculaMedia());
	}
	
	
}
