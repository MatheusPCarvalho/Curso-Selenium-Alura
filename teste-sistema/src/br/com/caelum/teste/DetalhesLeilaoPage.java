package br.com.caelum.teste;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetalhesLeilaoPage {

	private WebDriver driver;

	public DetalhesLeilaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void lance(String usuario, double valor) {
		WebElement txtValor = driver.findElement(By.name("lance.valor"));
		Select cbUsuario = new Select(driver.findElement(By.name("lance.usuario.id")));
		cbUsuario.selectByVisibleText(usuario);
		txtValor.sendKeys(String.valueOf(valor));

		driver.findElement(By.id("btnDarLance")).click();
	}

	public boolean existeLance(String usuario, double valor) {
		@SuppressWarnings("deprecation")
		Boolean temUsuario = new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(By.id("lancesDados"), usuario));
		if (temUsuario)
			return driver.getPageSource().contains(usuario) && driver.getPageSource().contains(String.valueOf(valor));
		return false;
	}
}
