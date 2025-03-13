<div align="center">
<img style="" src="https://github.com/Jinkogule/Banco-Sinistro/blob/main/web/images/logo.png" width="250px;" alt=""/>
<br>

[![Release](https://img.shields.io/github/v/release/Jinkogule/Banco-Sinistro?style=for-the-badge)](https://github.com/Jinkogule/BandejApp/releases)
[![License](https://img.shields.io/github/license/Jinkogule/Banco-Sinistro?style=for-the-badge)](LICENSE)<br>
![Status](https://img.shields.io/badge/STATUS-CONCLU%C3%8DDO%20-brightgreen?style=for-the-badge)
</div>

<p align="center">
  <a href="#-sobre-o-projeto">Sobre</a> •
  <a href="#-documentação">Documentação</a> •
  <a href="#-desenvolvimento">Desenvolvimento</a> •
  <a href="#-tecnologias">Tecnologias</a> •
  <a href="#-executar-o-projeto-localmente">Executar o projeto localmente</a> •
  <a href="#-autores">Autores</a> •
  <a href="#-licença">Licença</a>
  <br>
  <a href="./README.pt-BR.md">Português (BR)</a> •
  <a href="./README.md">English</a>
</p>

---

## 💻 Sobre o projeto

O **Banco Sinistro** é um software fictício desenvolvido para auxiliar no gerenciamento de lançamentos de créditos e débitos de usuários. O sistema funciona como uma conta corrente digital e tem como escopo a gestão financeira individual dos usuários. Através da aplicação, é possível cadastrar usuários e administradores, registrar contas correntes, realizar lançamentos de crédito e débito, além de listar os lançamentos com seus respectivos somatórios e saldo atual.

O projeto foi criado como parte da disciplina Desenvolvimento Web, com o objetivo de aplicar conceitos como desenvolvimento de aplicações web utilizando Java (Servlet + JSP), arquitetura MVC, manipulação de banco de dados MySQL, uso de DAO (Data Access Object), validação de dados no cliente e no servidor, além da implementação de autenticação e controle de acesso para diferentes tipos de usuários.

**Observação:** Este projeto foi desenvolvido apenas como um exercício acadêmico para fins de aprendizagem. Portanto, não se trata de um sistema bancário real.

## 📋 Documentação

-   **[Wiki](https://github.com/Jinkogule/Banco-Sinistro/wiki)**

## 🧑🏻‍💻 Desenvolvimento

-   **[Código fonte](https://github.com/Jinkogule/Banco-Sinistro)**
-   **[Issue Tracking](https://github.com/Jinkogule/Banco-Sinistro/issues)**

## 🛠 Tecnologias

### **Back-End (Aplicação Web)**

-   **[Java (Servlet + JSP)](https://www.oracle.com/br/java/technologies/downloads/)**

### **Banco de Dados**

-   **[MySQL 8.0.3](https://www.mysql.com/)**

## ⚙ Executar o projeto localmente

### **Pré-Requisitos**

Antes de começar, certifique-se de:

- Instalar o **[Git](https://git-scm.com/)**.
- Instalar o **[MySQL Server 8.0.3](https://dev.mysql.com/downloads/mysql/)** (ou versão compatível).
- Executar o **[script de banco de dados](https://github.com/Jinkogule/Banco-Sinistro/blob/main/financeiro.sql)** para criar o schema e as tabelas necessárias.
- Instalar o **[JDK 15](https://www.oracle.com/br/java/technologies/downloads/#java15)** (ou versão compatível) e configurar a variável `JAVA_HOME` com o caminho correto do JDK.
- Baixar o **[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)** para conectar o projeto ao MySQL.
- Instalar o **[Apache NetBeans IDE](https://netbeans.apache.org/front/main/index.html)** para rodar o projeto.
- Configure um servidor web compatível (**[Tomcat](https://tomcat.apache.org/)** or **[GlassFish](https://javaee.github.io/glassfish/)**) no Apache NetBeans.

### **Rodando a Aplicação**

1. **Clone este repositório**
```bash
git clone https://github.com/Jinkogule/Banco-Sinistro
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

## ✒ Autores

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
    <td align="center">
      Lucas Couto
      <br>
      <a href="https://github.com/LucasCouto22">
        <img src="https://avatars.githubusercontent.com/u/62523407?v=4" width="100px;" alt="Lucas Couto"/>
      </a>
      <br>
      <a href="https://github.com/LucasCouto22">
        <img src="https://img.shields.io/badge/-Github-black?style=flat-square&logo=Github&logoColor=white">
      </a>
    </td>
  </tr>
</table>

## 📝 Licença

Este projeto está sob a licença **[MIT](./LICENSE)**.