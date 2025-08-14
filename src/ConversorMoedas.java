import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorMoedas {
    // HttpClient como campo da classe para reutilização
    private final HttpClient httpClient;

    // Moedas disponíveis para conversão
    private static final String[] CODIGOS_MOEDAS = {"USD", "BRL", "ARS", "BOB", "CLP", "COP"};
    private static final String[] NOMES_MOEDAS = {
            "Dólar Americano",
            "Real Brasileiro",
            "Peso Argentino",
            "Boliviano Boliviano",
            "Peso Chileno",
            "Peso Colombiano"
    };

    public ConversorMoedas() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public static void main(String[] args) {
        // Verifica se a API_KEY está configurada
        if (!ApiConfig.isApiKeySet()) {
            System.out.println("Erro: Chave da API não configurada.");
            return;
        }

        ConversorMoedas conversor = new ConversorMoedas();
        conversor.iniciar();
    }

    public void iniciar() {
        try {
            System.out.println("Obtendo taxas de câmbio...");
            JsonObject taxasCambio = obterTaxasCambio();

            if (taxasCambio != null) {
                iniciarInterfaceUsuario(taxasCambio);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao conectar com o serviço de câmbio: " + e.getMessage());
        }
    }

    private JsonObject obterTaxasCambio() throws IOException, InterruptedException {
        // Usa a URL da classe de configuração
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ApiConfig.getApiUrl()))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            return jsonResponse.getAsJsonObject("conversion_rates");
        } else {
            System.out.println("Erro ao obter taxas. Código: " + response.statusCode());
            return null;
        }
    }

    private void iniciarInterfaceUsuario(JsonObject taxasCambio) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Olá, bem vindo ao Conversor de Moedas atualizado ===");

            for (int i = 0; i < CODIGOS_MOEDAS.length; i++) {
                System.out.printf("%d - %s (%s)%n", i+1, NOMES_MOEDAS[i], CODIGOS_MOEDAS[i]);
            }
            System.out.println("0 - Sair");

            System.out.print("Escolha a moeda de origem, a sua moeda atual (número): ");
            int origem = scanner.nextInt();

            if (origem == 0) break;
            if (origem < 1 || origem > CODIGOS_MOEDAS.length) {
                System.out.println("Opção inválida!");
                continue;
            }

            System.out.print("Escolha a moeda de destino, para qual irá converter a moeda atual (número): ");
            int destino = scanner.nextInt();

            if (destino < 1 || destino > CODIGOS_MOEDAS.length) {
                System.out.println("Opção inválida!");
                continue;
            }

            System.out.printf("Digite o valor em %s: ", NOMES_MOEDAS[origem-1]);
            double valor = scanner.nextDouble();

            double taxaOrigem = taxasCambio.get(CODIGOS_MOEDAS[origem-1]).getAsDouble();
            double taxaDestino = taxasCambio.get(CODIGOS_MOEDAS[destino-1]).getAsDouble();

            double valorConvertido = (valor / taxaOrigem) * taxaDestino;

            System.out.printf("\n%.2f %s = %.2f %s%n",
                    valor, NOMES_MOEDAS[origem-1],
                    valorConvertido, NOMES_MOEDAS[destino-1]);
        }

        System.out.println("Programa encerrado. Muito obrigado, volte sempre!");
        scanner.close();
    }
}