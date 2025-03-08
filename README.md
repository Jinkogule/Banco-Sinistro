# Trabalho de Desenvolvimento Web - BancoSinistro
Aplicação Web desenvolvida para disciplina TCC00226 - Desenvolvimento Web, ministrada pelo professor Leonardo Cruz no Instituto de Computação da Universidade Federal Fluminense, durante o período 1º/2021.

Trabalho feito por Lucas Pimenta e Lucas Couto.

## Requisitos do trabalho:

### 1 Introdução

Esse documento tem por objetivo especificar os requisitos da aplicação que apoia o
desenvolvimento de uma aplicação de controle de lançamentos de créditos e débito de
um usuário.

### 2 Visão da Solução

Uma aplicação em Java (servlet+jsp) que apoia um usuário no controle de lançamentos
de créditos e débitos como uma conta corrente de um banco. Ao acessar a aplicação
essa contará com uma área pública (onde informações sobre a aplicação estarão
disponíveis) e uma área privada (onde as funções do sistema estão disponíveis).

### 3 Papeis

Usuário: acessa a aplicação na sua área privada e interage com a sua conta corrente;
Público: acessa a aplicação na sua área pública e visualiza informações gerais sobre a
aplicação;
Administrador: acessa a aplicação na sua área privada e administra os usuários.

### 4 Escopo da Solução

4.1 Requisitos Não Funcionais Gerais
RNF1: Sistema deverá ser responsivo sendo obrigatório o uso do Bootstrap
RNF2: Todas as bibliotecas, scripts, imagens, etc., necessários para o
funcionamento devem estar disponíveis localmente;

4.2 Requisitos Funcionais Gerais
Obs. Sempre que o termo cadastrar (e seus sinônimos) for empregado fica
compreendido um conjunto de tarefas a serem implementas na aplicação são:
Listar/Consultar, incluir, alterar e excluir - CRUD.

4.2.1 Casos de Usos - Administrador
RF1: O administrador acessa a área privada da aplicação por meio de login.
RF2: O administrador cadastra os usuários no sistema.
RF3: O administrador cadastra os administradores no sistema.
RF4: O administrador pode suspender o acesso do usuário ao sistema.
RF5: O administrador cadastra a categoria no sistema.

4.2.2 Casos de Usos - Usuário
RF6: O usuário acessa a área privada da aplicação por meio de login.
RF7: O usuário cadastra sua conta corrente no sistema.
RF8: O usuário cadastra seus lançamentos (crédito e débito) na conta corrente.
2 de 4
F9: Listagem na tela dos lançamentos com os somatórios dos débitos e créditos e
saldo atual;

### 5 Limites e Restrições da Solução

5.1 O nome do banco de dados é: financeiro. As tabelas apresentadas abaixo não
podem ser alteradas; será fornecido o script para o banco de dados.

5.2 Para acessar o banco de dados deverá ser usado o usuário = root e sem senha.

5.3 Deve existir um administrador previamente cadastrado no sistema (Banco de Dados)
com o cpf= 249.252.810-38 e senha= 111

5.4 Todos os logins são realizados usando cpf e senha.

5.5 Deverá possuir validações de dados no cliente e no servidor.

5.6 O sistema deverá ser construído usando a IDE Netbeans 8.2 e Mysql 5.7

5.7 Todas as entregas (etapas) devem ser realizadas como uma aplicação Java. Não
serão aceitos arquivos ou fontes “soltos”.

5.8 A aplicação deve ser desenvolvida no estilo MVC.

5.9 É obrigatório o uso de DAO.


<div align="center">
<img style="" src="https://github.com/Jinkogule/BancoSinistro/blob/main/web/images/logo.png" width="250px;" alt=""/>
<br>

