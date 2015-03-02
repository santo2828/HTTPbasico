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

/**
 *
 * @author PC-USUARIO
 */
public class Service  extends Thread{
    private Socket socket;
    private BufferedReader buf;
    private boolean stop=false;
    
    public Service(Socket socket){
        System.out.println("[service] servicio iniciado");
        this.socket = socket;
    }   


   public void run(){
       String msg;
       try{
           buf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           System.out.println("listo para escribir mensaje");
           while(!stop){
               System.out.println("ESperando mensaje...");
               msg=buf.readLine();
               System.out.println("desde:" +socket.getInetAddress().getHostAddress() + "Mensaje: " +msg);
               if (msg.equals("exit")){
               stop = true;
               }
              
            }
        
           
        }catch(IOException e){}
    }  
    
}
