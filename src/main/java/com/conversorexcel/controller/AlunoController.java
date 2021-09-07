package com.conversorexcel.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlunoController {

	private String texto;
	private List<String> lista;
	private String path = "baseImportacao.csv";
	
	private FileReader reader;
	
	private String extrairTexto() throws IOException {
		
		reader = new FileReader(new File(path));
		StringBuilder sb = new StringBuilder();
		int dados = reader.read();
		
		while(dados != -1) {
			
			sb.append((char) dados);
			dados = reader.read();
			
		}
		
		reader.close();
		texto = sb.toString();
		return texto;
	}
	
	public List<String> geraLinhas() throws IOException{
		
		extrairTexto();
		String[] linhas = texto.split("\n");
		lista = new ArrayList<>();
		
		for(String linha : linhas) {
			
			lista.add(linha);
			
		}
		return lista;
		
	}
	

}
