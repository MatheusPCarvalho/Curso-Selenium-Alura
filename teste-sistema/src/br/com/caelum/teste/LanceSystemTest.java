package br.com.caelum.teste;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LanceSystemTest {

	private WebDriver driver;
	private LeiloesPage leiloes;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "D:\\Programas\\geckodriver.exe");
		driver = new FirefoxDriver();
		leiloes = new LeiloesPage(driver);
		
		driver.get("http://localhost:8080/apenas-teste/limpa");
		
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Jos� Eduardo","jose@eduardo.com");
		usuarios.novo().cadastra("Jos� Eduardo 2","jose@eduardo2.com");
		LeiloesPage leiloesPage = new LeiloesPage(driver);
		leiloesPage.visita();
		leiloesPage.novo().preenche("Geladeira",100, "Jos� Eduardo", false);
	}

	@Test
	public void deveFazerUmLance() throws Exception {
		DetalhesLeilaoPage lances = leiloes.detalhes(1);
		lances.lance("Jos� Eduardo", 150);
		assertTrue(lances.existeLance("Jos� Eduardo", 150));
	}
}
