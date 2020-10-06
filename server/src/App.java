import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Instânciando servidor...");
        ServerSocket server = new ServerSocket(5555);

        System.out.println("Esperando Conexão...");
        // Esperar conexão...
        Socket connection = server.accept();

        System.out.println("Conexão chegou!");

        // Pega os dados que estão chegando
        InputStream inBytes = connection.getInputStream();

        InputStreamReader leitorBytes = new InputStreamReader(inBytes);

        //Colocar os bytes em um objeto que entenda eles
        BufferedReader leitorString = new BufferedReader(leitorBytes);

        String request = "";

        while(true){
            String line = leitorString.readLine();
            
            if (line.isEmpty()){
                break;
            }
            
            request += line + "\n";
        }

        System.out.println(request);

        String responseHttp = "HTTP/1.1 200 OK\n"
            + "Access-Control-Allow-Origin: *\n"
            + "Content-Type: text/html\n\n"
            + request;


        byte[] bytesResponse = responseHttp.getBytes();

        connection.getOutputStream().write(bytesResponse);

        System.out.println("Fechando Servidor...");
        server.close();

    }
}
