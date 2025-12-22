# 📖 Livro de Receitas

Sistema de gerenciamento de receitas desenvolvido com Spring Boot, permitindo que usuários registrem, visualizem e gerenciem suas receitas favoritas.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.0** (com spring-boot-starter-webmvc)
- **Spring Security** - Autenticação e autorização
- **JWT (JSON Web Token)** - Dependências incluídas (implementação em desenvolvimento)
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **H2 Database** - Banco de dados em memória para desenvolvimento
- **Thymeleaf** - Engine de templates para views
- **SpringDoc OpenAPI (Swagger)** - Documentação interativa da API
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências

## 📋 Funcionalidades

### Autenticação
- ✅ Registro de novos usuários (`/auth/register`)
- ✅ Login de usuários (`/auth/login`)
- ✅ Autenticação HTTP Basic com Spring Security
- ✅ Codificação de senhas com BCrypt
- ✅ Proteção de rotas com Spring Security
- ⏳ Autenticação JWT (dependências incluídas, implementação em desenvolvimento)

### Receitas
- ✅ Listar todas as receitas do usuário logado (`GET /api/receitas`)
- ✅ Buscar receita por ID (`GET /api/receitas/{id}`)
- ✅ Criar nova receita (`POST /api/receitas`)
- ✅ Atualizar receita existente (`PUT /api/receitas/{id}`)
- ✅ Deletar receita (`DELETE /api/receitas/{id}`)
- ✅ Usuários só podem visualizar, editar e deletar suas próprias receitas

### Documentação da API
- ✅ Documentação interativa com Swagger/OpenAPI
- ✅ Interface Swagger UI para testar endpoints
- ✅ Documentação automática de todos os endpoints da API

### Estrutura de Dados
- **Usuários**: id, username (único), password (codificado com BCrypt)
- **Receitas**: id, título, descrição, tempo de preparo, dificuldade, temperatura, ingredientes (array de objetos), instruções (array de strings), usuário (relacionamento ManyToOne)
- **Categorias**: modelo criado (id, nome, slug, isDefault, userID) - endpoints ainda não implementados
- **Favoritos**: modelo criado (id, userID) - endpoints ainda não implementados

## 🛠️ Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java 17** ou superior
- **Maven 3.6+**
- **PostgreSQL** (para produção) ou **H2** (para desenvolvimento)
- **IDE** de sua preferência (IntelliJ IDEA, Eclipse, VS Code, etc.)

## 📦 Instalação e Configuração

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd Livro-de-Receitas
```

### 2. Configure o banco de dados

Edite o arquivo `src/main/resources/application.properties` com suas credenciais do PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/livro-receita
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
```

### 3. Crie o banco de dados

No PostgreSQL, execute:

```sql
CREATE DATABASE livro_receita;
```

### 4. Compile o projeto

```bash
mvn clean install
```

### 5. Execute a aplicação

```bash
mvn spring-boot:run
```

Ou execute a classe `LivroDeReceitasApplication.java` diretamente na sua IDE.

A aplicação estará disponível em: `http://localhost:8080`

### 6. Documentação da API (Swagger)

O projeto inclui documentação interativa da API usando Swagger/OpenAPI. Após iniciar a aplicação, acesse:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs (JSON)**: `http://localhost:8080/v3/api-docs`
- **API Docs (YAML)**: `http://localhost:8080/v3/api-docs.yaml`

A interface Swagger UI permite:
- Visualizar todos os endpoints da API
- Ver detalhes de cada endpoint (métodos, parâmetros, respostas)
- Testar os endpoints diretamente na interface
- Ver exemplos de requisições e respostas
- Autenticar usando HTTP Basic Auth diretamente na interface

**Nota**: As rotas do Swagger são públicas e não requerem autenticação.

### 7. Interface Frontend

O projeto inclui uma interface HTML completa localizada em `src/main/resources/static/index.html` que permite:

- **Autenticação**: Registro e login de usuários
- **Gerenciamento de Receitas**:
  - Criar novas receitas com sistema de ingredientes e instruções dinâmicas
  - Visualizar lista de receitas em cards
  - Editar receitas existentes
  - Deletar receitas
