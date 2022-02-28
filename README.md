# Trabalho de Desenvolvimento Web - BancoSinistro
Aplicação Web desenvolvida para disciplina TCC00226 - Desenvolvimento Web, ministrada pelo professor Leonardo Cruz no Instituto de Computação da Universidade Federal Fluminense, durante o período 1º/2021.

Trabalho feito por Lucas Pimenta e Lucas Couto.

Especificações do trabalho:

1 Introdução

Esse documento tem por objetivo especificar os requisitos da aplicação que apoia o
desenvolvimento de uma aplicação de controle de lançamentos de créditos e débito de
um usuário.

2 Visão da Solução

Uma aplicação em Java (servlet+jsp) que apoia um usuário no controle de lançamentos
de créditos e débitos como uma conta corrente de um banco. Ao acessar a aplicação
essa contará com uma área pública (onde informações sobre a aplicação estarão
disponíveis) e uma área privada (onde as funções do sistema estão disponíveis).

3 Papeis

Usuário: acessa a aplicação na sua área privada e interage com a sua conta corrente;
Público: acessa a aplicação na sua área pública e visualiza informações gerais sobre a
aplicação;
Administrador: acessa a aplicação na sua área privada e administra os usuários.

4 Escopo da Solução

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

5 Limites e Restrições da Solução

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

