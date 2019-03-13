package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private HashMap<Long, Time> times;
	
	public DesafioMeuTimeApplication() {
		times = new HashMap<>();
	}
	
	public HashMap<Long, Time> getTimes() {
	    return times;
	}
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Time t = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		times.put(id, t);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Jogador j = new Jogador(id, nome, dataNascimento, nivelHabilidade, salario);
		times.get(idTime).getJogadores().put(id, j);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
	    
	    Time time = times.values().stream()
	    .filter(t -> t.getJogadores().keySet().contains(idJogador) == true)
	    .collect(Collectors.toList()).get(0);
	    
	    Stream<Jogador> jogadores = time.getJogadores().values().stream();
	    
	    if (jogadores.anyMatch(j -> j.getCapitao() == true)) {
    	    Jogador exCapitao = time.getJogadores().values().stream()
    	            .filter(j -> j.getCapitao() == true)
    	            .collect(Collectors.toList())
    	            .get(0);
    	    exCapitao.setCapitao(false);
	    }
	    
	    time.getJogadores().get(idJogador).setCapitao(true);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
	    return times.get(idTime).getJogadores().values().stream()
	    .filter(cap -> cap.getCapitao() == true)
	    .collect(Collectors.toList())
	    .get(0).getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return times.get(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.keySet().stream()
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		throw new UnsupportedOperationException();
	}
}
