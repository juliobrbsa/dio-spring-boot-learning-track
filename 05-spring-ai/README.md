# 🚀 Entrega do Desafio: Evolução da API Financeira com Spring AI

## 1. O que o projeto faz
Esta aplicação é uma API Inteligente de orçamento financeiro construída com **Spring Boot** e **Spring AI**. Ela recebe comandos de voz (arquivos de áudio), transcreve o áudio para texto (Speech-to-Text), utiliza um modelo de linguagem (LLM) para interpretar a intenção da pessoa usuária, aciona funções Java reais via **Tool Calling** (para criar ou consultar transações no banco de dados) e devolve a resposta final sintetizada em áudio (Text-to-Speech).

## 2. Quais tecnologias foram usadas
- **Java 21**
- **Spring Boot 3** (Spring Web, Spring Data JPA)
- **Spring AI** (ChatClient, Tool Calling, AudioTranscription, AudioSpeech com integração OpenAI)
- **Banco de Dados Relacional / H2 / PostgreSQL**
- **Maven**

## 3. Qual melhoria foi implementada
Para evoluir o projeto base, foi implementada a camada de validação e alerta de orçamento (`ValidadorTransacaoService.java`), contemplando:
- **Validação preventiva antes de salvar transações:** Garante que o comando interpretado pela IA possua uma descrição válida e um valor estritamente positivo (`> 0`) antes de acionar a persistência no banco de dados.
- **Verificação de limite de orçamento:** Adiciona uma regra de negócio que emite um aviso automático na resposta da IA sempre que uma despesa individual ultrapassa o teto de alerta configurado (R$ 1.000,00), enriquecendo o retorno em áudio para o usuário.

## 4. Como executar a aplicação
1. Clone o repositório:
   `git clone https://github.com/SEU-USUARIO/dio-spring-boot-learning-track.git`
2. Acesse a pasta do projeto:
   `cd dio-spring-boot-learning-track/05-spring-ai`
3. Configure a variável de ambiente com sua chave da OpenAI:
   `export OPENAI_API_KEY="sua-chave-aqui"`
4. Execute o projeto com Maven:
   `./mvnw spring-boot:run`

## 5. Como testar o fluxo principal
Com a aplicação rodando em `http://localhost:8080`, envie uma requisição `POST` (via Postman, Insomnia ou cURL) com um arquivo de áudio (`.mp3` ou `.wav`) contendo um comando como *"Registre uma despesa de mercado no valor de 150 reais"*:

`curl -X POST http://localhost:8080/api/assistant/audio -H "Content-Type: multipart/form-data" -F "file=@comando_despesa.mp3" --output resposta_assistente.mp3`

A API processará o áudio, registrará a transação no banco e salvará o retorno falado no arquivo `resposta_assistente.mp3`.

## 6. O que aprendi durante o desafio
- Como conectar modelos de IA Generativa a regras de negócio reais em Java usando **Tool Calling**, permitindo que a IA execute métodos da aplicação com segurança.
- Como orquestrar fluxos multimodais (Áudio -> Texto -> IA/Funções -> Áudio) usando a abstração do `ChatClient` e os modelos de transcrição e síntese do **Spring AI**.
- A importância de manter a separação de responsabilidades e validar os dados recebidos da IA antes de persistir informações críticas no banco de dados.
