package com.conversorexcel.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AlunoController {

	private String texto;
	private String path = "baseImportacao.csv";
	
	private FileReader reader;
	
	public String extrairTexto() throws IOException {
		
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
	
}
