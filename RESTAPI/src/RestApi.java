import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestApi {
    public static void main(String[] args) {
        try {
            Transcript transcript = new Transcript();
            transcript.setAudio_url("https://github.com/johnmarty3/JavaAPITutorial/blob/main/Thirsty.mp4?raw=true");

            Gson gson = new Gson();
            String jsonRequest = gson.toJson(transcript);
            System.out.println("Request JSON: " + jsonRequest);

            // Create POST request to create a transcript
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                    .header("Authorization", "e95a85062e684e14bf1d50b9d9c210db")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Post Response: " + postResponse.body());

            transcript = gson.fromJson(postResponse.body(), Transcript.class);
            System.out.println("Transcript ID: " + transcript.getId());

            // Create GET request to check the transcription status
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                    .header("Authorization", "e95a85062e684e14bf1d50b9d9c210db")
                    .build();

            // Poll for the status of the transcription
            while (true) {
                HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                transcript = gson.fromJson(getResponse.body(), Transcript.class);
                System.out.println("Transcript Status: " + transcript.getStatus());

                if ("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())) {
                    break;
                }
                Thread.sleep(1000); // Wait before the next status check
            }

            System.out.println("Transcription completed!");
            System.out.println("Transcription Text: " + transcript.getText());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace(); // To Handle exceptions
        }
    }
}