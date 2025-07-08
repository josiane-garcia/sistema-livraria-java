📚 LIVRARIA ONLINE

Este projeto foi desenvolvido com fins acadêmicos, e consiste na criação de uma aplicação web para o gerenciamento de livros, categorias, clientes e pedidos.

✅ Funcionalidades

- CRUD de Livros
- CRUD de Categorias
- Realização de pedidos/vendas
- Filtro por categoria ou termo de busca
- Interface responsiva


💡Tecnologias Utilizadas

🎨 Frontend
- HTML
- CSS
- Javascript
- JQuery

⚙️ Backend
- Java
- Spring Boot

O Spring Boot é utilizado como framework principal, permitindo a criação de uma API RESTful, feita de forma rápida e com menos configuração manual. A aplicação segue uma estrutura em camadas:
- Controller: Recebe as requisições do cliente.
- Service: Contém as regras de negócio.
- Repository: Se comunica diretamente com o banco de dados.

O banco de dados é o MySQL, com as configurações de conexão definidas no arquivo application.properties do projeto. Para facilitar a comunicação com o banco de dados, utiliza-se o Spring Data JPA, que cria as queries SQL automaticamente com base nos métodos definidos no repository. Além disso, as entidades Java são mapeadas para as tabelas do banco de dados.

✨ Observação

Como o foco o projeto é a manipulação do Java e o CRUD das entidades, o sistema não possui a etapa de cadastro e login para acesso restrito.

📌 Projeto finalizado
