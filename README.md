# FIAP Produto Service

API REST para gerenciamento de produtos, baseada em Quarkus e Clean Architecture.

---

## 🚀 Tecnologias

- **Java 21**
- **Quarkus** (REST, DI, DevMode)
- **PostgreSQL** (DB)
- **Flyway** (migrações SQL)
- **MapStruct** (DTO ↔ Domínio)
- **Jacoco** (cobertura de testes)
- **Arquitetura Limpa (Clean Architecture)**

---

## 📋 Endpoints REST

| Método | Endpoint             | Descrição               |
|--------|----------------------|-------------------------|
| GET    | `/products`          | Listar todos os produtos|
| GET    | `/products/{id}`     | Buscar produto por ID   |
| POST   | `/products`          | Criar novo produto      |
| PUT    | `/products/{id}`     | Atualizar produto       |
| DELETE | `/products/{id}`     | Remover produto         |

### Exemplos

#### Criar produto

```json
POST /products
{
  "name": "Café Especial",
  "sku": "SKU123",
  "price": 18.5
}
```

#### Resposta

```json
{
  "id": "uuid...",
  "name": "Café Especial",
  "sku": "SKU123",
  "price": 18.5
}
```

---

## 🏃 Como Rodar

### 1. Subir tudo com Docker (App + DB)

```bash
docker-compose up --build
```
- API disponível em: http://localhost:8081/products
- PostgreSQL em: localhost:5433

### 2. Rodar Localmente

1. Suba o banco:
   ```bash
   docker-compose up -d postgres
   ```
2. Rode a aplicação:
   ```bash
   ./mvnw quarkus:dev
   ```
3. Acesse Dev UI: http://localhost:8081/q/dev-ui/

---

## 🧪 Testes & Cobertura

- Testes automatizados:
  ```bash
  ./mvnw clean verify -Dquarkus.test.coverage=true
  ```
- Relatório Jacoco:  
  `target/jacoco-report/index.html`

---

## 🗄️ Banco de Dados

- **Desenvolvimento:** PostgreSQL (porta 5433)
- **Migrações automáticas:** via Flyway (`src/main/resources/db/migration`)

---

## 🧱 Estrutura de Pastas

- `domain` - modelo rico do domínio (Product)
- `usecase` - casos de uso (application)
- `gateway` - interface e implementação do gateway (database)
- `controller` - REST API
- `infra` - handlers globais (erros, etc)
- `mapper` - conversores (DTO ↔ domínio)



