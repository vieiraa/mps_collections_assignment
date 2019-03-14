package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

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
	    if (times.containsKey(id))
	        throw new IdentificadorUtilizadoException();
	    
		Time t = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		times.put(id, t);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
	    if (!times.containsKey(idTime))
	        throw new TimeNaoEncontradoException();
	    
	    if (times.values().stream().anyMatch(t -> t.getJogadores().containsKey(idTime)))
	        throw new IdentificadorUtilizadoException();
	    
		Jogador j = new Jogador(id, nome, dataNascimento, nivelHabilidade, salario);
		times.get(idTime).getJogadores().put(id, j);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
	    if (times.values().stream().anyMatch(t -> !t.getJogadores().containsKey(idJogador)))
	        throw new JogadorNaoEncontradoException();
	    
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
	    if (!times.containsKey(idTime))
	        throw new TimeNaoEncontradoException();
	    
	    Stream<Time> time = times.values().stream();
	    if (time.anyMatch(t -> t.getJogadores().values().stream().allMatch(j -> !j.getCapitao())))
	        throw new CapitaoNaoInformadoException();
	    
	    return times.get(idTime).getJogadores().values().stream()
	    .filter(cap -> cap.getCapitao() == true)
	    .collect(Collectors.toList())
	    .get(0).getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
	    if (times.values().stream().anyMatch(t -> !t.getJogadores().containsKey(idJogador)))
	        throw new JogadorNaoEncontradoException();
	    
	    Jogador jogador = null;
	    for (Time t : times.values()) {
	        if (t.getJogadores().values().stream().anyMatch(j -> j.getId() == idJogador)) {
    	        jogador = t.getJogadores().values().stream()
    	                .filter(j -> j.getId() == idJogador)
    	                .collect(Collectors.toList())
    	                .get(0);
	        }
	    }
	    
	    return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
	    if (!times.containsKey(idTime))
	        throw new TimeNaoEncontradoException();
	    
		return times.get(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
	    if (!times.containsKey(idTime))
            throw new TimeNaoEncontradoException();
	    
	    return times.get(idTime).getJogadores().keySet()
	            .stream()
                .sorted()
                .collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
	    if (!times.containsKey(idTime))
            throw new TimeNaoEncontradoException();
	    
		return times.get(idTime).getJogadores().values().stream()
		        .max(Comparator.comparing(Jogador::getNivelHabilidade))
		        .get().getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
	    if (!times.containsKey(idTime))
            throw new TimeNaoEncontradoException();
	    
		return times.get(idTime).getJogadores().values().stream()
		        .sorted(Comparator.comparing(Jogador::getId))
		        .max(Comparator.comparing(Jogador::getDataNascimento))
		        .get().getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.keySet().stream()
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
	    if (!times.containsKey(idTime))
            throw new TimeNaoEncontradoException();
	    
	    return times.get(idTime).getJogadores().values().stream()
                .sorted(Comparator.comparing(Jogador::getId))
                .max(Comparator.comparing(Jogador::getSalario))
                .get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
	    if (times.values().stream().anyMatch(t -> !t.getJogadores().containsKey(idJogador)))
            throw new JogadorNaoEncontradoException();
	    
	    Jogador jogador = null;
        for (Time t : times.values()) {
            if (t.getJogadores().values().stream().anyMatch(j -> j.getId() == idJogador)) {
                jogador = t.getJogadores().values().stream()
                        .filter(j -> j.getId() == idJogador)
                        .collect(Collectors.toList())
                        .get(0);
            }
        }
        
        return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
	    List<Jogador> jogadores = new ArrayList<>();
	    List<Jogador> melhores = new ArrayList<>();
	    List<Long> ids = new ArrayList<>();
		for (Time t : times.values()) {
		    List<Jogador> aux = t.getJogadores().values().stream()
		            .collect(Collectors.toList());
		    jogadores.addAll(aux);
		}
		
		jogadores = jogadores.stream()
		        .sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
		        .collect(Collectors.toList());
		
		melhores.addAll(jogadores.subList(0, top));
		melhores.stream().forEach(j -> ids.add(j.getId()));
		
		return ids;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
	    Time casa = times.get(timeDaCasa);
	    Time fora = times.get(timeDeFora);
		if (casa.getCorUniformePrincipal() != fora.getCorUniformePrincipal()) {
		    return fora.getCorUniformePrincipal();
		}
		
		else {
		    return fora.getCorUniformeSecundario();
		}
	}
}
