package br.com.caelum.teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LeiloesSystemTest {

	private WebDriver driver;
	private LeiloesPage leiloes;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "D:\\Programas\\geckodriver.exe");
		this.driver = new FirefoxDriver();
		this.leiloes = new LeiloesPage(driver);
		
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("João do leilão", "joao@leilao@gmail.com");
	}

	@Test
	public void deveCadastrarUmLeilao() throws InterruptedException {
		leiloes.visita();
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.preenche("Geladeira", 123, "João do leilão", true);
		assertTrue(leiloes.existe("Geladeira", 123, "João do leilão", true));
	}
}