- **Interface Moderna**: Design responsivo com CSS e JavaScript vanilla
- **Funcionalidades**:
  - Sistema de ingredientes com campos separados (nome e quantidade) e botão "+" para adicionar
  - Sistema de instruções com botão "+" para adicionar cada passo
  - Visualização detalhada de receitas com expand/collapse
  - Autenticação HTTP Basic automática nas requisições

## 📡 Endpoints da API

### Autenticação

#### Registrar Usuário
```http
POST /auth/register
Content-Type: application/json

{
  "username": "usuario123",
  "password": "senha123"
}
```

#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "usuario123",
  "password": "senha123"
}
```

**Resposta (sucesso):**
```json
{
  "id": 1,
  "username": "usuario123",
  "password": "$2a$10$..."
}
```

**Resposta (erro):**
```json
"Credenciais invalidas"
```

### Receitas

#### Listar Todas as Receitas
```http
GET /api/receitas
Authorization: Basic {credenciais_base64}
```

**Resposta:**
```json
[
  {
    "id": 1,
    "title": "Bolo de Chocolate",
    "description": "Delicioso bolo de chocolate caseiro",
    "ingredients": [
      {
        "item": "Farinha de trigo",
        "quantity": "2 xícaras"
      }
    ],
    "instructions": [
      "Misture os ingredientes secos",
      "Asse por 40 minutos"
    ],
    "usuario": "usuario123"
  }
]
```

#### Buscar Receita por ID
```http
GET /api/receitas/{id}
Authorization: Basic {credenciais_base64}
```

**Resposta:**
```json
{
  "id": 1,
  "title": "Bolo de Chocolate",
  "description": "Delicioso bolo de chocolate caseiro",
  "ingredients": [
    {
      "item": "Farinha de trigo",
      "quantity": "2 xícaras"
    }
  ],
  "instructions": [
    "Misture os ingredientes secos",
    "Asse por 40 minutos"
  ],
  "usuario": "usuario123"
}
```

#### Criar Receita
```http
POST /api/receitas
Authorization: Basic {credenciais_base64}
Content-Type: application/json

{
  "title": "Bolo de Chocolate",
  "description": "Delicioso bolo de chocolate caseiro",
  "prepTime": "60 minutos",
  "difficulty": "Médio",
  "temperature": "180°C",
  "ingredients": [
    {
      "item": "Farinha de trigo",
      "quantity": "2 xícaras"
    },
    {
      "item": "Açúcar",
      "quantity": "1 xícara"
    }
  ],
  "instructions": [
    "Misture os ingredientes secos",
    "Adicione os ingredientes líquidos",
    "Asse por 40 minutos"
  ]
}
```

**Nota**: O DTO retornado não inclui `prepTime`, `difficulty` e `temperature` (filtrados propositalmente para demonstrar o uso de DTOs).

#### Atualizar Receita
```http
PUT /api/receitas/{id}
Authorization: Basic {credenciais_base64}
Content-Type: application/json

