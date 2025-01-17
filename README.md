# AutomationTests

Este projeto é uma suíte de testes de automação para validar cenários de login no site [Automation Practice](http://automationpractice.pl). Ele utiliza **Selenium WebDriver**, **JUnit**, e **WebDriverManager**, com configuração gerenciada pelo **Maven**.

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

- **Java 17** ou superior
- **Maven 3.6** ou superior
- Navegador **Google Chrome**
- **ChromeDriver** compatível com a versão do navegador (gerenciado automaticamente pelo WebDriverManager)

## 🛠️ Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/paolachu/ScenarioAutomation.git
   
2. Instale as dependências do Maven:
   ```bash
   mvn clean install

## 📂 Estrutura do Projeto

- **`src/main/java`**: Código principal.
- **`src/test/java`**: Código dos testes automatizados.
- **`pom.xml`**: Configuração do Maven, incluindo dependências e plugins.

### 📦 Dependências principais

- [Selenium](https://www.selenium.dev): Para interação com navegadores.
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager): Para gerenciar drivers de navegador.
- [JUnit 4](https://junit.org/junit4/): Para estruturação e execução dos testes.

## 🧪 Cenários de Teste Implementados

1. **Verificar mensagem de erro ao tentar login sem senha**  
   Certifica que a mensagem **"Password is required."** é exibida.

2. **Verificar mensagem de erro ao usar email inválido**  
   Certifica que a mensagem **"Invalid email address."** é exibida.

3. **Verificar redirecionamento para recuperação de senha**  
   Valida que o link **"Forgot your password?"** redireciona corretamente.

4. **Verificar login com credenciais válidas**  
   Garante que o usuário seja redirecionado para a página da conta ao usar credenciais válidas.


## ▶️ Executando os Testes

1. Certifique-se de que o Maven está configurado corretamente.
2. Execute os testes com o comando:
   ```bash
   mvn test

## 📄 Logs e Relatórios

Os logs dos testes são gerados usando SLF4J. Certifique-se de revisar o console para detalhes sobre falhas ou resultados dos testes.

## Autor
Criado por Paola Chu como parte de um teste técnico para validação de conhecimentos em testes automatizados.
