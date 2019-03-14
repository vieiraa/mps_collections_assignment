package br.com.codenation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		DesafioMeuTimeApplication app = new DesafioMeuTimeApplication();
		app.incluirTime(0L, "SPFC", LocalDate.now(), "Branco", "Preto");
		app.incluirTime(1L, "Vasco", LocalDate.now(), "Preto", "Branco");
		app.incluirTime(2L, "Vitoria", LocalDate.now(), "Preto", "Vermelho");
		app.incluirJogador(0L, 0L, "Ceni", LocalDate.now(), 99, BigDecimal.valueOf(180.000));
		app.definirCapitao(0L);
		app.incluirJogador(1L, 0L, "Kaka", LocalDate.now(), 95, BigDecimal.valueOf(200.000));
		app.incluirJogador(2L, 1L, "Pikachu", LocalDate.now(), 91, BigDecimal.valueOf(210.000));
		app.incluirJogador(3L, 1L, "Castan", LocalDate.now(), 70, BigDecimal.valueOf(203.000));
		app.definirCapitao(3L);
		app.incluirJogador(4L, 2L, "Neto Baiano", LocalDate.now(), 96, BigDecimal.valueOf(400.000));
		app.incluirJogador(5L, 2L, "Vitor Ramos", LocalDate.now(), 65, BigDecimal.valueOf(500.000));
        app.definirCapitao(5L);
        
        
		
		System.out.println(app.buscarTopJogadores(5));
	}

}
