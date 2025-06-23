# 🗄️ Configuração do Banco de Dados PostgreSQL

Este documento contém os scripts SQL para configurar o banco de dados do e-commerce.

## 📋 Pré-requisitos

- PostgreSQL instalado e rodando
- Acesso ao banco de dados (psql, pgAdmin, ou similar)
- Banco de dados criado para o projeto

## 🚀 Como Executar os Scripts

### 1. Conectar ao PostgreSQL

```bash
# Via linha de comando
psql -U seu_usuario -d nome_do_banco

# Ou via pgAdmin
# Abra o pgAdmin e conecte ao seu servidor
```

### 2. Executar os Scripts na Ordem

```sql
-- 1. Primeiro, execute o script de produtos
\i database_products.sql

-- 2. Depois, execute o script de pedidos
\i database_orders.sql

-- 3. Opcional: Execute o script de teste
\i test_order.sql
```

## 📊 Estrutura das Tabelas

### Tabela `products`
```sql
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    image_url VARCHAR(500),
    category VARCHAR(100),
    stock INTEGER DEFAULT 0,
    rating DECIMAL(3,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Tabela `orders`
```sql
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_number VARCHAR(50) UNIQUE NOT NULL,
    status VARCHAR(50) DEFAULT 'pending',
    total_amount DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Tabela `order_items`
```sql
CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders(id) ON DELETE CASCADE,
    product_id INTEGER REFERENCES products(id) ON DELETE CASCADE,
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 🛍️ Produtos Incluídos

O script `database_products.sql` inclui **25 produtos** distribuídos em 6 categorias:

- **Eletrônicos** (4 produtos)
- **Acessórios** (4 produtos)
- **Fotografia** (4 produtos)
- **Informática** (4 produtos)
- **Smartphones** (3 produtos)
- **Gaming** (4 produtos)

## 🔧 Funcionalidades Implementadas

### Função `create_order_with_items(items_json)`
Cria um pedido com itens a partir de um JSON:
```sql
SELECT create_order_with_items('[
    {"productId": 1, "quantity": 2},
    {"productId": 3, "quantity": 1}
]');
```

### Trigger `generate_order_number()`
Gera automaticamente números de pedido no formato: `ORD-YYYYMMDD-XXXX`

### View `order_details`
Visualização completa dos pedidos com detalhes dos produtos.

## 📈 Consultas Úteis

### Ver todos os produtos
```sql
SELECT * FROM products ORDER BY category, name;
```

### Ver estatísticas por categoria
```sql
SELECT 
    category,
    COUNT(*) as total_products,
    AVG(price) as avg_price,
    SUM(stock) as total_stock
FROM products 
GROUP BY category;
```

### Ver todos os pedidos
```sql
SELECT * FROM order_details ORDER BY created_at DESC;
```

### Produtos mais vendidos
```sql
SELECT 
    p.name,
    SUM(oi.quantity) as total_sold
FROM order_items oi
JOIN products p ON oi.product_id = p.id
GROUP BY p.id, p.name
ORDER BY total_sold DESC;
```

## 🔗 Integração com a API

Para integrar com sua API backend, use a função `create_order_with_items()`:

```sql
-- Exemplo de uso na API
SELECT create_order_with_items($1) 
-- Onde $1 é o JSON enviado pelo frontend:
-- [{"productId": 1, "quantity": 2}, {"productId": 3, "quantity": 1}]
```

## 🚨 Status dos Pedidos

Os pedidos podem ter os seguintes status:
- `pending` - Pendente (padrão)
- `confirmed` - Confirmado
- `shipped` - Enviado
- `delivered` - Entregue
- `cancelled` - Cancelado

## 📝 Notas Importantes

1. **Backup**: Sempre faça backup antes de executar scripts em produção
2. **Índices**: Os scripts criam índices para melhor performance
3. **Constraints**: As tabelas têm constraints de integridade referencial
4. **Timestamps**: Todas as tabelas têm campos de auditoria (created_at, updated_at)

## 🐛 Troubleshooting

### Erro: "relation 'products' does not exist"
- Execute primeiro o `database_products.sql`

### Erro: "function create_order_with_items does not exist"
- Execute primeiro o `database_orders.sql`

### Erro: "duplicate key value violates unique constraint"
- O produto já existe, use `INSERT ... ON CONFLICT DO NOTHING`

## 📞 Suporte

Se encontrar problemas, verifique:
1. Versão do PostgreSQL (recomendado: 12+)
2. Permissões do usuário
3. Logs de erro do PostgreSQL 