[![Release](https://img.shields.io/github/v/release/Jinkogule/BancoSinistro?style=for-the-badge)](https://github.com/Jinkogule/BandejApp/releases)
[![License](https://img.shields.io/github/license/Jinkogule/BancoSinistro?style=for-the-badge)](LICENSE)<br>
![Status](https://img.shields.io/badge/STATUS-CONCLU%C3%8DDO%20-brightgreen?style=for-the-badge)
</div>

<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-documentação">Documentação</a> • 
 <a href="#-desenvolvimento">Desenvolvimento</a> • 
 <a href="#-tecnologias">Tecnologias</a> • 
 <a href="#-executar-o-projeto-localmente">Executar o projeto localmente</a> • 
 <a href="#-autor">Autor</a> •
 <a href="#-licença">Licença</a>
</p>

---

## 💻 Sobre o projeto

O **Banco Sinistro** é um software fictício desenvolvido para auxiliar no gerenciamento de lançamentos de créditos e débitos de usuários. O sistema funciona como uma conta corrente digital e tem como escopo a gestão financeira individual dos usuários. Através da aplicação, é possível cadastrar usuários e administradores, registrar contas correntes, realizar lançamentos de crédito e débito, além de listar os lançamentos com seus respectivos somatórios e saldo atual.

O projeto foi criado como parte da disciplina Desenvolvimento Web, com o objetivo de aplicar conceitos como desenvolvimento de aplicações web utilizando Java (Servlet + JSP), arquitetura MVC, manipulação de banco de dados MySQL, uso de DAO (Data Access Object), validação de dados no cliente e no servidor, além da implementação de autenticação e controle de acesso para diferentes tipos de usuários.

**Observação:** Este projeto foi desenvolvido apenas como um exercício acadêmico para fins de aprendizagem. Portanto, não se trata de um sistema bancário real.

## 📋 Documentação

-   **[Wiki](https://github.com/Jinkogule/BancoSinistro/wiki)**

## 🧑🏻‍💻 Desenvolvimento

-   **[Código fonte](https://github.com/Jinkogule/BancoSinistro)**
-   **[Issue Tracking](https://github.com/Jinkogule/BancoSinistro/issues)**

## 🛠 Tecnologias

### **Back-End (Aplicação Web)**  

- [Java (Servlet + JSP)](https://www.oracle.com/br/java/technologies/downloads/)  
- [GlassFish](https://javaee.github.io/glassfish/)   

### **Banco de Dados**

-   **[MySQL 8.0.3](https://www.mysql.com/)**

## ⚙ Executar o projeto localmente  

### **Pré-Requisitos**  

Antes de começar, certifique-se de:  

- Instalar o **[Git](https://git-scm.com/)**.  
- Instalar o **[MySQL Server 8.0.3](https://dev.mysql.com/downloads/mysql/)** (ou versão compatível).  
- Executar o **[script de banco de dados](https://github.com/Jinkogule/BancoSinistro/blob/main/financeiro.sql)** para criar o schema e as tabelas necessárias.    
- Instalar o **[JDK 15](https://www.oracle.com/br/java/technologies/downloads/#java15)** (ou versão compatível) e configurar a variável `JAVA_HOME` com o caminho correto do JDK.
- Baixar o **[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)** para conectar o projeto ao MySQL.  
- Instalar o **[Apache NetBeans IDE](https://netbeans.apache.org/front/main/index.html)** para rodar o projeto.

### **Rodando a Aplicação**  

1. **Clone este repositório**
```bash
git clone https://github.com/Jinkogule/BancoSinistro    
```

2. **Abra o projeto no Apache NetBeans IDE**  
   - No menu, vá em *File* > *Open Project* e selecione a pasta do projeto.  

3. **Configure a conexão com o banco de dados**  
   - Se necessário, edite a classe `ConnectDB.java` (localizada em `src/java/model/conexaoBD.java`).  
   - Ajuste os dados de conexão com o MySQL.  

4. **Adicione o MySQL Connector/J às Libraries do projeto**  
   - No NetBeans, clique com o botão direito no projeto → *Properties* → *Libraries*.  
   - Na aba *Compile* (*Compile-time Libraries*), clique no **"+"** ao lado da opção *Classpath*.  
   - Selecione *Add JAR/Folder* e escolha o arquivo `.jar` do **MySQL Connector/J** baixado.  
   - Confirme e aplique as alterações.  

5. **Execute a aplicação**  
   - No Apache NetBeans, clique com o botão direito no projeto e selecione *Run*.  

## ✒ Autor

<table>
  <tr>
    <td align="center">
      Lucas Pimenta
      <br>
      <a href="https://github.com/Jinkogule">
        <img src="https://avatars.githubusercontent.com/u/52849575?v=4" width="100px;" alt="Lucas Pimenta"/>
      </a>
      <br>
      <a href="https://github.com/Jinkogule">
        <img src="https://img.shields.io/badge/-Github-black?style=flat-square&logo=Github&logoColor=white">
      </a>
    </td>
  </tr>
</table>

## 📝 Licença

Este projeto está sob a licença **[MIT](./LICENSE)**.

