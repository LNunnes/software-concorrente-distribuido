### **1. Node.js (Obrigatório)**

#### **Windows**
1. Acesse [nodejs.org](https://nodejs.org)
2. Baixe a versão **LTS** (recomendado)
3. Execute o instalador e siga as instruções
4. Verifique a instalação:
```bash
node --version
npm --version
```

#### **macOS**
```bash
# Usando Homebrew (recomendado)
brew install node

# Ou baixe do site oficial
# https://nodejs.org
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

### **3. Git (Opcional, mas recomendado)**

#### **Windows**
1. Baixe em [git-scm.com](https://git-scm.com)
2. Execute o instalador
3. Verifique: `git --version`

#### **macOS**
```bash
# Já vem instalado ou use Homebrew
brew install git
```

#### **Linux**
```bash
sudo apt install git
```

### **4. Editor de Código (Recomendado)**

#### **Visual Studio Code**
1. Baixe em [code.visualstudio.com](https://code.visualstudio.com)
2. Instale as extensões recomendadas:
   - Angular Language Service
   - TypeScript Importer
   - Auto Rename Tag
   - Bracket Pair Colorizer

## 📦 Configuração do Projeto

### **1. Clonar/Download do Projeto**

### **2. Instalar Dependências**

```bash
# Navegar para a pasta do projeto
cd ecommerce-frontend

# Instalar todas as dependências
npm install
```

**Tempo estimado:** 2-5 minutos (depende da conexão)

## 🚀 Executando o Projeto

### **1. Servidor de Desenvolvimento**

```bash

# Ou abrir automaticamente no navegador
ng serve --open
```
