package edu.enphasis.examplesocket.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author javhur
 */
public class ServidorTcp {
    private ServerSocket server;
    private static final int PORT = 5000;
    private boolean active = true;
    private Socket client;
    private DataOutputStream out ;
    private BufferedReader in;
    private ArrayList<Service> list;  //cuando se crea un hilo lo agrego
    
    public static void main(String args[]) {
        ServidorTcp app = new ServidorTcp();
        if (app.startServer()) {//si el servidor pudo comensar estonces que espere las conexiones
            app.waitConnection();
        }
    }
    
    public boolean startServer() {
        try {
            System.out.println("[server] Iniciando servidor...");
            server = new ServerSocket(PORT);
            System.out.println("[server] Servidor esperando en puerto "+PORT);
            return true;
        } catch(IOException e) {
            return false;
        }
    }

    public void waitConnection() {
        list = new ArrayList();
        while(active) {
            try {
                System.out.println("[server] Esperando conexiones...");
                client = server.accept();
                
                
                Service service = new Service(client);
                service.start();//verifica que el hilo esta creado
                list.add(service);
                System.out.println("[server] Cliente conectado en "+client.getLocalPort()+" desde..."+client.getInetAddress().getHostAddress()+":"+client.getPort());
            
             in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             out = new DataOutputStream(client.getOutputStream());
                
            String s;
            s=in.readLine();
            while(s!=null){
                  
                 System.out.println(" [server]  Mensaje recibido"+s);
                System.out.println("HTTP/1.0 100 OK \r\n");
                 //System.out.println("<html>hola mundo </html>\r\n");
                 
            }
           
             
            
            
            } catch(IOException e) {
                //No hace nada
            }
        }
    }
}
