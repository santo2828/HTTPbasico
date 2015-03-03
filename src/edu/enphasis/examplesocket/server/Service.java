/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.enphasis.examplesocket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC-USUARIO
 */
public class Service  extends Thread{
    private Socket socket;
    private BufferedReader buf;
    private boolean stop=false;
    private BufferedReader comando;
    Thread thread;
    
    public Service(Socket socket){
        System.out.println("[service] servicio iniciado");
        this.socket = socket;
    }   
   

   public void run(){
       String msg;
       String comparer;
      String[] parts ;
       try{
           buf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            comando=new BufferedReader(new InputStreamReader(System.in));
           
               System.out.println("Esperando mensaje...");
               msg=buf.readLine();
               
               
      //StringBuilder response = new StringBuilder(); 
             
             
     while (msg!=null){
         
     // while ( msg.equals("\r\n")){
        //response.append(msg);
        //response.append('\r');
       // System.out.println(msg);
             //System.out.println(" [server]  Mensaje recibido es "+ msg);
      parts =msg.split("\\s+");
      if("GET".equals(parts[0])){
          java.util.Date fecha = new Date();
       System.out.println("HTTP/1.1 200 OK\r\n"+fecha+"\r\n"+"<title>Página de web<title>");
       
       
       
               System.out.println("desde:" +socket.getInetAddress().getHostAddress() + "  Mensaje: " +msg);
               
               stop = true;
               Thread.sleep(5000000); //evito seguir leyendo lo mismo del hilo
               if (msg.equals("exit")){
               stop = true;
                 
            }
     
      
                 
     //}  
               
               
      } 
      else {
       System.out.println("HTTP/1.1 404 Not Found\r\n");
       
      
      }
     }
        
           
        }catch(IOException e){
        
        } catch (InterruptedException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
}
