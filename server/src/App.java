import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Instânciando servidor...");
        ServerSocket server = new ServerSocket(5555);

        System.out.println("Esperando Conexão...");
        //Esperar conexão...
        Socket connection = server.accept();

        System.out.println("Conexão chegou!");

        int nBytes = connection.getInputStream().read(); 

        String response = "HTTP/1.1 200 OK\n"
            + "Content-Type: text/html\n\n"
            + "<h1>Bytes Recebidos: " + nBytes +"</h1>";
            
        byte[] bytesResponce = response.getBytes();

        connection.getOutputStream().write(bytesResponce);

        System.out.println("Fechando Servidor...");
        server.close();

    }
}
