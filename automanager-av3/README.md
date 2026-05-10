# AutoManager - AV3 - Sistema de Gerenciamento Automotivo

## Descrição

Atividade de avaliação individual 3 (AV3) da matéria de WEBIII

## Endpoints da API

### Empresa
```
GET    /empresa                        - Listar todas as empresas
GET    /empresa/{id}                   - Obter empresa por ID
POST   /empresa/cadastro               - Cadastrar nova empresa
PUT    /empresa/atualizar/{id}         - Atualizar empresa
DELETE /empresa/excluir/{id}           - Excluir empresa
POST   /empresa/{empresaId}/usuario/{usuarioId}      - Vincular usuário à empresa
DELETE /empresa/{empresaId}/usuario/{usuarioId}      - Desvincular usuário da empresa
```

### Usuario
```
GET    /usuario                        - Listar todos os usuários
GET    /usuario/{id}                   - Obter usuário por ID
POST   /usuario/cadastro               - Cadastrar novo usuário
PUT    /usuario/atualizar/{id}         - Atualizar usuário
DELETE /usuario/excluir/{id}           - Excluir usuário
POST   /usuario/{usuarioId}/veiculo/{veiculoId}     - Vincular veículo ao usuário
DELETE /usuario/{usuarioId}/veiculo/{veiculoId}     - Desvincular veículo do usuário
```

### Veiculo
```
GET    /veiculo                        - Listar todos os veículos
GET    /veiculo/{id}                   - Obter veículo por ID
POST   /veiculo/cadastro               - Cadastrar novo veículo
PUT    /veiculo/atualizar/{id}         - Atualizar veículo
DELETE /veiculo/excluir/{id}           - Excluir veículo
```

### Mercadoria
```
GET    /mercadoria                     - Listar todas as mercadorias
GET    /mercadoria/{id}                - Obter mercadoria por ID
POST   /mercadoria/cadastro            - Cadastrar nova mercadoria
PUT    /mercadoria/atualizar/{id}      - Atualizar mercadoria
DELETE /mercadoria/excluir/{id}        - Excluir mercadoria
```

### Servico
```
GET    /servico                        - Listar todos os serviços
GET    /servico/{id}                   - Obter serviço por ID
POST   /servico/cadastro               - Cadastrar novo serviço
PUT    /servico/atualizar/{id}         - Atualizar serviço
DELETE /servico/excluir/{id}           - Excluir serviço
```

### Venda
```
GET    /venda                          - Listar todas as vendas
GET    /venda/{id}                     - Obter venda por ID
POST   /venda/cadastro                 - Cadastrar nova venda
PUT    /venda/atualizar/{id}           - Atualizar venda
DELETE /venda/excluir/{id}             - Excluir venda
POST   /venda/{vendaId}/funcionario/{funcionarioId}  - Vincular funcionário à venda
POST   /venda/{vendaId}/cliente/{clienteId}          - Vincular cliente à venda
POST   /venda/{vendaId}/veiculo/{veiculoId}          - Vincular veículo à venda
POST   /venda/{vendaId}/mercadoria/{mercadoriaId}    - Adicionar mercadoria à venda
DELETE /venda/{vendaId}/mercadoria/{mercadoriaId}    - Remover mercadoria da venda
POST   /venda/{vendaId}/servico/{servicoId}          - Adicionar serviço à venda
DELETE /venda/{vendaId}/servico/{servicoId}          - Remover serviço da venda
```

### Telefone, Documento, Endereco, CredencialAcesso
Seguem o padrão CRUD básico:
```
GET    /{recurso}                      - Listar
GET    /{recurso}/{id}                 - Obter por ID
POST   /{recurso}/cadastro             - Cadastrar
PUT    /{recurso}/atualizar/{id}       - Atualizar
DELETE /{recurso}/excluir/{id}         - Excluir

?      /gerson                      
```

---

## Como Executar

### Pré-requisitos
- **Java 17+**
- **Maven 3.6+**

### Build e Execução

```bash
# Clonar/extrair o projeto
cd automanager-av3

# Compilar e baixar dependências
mvn clean install

# Executar
mvn spring-boot:run
```

A aplicação será iniciada em `http://localhost:8080`


## Exemplos de Requisições

### 1. Cadastrar Endereco
```json
POST /endereco/cadastro
{
  "estado": "SP",
  "cidade": "Sao Paulo",
  "bairro": "vila",
  "rua": "Avenida 1",
  "numero": "1000",
  "codigoPostal": "04082-000",
  "informacoesAdicionais": "Nao"
}
```

### 2. Cadastrar Empresa
```json
POST /empresa/cadastro
{
  "razaoSocial": "AutoBots Manutenção LTDA",
  "nomeFantasia": "AutoBots SP",
  "cnpj": "12.345.678/0001-90",
  "endereco": {
    "id": 1
  }
}
```

### 3. Cadastrar Credencial
```json
POST /credencial/cadastro
{
  "nomeUsuario": "joao.silva",
  "senha": "senha123"
}
```

### 4. Cadastrar Usuario
```json
POST /usuario/cadastro
{
  "nome": "João Silva",
  "email": "joao@example.com",
  "dataNascimento": "1990-05-15",
  "perfil": ["CLIENTE", "FUNCIONARIO"],
  "credencial": {
    "id": 1
  },
  "endereco": {
    "id": 1
  }
}
```

### 5. Cadastrar Veiculo
```json
POST /veiculo/cadastro
{
  "modelo": "Corolla",
  "fabricante": "Toyota",
  "anoFabricacao": "2022",
  "placa": "ABC-1234"
}
```

### 6. Vincular Veiculo a Usuario
```
POST /usuario/1/veiculo/1
```

### 7. Cadastrar Mercadoria
```json
POST /mercadoria/cadastro
{
  "nome": "Filtro de Ar",
  "fabricante": "Bosch",
  "quantidade": 50,
  "valor": 45.50,
  "validade": "2025-12-31",
  "descricao": "Filtro de ar"
}
```

### 8. Cadastrar Servico
```json
POST /servico/cadastro
{
  "nome": "Troca de Óleo",
  "valor": 120.00,
  "descricao": "Troca de óleo e filtro"
}
```

### 9. Cadastrar Venda
```json
POST /venda/cadastro
{
  "funcionario": { "id": 1 },
  "cliente": { "id": 2 },
  "veiculo": { "id": 1 }
}
```

### 10. Adicionar Mercadoria à Venda
```
POST /venda/1/mercadoria/1
```

### 11. Adicionar Servico à Venda
```
POST /venda/1/servico/1
```

