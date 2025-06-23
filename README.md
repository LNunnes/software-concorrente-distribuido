
# Sistema Distribuído de E-commerce com Apache Kafka

## 🔧 Visão Geral

Este projeto simula um sistema distribuído de e-commerce composto por três microserviços integrados via Apache Kafka. Os serviços trocam eventos em tempo real para processar pedidos, verificar estoque e notificar clientes.

## 🧱 Arquitetura dos Serviços

- `Order-Service`: produtor que envia pedidos confirmados para o tópico `orders`.
- `Inventory-Service`: consumidor de `orders` e produtor de `inventory-events`.
- `Notification-Service`: consumidor de `inventory-events` que simula envio de e-mails ou SMS.

## 🧩 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Apache Kafka (local)
- PostgreSQL
- Maven

---

## ✅ Requisitos Funcionais

| Código | Descrição |
|--------|-----------|
| RF‑1   | Criar tópicos `orders` e `inventory-events` via `kafka-topics.sh`. |
| RF‑2   | Order-Service expõe API REST `POST /orders`. |
| RF‑3   | Inventory-Service processa pedidos e publica resultados no tópico. |
| RF‑4   | Notification-Service registra no console as notificações simuladas. |

---

## 🔁 Requisitos Não-Funcionais

### 1. Escalabilidade

O Apache Kafka permite escalabilidade horizontal de consumidores e produtores. Podemos:

- Aumentar o número de **partições** por tópico, permitindo múltiplos consumidores em paralelo no mesmo grupo.
- Escalar serviços (ex: múltas instâncias de `Inventory-Service`) para processar mensagens em paralelo com **balanceamento automático** via Kafka Consumer Groups.

### 2. Tolerância à Falha

É a capacidade do sistema continuar operando mesmo em caso de falhas. Exemplos:

- Se o `Inventory-Service` falhar, o Kafka mantém os eventos no tópico `orders` e os entrega quando o serviço voltar.
- Kafka também replica dados entre brokers (caso seja um cluster), garantindo alta disponibilidade.

### 3. Idempotência

Idempotência significa que o processamento de uma mesma mensagem múltiplas vezes não afeta o resultado final.

Como garantir:

- **Na camada de negócio**: validar se um pedido já foi processado usando o UUID.
- **No banco de dados**: usar chaves únicas no campo de identificação do pedido.
- **No Kafka Producer**: ativar `enable.idempotence=true` para evitar envio duplicado de mensagens.

---

## Instalação do backend

### 1. Pré-requisitos

- Java 21
- Apache Kafka instalado localmente
- PostgreSQL rodando localmente
- Kafka Tool (opcional, visualização)

### 2. Criar tópicos

```bash

bin/kafka-topics.sh --create --topic orders --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

bin/kafka-topics.sh --create --topic inventory-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

### 3. Rodar os serviços

Compile os módulos na ordem correta:

```bash
cd ecommerce-common
mvn clean install

cd ../order
mvn spring-boot:run

cd ../inventory
mvn spring-boot:run

cd ../notification
mvn spring-boot:run
```

### 4. Testar (Caso deseje testar sem interface visual)

Envie uma requisição:

```http
POST http://localhost:8080/api/orders
Content-Type: application/json

{
    "items": [
        {
            "productId": 1,
            "quantity": 1
        }
    ]
}
```

## Instalação do Frontend

### 1. Node.js (Obrigatório)

#### **Windows**
1. Acesse [nodejs.org](https://nodejs.org)
2. Baixe a versão **LTS** (recomendado)
3. Execute o instalador e siga as instruções
4. Verifique a instalação:
```bash
node --version
npm --version
```

#### **Linux (Ubuntu/Debian)**
```bash
# Atualizar repositórios
sudo apt update

# Instalar Node.js
curl -fsSL https://deb.nodesource.com/setup_lts.x | sudo -E bash -
sudo apt-get install -y nodejs

# Verificar instalação
node --version
npm --version
```

### **2. Angular CLI (Obrigatório)**

Após instalar o Node.js, instale o Angular CLI globalmente:

```bash
npm install -g @angular/cli
```

**Verificar instalação:**
```bash
ng version
```

##  Configuração do Projeto

### **1. Clonar Projeto**

### **2. Instalar Dependências**

```bash
# Navegar para a pasta do projeto
cd ecommerce-frontend

# Instalar todas as dependências
npm install
```

**Tempo estimado:** 2-5 minutos (depende da conexão)

##  Executando o Projeto

### **1. Servidor de Desenvolvimento**

```bash

# Ou abrir automaticamente no navegador
ng serve --open
```

