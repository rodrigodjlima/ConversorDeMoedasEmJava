Conversor de Moedas em Java
Um projeto simples de conversor de moedas que utiliza a API ExchangeRate-API para obter cotações em tempo real.

▶️ Como Executar

Clone o repositório

Importe o projeto no IntelliJ

Voce pode também incluir sua própria API_KEY

clique no link https://www.exchangerate-api.com/ crie sua conta e adiquira sua API_KEY

e pode configar o mesmo na classe ApiConfig.

É só trocar API_KEY atual para a sua API_KEY.

Execute a classe ConversorMoedas

Siga as instruções no console


<img width="494" height="204" alt="image" src="https://github.com/user-attachments/assets/a4f609b2-5e60-40ef-8fab-c078a5de6737" />



📌 Próximos possíveis Passos

Adicionar mais moedas

Implementar histórico de conversões

Melhorar tratamento de erros

📋 Passos de Implementação

1) Configurando o ambiente de trabalho
JDK 11 ou superior instalado

IntelliJ IDEA (ou outra IDE Java)

Dependência Gson adicionada ao projeto

2) Conhecendo a API para trazer dados
Utilizamos a ExchangeRate-API (v6)

Endpoint base: https://v6.exchangerate-api.com/v6/{API_KEY}/latest/USD

Retorna dados em JSON com as cotações atuais

3) Importando a biblioteca Gson no IntelliJ

adicione manualmente o arquivo JAR do Gson às dependências do projeto.

4) Construindo o Cliente para solicitações (HttpClient)
5) Construindo a Requisição (HttpRequest)
6) Construindo a Resposta (HttpResponse)
7) Analisando a resposta em formato JSON
8) Filtrando as moedas

Moedas disponíveis: USD, BRL, ARS, BOB, CLP, COP

Armazenadas em arrays para fácil acesso:

ARS - Peso argentino

BOB - Boliviano boliviano

BRL - Real brasileiro

CLP - Peso chileno

COP - Peso colombiano

USD - Dólar americano

9) Convertendo os valores

10) Interagindo com o usuário

Menu interativo via console

Scanner para capturar entrada do usuário

