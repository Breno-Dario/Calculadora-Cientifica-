# 🧮 Calculadora Científica em Java

Este projeto é uma **Calculadora Científica** desenvolvida em **Java**, utilizando a biblioteca **Swing** para a interface gráfica.  
Ela possui dois modos: **Básico** e **Científico**, permitindo realizar desde operações simples até cálculos avançados.

---

## 🚀 Funcionalidades

- **Modo Básico**
    - Operações aritméticas: `+`, `-`, `*`, `/`
    - Parênteses `(` `)`
    - Cálculo de porcentagem `%`
    - Histórico (`Ans`, `I/P`)
    - Botões de controle: `C` (limpar), `⌫` (apagar), `OFF` (sair)

- **Modo Científico**
    - Potenciação: `x²`, `x³`
    - Raízes: `√` (quadrada), `∛` (cúbica)
    - Funções trigonométricas: `sin`, `cos`, `tan`
        - Inversas: `sin⁻¹`, `cos⁻¹`, `tan⁻¹`
    - Funções logarítmicas: `log` (base 10), `Ln` (logaritmo natural)
    - Exponencial: `e^x`
    - Fatorial: `x!`
    - Valor absoluto: `| |`
    - Constante: `π`
    - Permutação: `nPr`
    - Combinação: `nCr`
    - Alternância entre **Modo Básico (BSI)** e **Modo Científico (SCI)**

---

## 🖼️ Interface Gráfica

- Desenvolvida com **Java Swing**
- Layout organizado em **GridLayout**
- Dois tamanhos de janela:
    - **Básico:** 400x550
    - **Científico:** 600x700
- Tema em tons de cinza e botões estilizados

---

## 📂 Estrutura do Código

- **`CalculadoraCientifica`**  
  Classe principal que contém:
    - Criação da interface
    - Definição dos botões
    - Controle de eventos (`ActionListener`)
    - Alternância entre modos
    - Implementação de cálculos básicos e científicos

- **`ExpressionParser`**  
  Classe responsável por:
    - Interpretar expressões matemáticas
    - Aplicar operações e funções
    - Suporte a precedência de operadores e funções matemáticas (`sqrt`, `sin`, `cos`, etc.)

---

## ⚙️ Como Executar

### Pré-requisitos
- **Java JDK 17+** (ou versão compatível)

### Compilação
```bash
javac CalculadoraCientifica.java


```
### Imagens 

<img width="934" height="1022" alt="Captura de imagem_20251001_195814" src="https://github.com/user-attachments/assets/566aefaa-84af-4ecf-8b59-462d093fbd49" />

<img width="930" height="1021" alt="Captura de imagem_20251001_195831" src="https://github.com/user-attachments/assets/037c5c66-399c-4760-bbfa-4391a7a17b79" />



