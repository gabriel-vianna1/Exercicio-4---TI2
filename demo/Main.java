import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main {

    public static void main(String[] args) {
        String subscriptionKey = "2088889eebaa474992a8723f26c17b99"; 
        String endpoint = "https://iaex04.cognitiveservices.azure.com/"; 
        String textToAnalyze = "Este é um exemplo de texto para análise de sentimentos.";

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(endpoint + "/text/analytics/v3.0/sentiment");
            
            // Corpo da requisição (JSON)
            String body = "{"
                    + "\"documents\": ["
                    + "{ \"id\": \"1\", \"language\": \"pt\", \"text\": \"" + textToAnalyze + "\" }"
                    + "]"
                    + "}";

            // Define o corpo da requisição
            StringEntity entity = new StringEntity(body);
            request.setEntity(entity);
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            request.setHeader("Content-type", "application/json");

            // Envia a requisição
            HttpResponse response = httpClient.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity());
            
            // Exibe a resposta JSON no console
            System.out.println(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
