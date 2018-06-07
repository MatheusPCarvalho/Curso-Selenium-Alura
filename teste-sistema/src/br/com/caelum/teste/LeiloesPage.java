package br.com.caelum.teste;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeiloesPage {
	private final WebDriver driver;

	public LeiloesPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost:8080/leiloes");
	}

	public NovoLeilaoPage novo() {
		driver.findElement(By.linkText("Novo Leilão")).click();
		return new NovoLeilaoPage(driver);
	}

	public boolean existe(String produto, double valor, String usuario, boolean usado) throws InterruptedException {
		return  driver.getPageSource().contains(produto) && 
				driver.getPageSource().contains(String.valueOf(valor)) &&
				driver.getPageSource().contains(usuario) && 
				driver.getPageSource().contains(usado ? "Sim" : "Não");
	}

	@SuppressWarnings("deprecation")
	public DetalhesLeilaoPage detalhes(int posicao) throws Exception {
		Boolean podeVerificar = new WebDriverWait(driver, 5).until(ExpectedConditions.textToBePresentInElement(By.linkText("exibir"), "exibir"));
		if(podeVerificar) {			
			List<WebElement>elementos = driver.findElements(By.linkText("exibir"));
			elementos.get(posicao - 1).click();
			return new DetalhesLeilaoPage(driver);
		}
		throw new Exception("Não existem itens para serem detalhados!");
	}
}
