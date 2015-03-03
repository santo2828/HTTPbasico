package edu.enphasis.examplesocket.client;

import edu.enphasis.examplesocket.server.ServidorTcp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
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
            client = new Socket("192.168.0.101", 5000);
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
       
       
        
        String path = "/index.html";
        
     
        
        try {
           
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
             String params = URLEncoder.encode("param1", "UTF-8")  + "=" + URLEncoder.encode("value1", "UTF-8");
            params += "&" + URLEncoder.encode("param2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");
                
            String msg1 = "GET "+path+" HTTP/1.1\r\n";
            String msg2 = "Content-Length: "+params.length()+"\r\n";
           String msg3="Content-Type: application/x-www-form-urlencoded\r\n";
           
               BufferedWriter writer  = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));
               
                writer.write(msg1);

                writer.write(msg2);
                writer.write(msg3);
                writer.write("\r\n");
                writer.write(params);
                System.out.println("[client] Mensaje para enviar....."+msg1+msg2+msg3);
                writer.close();
              // out.writeUTF("");
               //out.writeUTF("\r\n");
               System.out.println("[client] Mensaje enviado.");
               
        
            
            
        } catch (IOException ex) {
            System.out.println("Error InputStreamReadoer");
        }
        
        
    }
    
}
