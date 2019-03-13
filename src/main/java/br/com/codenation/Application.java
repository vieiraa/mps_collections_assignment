package br.com.codenation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		DesafioMeuTimeApplication app = new DesafioMeuTimeApplication();
		app.incluirTime(0L, "SPFC", LocalDate.now(), "Branco", "Preto");
		app.incluirJogador(0L, 0L, "Ceni", LocalDate.now(), 99, BigDecimal.valueOf(180.000));
		app.definirCapitao(0L);
		app.incluirJogador(1L, 0L, "Kaka", LocalDate.now(), 99, BigDecimal.valueOf(200.000));
		app.definirCapitao(1L);
		
		app.getTimes().values().stream().forEach(System.out::println);;
	}

}