{
  "title": "Bolo de Chocolate Atualizado",
  "description": "Delicioso bolo de chocolate caseiro - versão melhorada",
  "ingredients": [
    {
      "item": "Farinha de trigo",
      "quantity": "2 xícaras"
    },
    {
      "item": "Açúcar",
      "quantity": "1 xícara"
    }
  ],
  "instructions": [
    "Misture os ingredientes secos",
    "Adicione os ingredientes líquidos",
    "Asse por 40 minutos"
  ]
}
```

**Resposta:**
```json
{
  "id": 1,
  "title": "Bolo de Chocolate Atualizado",
  "description": "Delicioso bolo de chocolate caseiro - versão melhorada",
  "ingredients": [
    {
      "item": "Farinha de trigo",
      "quantity": "2 xícaras"
    }
  ],
  "instructions": [
    "Misture os ingredientes secos",
    "Asse por 40 minutos"
  ],
  "usuario": "usuario123"
}
```

**Nota**: Apenas o dono da receita pode atualizá-la. Tentativas de editar receitas de outros usuários retornarão erro.

#### Deletar Receita
```http
DELETE /api/receitas/{id}
Authorization: Basic {credenciais_base64}
```

**Resposta:** `204 No Content`

**Nota**: Apenas o dono da receita pode deletá-la. Tentativas de deletar receitas de outros usuários retornarão erro.

## 🔒 Segurança

- As rotas de receitas (`/api/receitas/**`) são protegidas e requerem autenticação HTTP Basic
- A autenticação HTTP Basic deve ser enviada no header `Authorization: Basic {credenciais_base64}`
- As rotas de autenticação (`/auth/**`) são públicas
- As rotas do Swagger (`/swagger-ui/**`, `/v3/api-docs/**`) são públicas para facilitar o acesso à documentação
- As senhas são codificadas usando BCrypt
- **Isolamento de dados**: Cada usuário só pode visualizar, editar e deletar suas próprias receitas
  - O sistema verifica automaticamente a propriedade da receita antes de permitir operações de edição ou exclusão
  - Tentativas de acessar receitas de outros usuários retornam erro
- **Nota**: As dependências JWT estão incluídas no projeto, mas a implementação completa ainda está em desenvolvimento

## 📁 Estrutura do Projeto

```
src/main/java/com/roberto/Livro_de_Receitas/
├── controller/          # Controladores REST
│   ├── AuthController.java
│   └── ReceitasController.java
├── model/              # Entidades JPA
│   ├── UsuariosDB.java
│   ├── ReceitasDB.java
│   ├── CategoriasDB.java
│   └── FavoritosDB.java
├── repository/         # Repositórios JPA
│   ├── UsuariosRepository.java
│   └── ReceitasRepository.java
├── service/            # Lógica de negócio
│   ├── UsuariosService.java
│   ├── ReceitasService.java
│   └── UsuariosDetailsService.java
├── DTO/               # Data Transfer Objects
│   ├── UsuariosDTO.java
│   ├── ReceitasDTO.java
│   └── StandardErrorDTO.java
├── security/          # Configurações de segurança
│   ├── SecurityConfig.java
├── exception/         # Tratamento de exceções
│   ├── GlobalExceptionHandler.java
│   └── RecursoNaoEncontradoException.java
└── LivroDeReceitasApplication.java
```

## 🧪 Testes

Para executar os testes:

```bash
mvn test
```

## 🔧 Configurações Adicionais

### Hibernate

O projeto está configurado para criar/atualizar as tabelas automaticamente:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Console H2 (Desenvolvimento)

Se estiver usando H2, o console estará disponível em:
`http://localhost:8080/h2-console`

### Swagger/OpenAPI

A documentação da API está disponível através do SpringDoc OpenAPI:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs JSON**: `http://localhost:8080/v3/api-docs`
- **API Docs YAML**: `http://localhost:8080/v3/api-docs.yaml`

A documentação é gerada automaticamente a partir das anotações dos controladores e está sempre sincronizada com o código.

## 📝 Notas de Desenvolvimento

- O projeto utiliza **DTOs** para transferência de dados, separando a camada de apresentação da camada de persistência
  - O `ReceitasDTO` filtra propositalmente alguns campos (prepTime, difficulty, temperature) para demonstrar o uso de DTOs
- **Lombok** é utilizado para reduzir código boilerplate (getters, setters, construtores)
- O tratamento de exceções é feito globalmente através do `GlobalExceptionHandler`
- A autenticação atual utiliza **HTTP Basic Auth** com BCrypt para codificação de senhas
- As dependências JWT estão incluídas no projeto, mas a implementação completa ainda está em desenvolvimento
- As receitas possuem relacionamento ManyToOne com usuários, permitindo rastrear o criador de cada receita
- **Segurança de dados**: O sistema implementa controle de acesso baseado em propriedade, garantindo que usuários só possam modificar suas próprias receitas
- Os modelos `CategoriasDB` e `FavoritosDB` estão criados, mas os endpoints ainda não foram implementados
- A interface frontend utiliza JavaScript vanilla para comunicação com a API, mantendo o token Basic Auth durante a sessão
- **Swagger/OpenAPI**: O projeto utiliza SpringDoc OpenAPI para documentação automática e interativa da API, permitindo testar endpoints diretamente na interface Swagger UI

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT.

## 👤 Autor

**Roberto**

---

Desenvolvido usando Spring Boot
