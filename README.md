Projeto Blog Pessoal - Backend com Spring Boot

source: imgur.com

      Status: Em Construção

1. Descrição

O Blog Pessoal é uma aplicação que permite que usuários publiquem, editem e visualizem postagens relacionadas a temas variados, de forma organizada e segura. Este projeto foi desenvolvido com fins educacionais, simulando uma aplicação real de blog para praticar conceitos de API REST com Java e Spring Boot.

Entre os principais recursos que um blog pessoal oferece, destacam-se:

Criação, edição e exclusão de postagens
Associação de postagens a temas específicos
Cadastro e autenticação de usuários
Visualização de postagens por tema ou usuário
Controle de acesso a operações sensíveis

2. Sobre esta API

A API do Blog Pessoal foi desenvolvida utilizando Java e o framework Spring, seguindo os princípios da Arquitetura MVC e REST. Ela oferece endpoints para o gerenciamento dos recursos Usuário, Postagem e Tema, permitindo a interação entre os usuários e os conteúdos publicados.


2.1. Principais funcionalidades da API:

Consulta, cadastro, login e atualização dos dados de usuários
Consulta, criação e gerenciamento de temas para classificar postagens
Criação, edição, listagem e remoção de postagens
Associação de postagens a temas e autores
Autenticação via token JWT para segurança nas requisições

3. Diagrama de Classes

O Diagrama de Classes é um modelo visual usado na programação orientada a objetos para representar a estrutura de um sistema. Ele exibe classes, atributos, métodos e os relacionamentos entre elas, como associações, heranças e dependências.

Esse diagrama ajuda a planejar e entender a arquitetura do sistema, mostrando como as entidades interagem e se conectam. É amplamente utilizado nas fases de design e documentação de projetos.




4. Diagrama Entidade-Relacionamento (DER)

O DER (Diagrama Entidade-Relacionamento) do projeto Blog Pessoal representa de forma visual como os dados estão organizados no banco de dados relacional e como as entidades se relacionam entre si.




5. Tecnologias utilizadas

Item	Descrição
Servidor	Tomcat
Linguagem de programação	Java
Framework	Spring Boot
ORM	JPA + Hibernate
Banco de dados Relacional	MySQL
Segurança	Spring Security
Autenticação	JWT
Testes automatizados	JUnit
Documentação	SpringDoc

6. Requisitos

Para executar os códigos localmente, você precisará:

Java JDK 17+
Banco de dados MySQL
STS
Insomnia ou Postman

7. Como Executar o projeto no STS

7.1. Importando o Projeto
Clone o repositório do Projeto Blog Pessoal dentro da pasta do Workspace do STS
git clone https://github.com/FernandaMurched/blogpessoal_spring.git
Abra o STS e selecione a pasta do Workspace onde você clonou o repositório do projeto
No menu superior do STS, clique na opção: File 🡲 Import...
Na janela Import, selecione a opção: General 🡲 Existing Projects into Workspace e clique no botão Next
Na janela Import Projects, no item Select root directory, clique no botão Browse... e selecione a pasta do Workspace onde você clonou o repositório do projeto
O STS reconhecerá o projeto automaticamente
Marque o Projeto Blog Pessoal no item Projects e clique no botão Finish para concluir a importação

7.2. Executando o projeto
Na Guia Boot Dashboard, localize o Projeto Blog Pessoal
Selecione o Projeto Blog Pessoal
Clique no botão Start or Restart source: imgur.com para iniciar a aplicação
Caso seja perguntado se você deseja autorizar o acesso ao projeto via rede, clique no botão Permitir Acesso
Acompanhe a inicialização do projeto no console do STS
Verifique se o banco de dados db_blogpessoal foi criado corretamente e se as tabelas foram geradas automaticamente.
Utilize o Insomnia ou o Postman para testar os endpoints.

Tip

Ao acessar a URL http://localhost:8080 em seu navegador, a interface do Swagger será carregada automaticamente, permitindo a visualização e a interação com os endpoints da API, bem como a consulta dos modelos de dados utilizados.


8. Contribuição

Este repositório é parte de um projeto educacional, mas contribuições são sempre bem-vindas! Caso tenha sugestões, correções ou melhorias, fique à vontade para:

Criar uma issue
Enviar um pull request
Compartilhar com colegas que estejam aprendendo Java!

9. Contato

Desenvolvido por: Fernanda Murched
https://github.com/FernandaMurched
