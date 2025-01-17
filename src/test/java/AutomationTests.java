import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AutomationTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(AutomationTests.class);
    private static final String BASE_URL = "http://automationpractice.pl/index.php?controller=authentication&back=my-account";

    @Before
    public void setUp() {
        // Dado que a configuração do teste será iniciada
        logger.info("Iniciando configuração do teste");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verificarMensagemDeErroSemSenha() {
        // Cenário: Usuário tenta fazer login sem informar senha
        // Dado que o usuário está na página de login
        driver.get(BASE_URL);

        // Quando o usuário informa o email e clica em "Sign in" sem preencher a senha
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.clear();
        emailField.sendKeys("paolawaifong@gmail.com");

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));
        signInButton.click();

        // Então o sistema deve exibir a mensagem de erro indicando que a senha é obrigatória
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger")));
        String expectedMessage = "Password is required.";
        assertTrue("A mensagem de erro não foi exibida corretamente!", errorMessage.getText().trim().contains(expectedMessage));

        logger.info("Teste de login sem senha concluído com sucesso");
    }

    @Test
    public void verificarMensagemDeErroEmailInvalido() {
        // Cenário: Usuário tenta fazer login com email inválido
        // Dado que o usuário está na página de login
        driver.get(BASE_URL);

        // Quando o usuário insere um email inválido
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.clear();
        emailField.sendKeys("paola123@.com");

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));
        signInButton.click();

        // Então o sistema deve exibir a mensagem de erro indicando que o email é inválido
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger")));
        String expectedMessage = "Invalid email address.";
        assertTrue("A mensagem de erro para e-mail inválido não foi exibida corretamente!", errorMessage.getText().trim().contains(expectedMessage));

        logger.info("Teste de email inválido concluído com sucesso");
    }

    @Test
    public void verificarRedirecionamentoEsqueciMinhaSenha() {
        // Cenário: Usuário clica no link 'Esqueci minha senha'
        // Dado que o usuário está na página de login
        driver.get(BASE_URL);

        // Quando o usuário clica no link 'Forgot your password?'
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgot your password?")));
        forgotPasswordLink.click();

        // Então o sistema deve redirecionar o usuário para a página de recuperação de senha
        wait.until(ExpectedConditions.urlContains("controller=password"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue("O redirecionamento para a página de recuperação de senha falhou!", currentUrl.contains("controller=password"));

        logger.info("Teste de redirecionamento 'Esqueci minha senha' concluído com sucesso");
    }

    @Test
    public void verificarLoginComCredenciaisValidas() {
        // Cenário: Usuário faz login com credenciais válidas
        // Dado que o usuário está na página de login
        driver.get(BASE_URL);

        // Quando o usuário insere um email e senha válidos
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.clear();
        emailField.sendKeys("paolawaifong@gmail.com"); // Email válido

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwd")));
        passwordField.clear();
        passwordField.sendKeys("jojo9898"); // Senha válida

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));
        signInButton.click();

        // Então o usuário deve ser redirecionado para a página principal de sua conta
        wait.until(ExpectedConditions.urlContains("controller=my-account"));
        assertTrue("Login não foi realizado com sucesso!", driver.getCurrentUrl().contains("controller=my-account"));

        logger.info("Teste de login com credenciais válidas concluído com sucesso");
    }

    @After
    public void tearDown() {
        // Dado que o teste foi finalizado, vamos fechar o navegador
        logger.info("Finalizando teste e fechando navegador");
        if (driver != null) {
            driver.quit();
        }
    }
}