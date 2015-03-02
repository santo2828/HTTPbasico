package edu.enphasis.examplesocket.client;

import edu.enphasis.examplesocket.server.ServidorTcp;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente TCP
 * @author javhur
 */
public class ClientTcp {
    private Socket client;
    private DataOutputStream out;
    private BufferedReader in;
    
    public static void main(String args[]) {
        ClientTcp app = new ClientTcp();
        if (app.connect()) {
            app.sendMessage();
        }
    }
    
    public boolean connect() {
        try {
            System.out.println("[client] Cliente conectando con servidor...");
            client = new Socket("192.168.122.61", 5000);
            System.out.println("[client] Cliente conectado.");
            out = new DataOutputStream(client.getOutputStream());
            return true;
        } catch(UnknownHostException e) {
            return false;
        } catch(IOException e) {
            return false;
        }
    }

    public void sendMessage() {
        ServidorTcp app =new ServidorTcp();
       
        
        
        
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
             
               System.out.println("[client] Mensaje para enviar: ");
               out.writeUTF("GET /HTTP/1.1\n");
               out.writeUTF("Host: 192.168.122.61\n");
               out.writeUTF("\r\n");
               System.out.println("[client] Mensaje enviado.");
               
        
            
            
        } catch (IOException ex) {
            System.out.println("Error InputStreamReadoer");
        }
        
        
    }
    
}
