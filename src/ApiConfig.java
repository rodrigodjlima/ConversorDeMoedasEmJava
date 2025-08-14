public class ApiConfig {
    // Classe dedicada para armazenar as configurações da API
    private static final String API_KEY = "d74df804acbc080485f6ca02";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static String getApiUrl() {
        return BASE_URL + API_KEY + "/latest/USD";
    }

    // Método adicional para verificar se a chave está configurada
    public static boolean isApiKeySet() {
        return API_KEY != null && !API_KEY.isEmpty();
    }
}