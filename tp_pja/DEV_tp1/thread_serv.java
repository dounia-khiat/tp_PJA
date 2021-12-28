package bean;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class thread_serv {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			final ServerSocket serveurSocket  ;
		     final Socket clientSocket ;
		     final BufferedReader in;
		     final PrintWriter out;
		     final Scanner sc=new Scanner(System.in);
		     serveurSocket = new ServerSocket(5000);
		       clientSocket = serveurSocket.accept();
		       out = new PrintWriter(clientSocket.getOutputStream());
		       in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
		       Thread envoi= new Thread(new Runnable() {
		          String msg;
		          @Override
		          public void run() {
		             while(true){
		                msg = sc.nextLine();
		                out.println(msg);
		                out.flush();
		             }
		          }
		       });
		       envoi.start();
		   
		       Thread recevoir= new Thread(new Runnable() {
		          String msg ;
		          @Override
		          public void run() {
		             try {
		                msg = in.readLine();
		                while(msg!=null){
		                   System.out.println("Client : "+msg);
		                   msg = in.readLine();
		                }		                
		                System.out.println("Client d�conect�");               
		                out.close();
		                clientSocket.close();
		                serveurSocket.close();	     
		}
		catch(Exception v)
		{
			v.printStackTrace();
		}
		          }});
		       

		       recevoir.start();
	      }catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	}

		       