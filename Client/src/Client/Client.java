package Client;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.entities.Cliente;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class Client {

    public static void main(String[] args) throws Exception {
        String url; 
        String server = "http://localhost:8089/server/webresources/"; 
         
    //////////// CLIENTE //////////////
    
        url = server+"clientes/v1";
        System.out.println("****************************************************");
        System.out.println("Servicio info Clientes: "+url);
        System.out.println(responseBody(url));    
    
        url = server+"clientes/v1/save?nombre=Penelopez&apellido=Veracruz&edad=18&direccion=CABA&email=karlita@gmail.com&telefono=2323232323&tipoDocumento=PASS&numeroDocumento=31003221";
        System.out.println("****************************************************");
        System.out.println("Servicio save: "+url);
        System.out.println(responseBody(url));

        url = server+"clientes/v1/all";
        System.out.println("****************************************************");
        System.out.println("Servicio all: "+url);
        System.out.println("List<Cliente>");
         // Armar List JSon
        List<Cliente> list = new Gson()
                .fromJson(responseBody(url), new TypeToken<List<Cliente>>() {
                }.getType());
        list.forEach(System.out::println);
        
        url = server+"clientes/v1/likeApellido?apellido=V";
        System.out.println("****************************************************");
        System.out.println("Servicio likeApellido: "+url);
        System.out.println("List<Cliente>");
         // Armar List JSon
        list = new Gson()
                .fromJson(responseBody(url), new TypeToken<List<Cliente>>() {
                }.getType());
        list.forEach(System.out::println);
        
        url = server+"clientes/v1/likeNombre?nombre=P";
        System.out.println("****************************************************");
        System.out.println("Servicio likeNombre: "+url);
        System.out.println("List<Cliente>");
         // Armar List JSon
        list = new Gson()
                .fromJson(responseBody(url), new TypeToken<List<Cliente>>() {
                }.getType());
        list.forEach(System.out::println);
        
        url = server+"clientes/v1/byId?id=46";
        System.out.println("****************************************************");
        System.out.println("Servicio byId: "+url);
        System.out.println(responseBody(url));
        
        url = server+"clientes/v1/byDocumento?tipoDocumento=DNI&numeroDocumento=12345600";
        System.out.println("****************************************************");
        System.out.println("Servicio byDocumento: "+url);
        System.out.println(responseBody(url));
        
       
        url = server+"clientes/v1/remove?id=46";
        System.out.println("****************************************************");
        System.out.println("Servicio remove: "+url);
        System.out.println(responseBody(url));
        
        
        //////////// ARTÍCULO //////////////
         /*
        url = server+"articulos/v1";
        System.out.println("****************************************************");
        System.out.println("Servicio info Articulos.");
        System.out.println("****************************************************");
        System.out.println(responseBody(url));

        url = server+"articulos/v1/save?"
                + "descripcion=Sandalia&tipo=CALZADO&color=negro&talle_num=3"
                + "&stock=10&stockMin=10&stockMax=30&costo=90&precio=200&temporada=VERANO";
     
               // String descripcion, Tipo tipo, String color, String talle_num, int stock, int stockMin, int stockMax, double costo, double precio, Temporada temporada 
                
        System.out.println("****************************************************");
        System.out.println("Servicio Alta Articulos.");
        System.out.println("****************************************************");
        System.out.println(responseBody(url));

        url = server+"/articulos/v1/remove?id=1";
        System.out.println("****************************************************");
        System.out.println("Servicio Baja Articulos.");
        System.out.println("****************************************************");
        System.out.println(responseBody(url));

        url = server+"articulos/v1/all";
        System.out.println("****************************************************");
        System.out.println("Servicio ArticuloAll.");
        System.out.println("****************************************************");
        System.out.println(responseBody(url));

        url = server+"articulos/v1/byId?id=3";
        System.out.println("****************************************************");
        System.out.println("Servicio ArticuloById.");
        System.out.println("****************************************************");
        System.out.println(responseBody(url));
        
        url = server+"articulos/v1/likeDescripcion?descripcion=re";
        System.out.println("****************************************************");
        System.out.println("Servicio ArticuloLikeDescripcion.");
        System.out.println("****************************************************");
        System.out.println(responseBody(url));

        //Armar List JSon
        Type listType=new TypeToken<List<Articulo>>(){}.getType();
        url = server+"articulos/v1/all";
        System.out.println("****************************************************");
        System.out.println("List<Articulo>");
        System.out.println("****************************************************");
        List<Articulo> list = new Gson()
                .fromJson(responseBody(url), new TypeToken<List<Articulo>>() {
                }.getType());
        list.forEach(System.out::println);
        */
    
    
    }
    
     private static String session(String url, String user, String pass, String comando) {
        //create the http client
        // https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
        String resp = "";
        try {

            HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

            //add reuqest header
            httpClient.setRequestMethod("POST");
            httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "user=" + user + "&pass=" + pass;

            // Send post request
            httpClient.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            }

            int responseCode = httpClient.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpClient.getInputStream()))) {

                String line;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("-----------------------------------------");
                System.out.println(response.toString());
                System.out.println("-----------------------------------------");
                resp = response.toString();
            }
              System.out.println(responseBody(comando));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return resp;
    }

    private static String responseBody(String url) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        response.headers().map().forEach((k, v) -> System.out.println(k + " " + v));
        return response.body();
    }

}
