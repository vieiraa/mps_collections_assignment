package br.com.codenation;

import java.time.LocalDate;
import java.util.HashMap;

public class Time {
	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private HashMap<Long, Jogador> jogadores;
	
	public Time(Long i, String n, LocalDate d, String c1, String c2) {
		id = i;
		nome = n;
		dataCriacao = d;
		corUniformePrincipal = c1;
		corUniformeSecundario = c2;
		jogadores = new HashMap<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}

	public void setCorUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
	}

	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}

	public void setCorUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public HashMap<Long, Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(HashMap<Long, Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
	@Override
	public String toString() {
	    String s = "";
	    for (Jogador j : jogadores.values()) {
	        s += j.getId() + " " + j.getNome() + " " + (j.getCapitao() ? "Capitao" : "") + "\n";
	    }
	    
	    return s;
	}
}
