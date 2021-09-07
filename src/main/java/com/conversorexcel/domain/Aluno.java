package com.conversorexcel.domain;

import java.time.LocalDate;
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
		return Objects.hash(identificacao);
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
		return Objects.equals(identificacao, other.identificacao);
	}

	@Override
	public String toString() {
		return "Boletim [identificacao=" + identificacao + ", nome=" + nome + ", sexo=" + sexo + ", dataNascimento="
				+ dataNascimento + ", notaPrimeiroTrimestre=" + notaPrimeiroTrimestre + ", notaSegundoTrimestre="
				+ notaSegundoTrimestre + ", notaTerceiroTrimestre=" + notaTerceiroTrimestre + "]";
	}
	
	
	
}
