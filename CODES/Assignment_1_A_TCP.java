
Client.java

import java.net.*;
import java.io.*;

public class Client
{
 	
 	private Socket socket	=null;
 	private PrintWriter out = null;
 	
 	public Client(String address,int port) throws IOException
 	{
 	
 		socket = new Socket(address,port);
 		System.out.println("Connected");
 		
 		out = new PrintWriter(socket.getOutputStream(), true);
 		BufferedReader keyboard= new BufferedReader(new InputStreamReader(System.in));
 		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	 	
	 	
	 	String message;
	 	
	 	
	 	System.out.println(">");
	 	message = keyboard.readLine();
	 	
	 	out.println(message);
	 	
	 	String rad = in.readLine();
	 	
	 	System.out.println(rad);
	 	
	 	out.close();
	 	keyboard.close();
	 	socket.close();
	 	
 	}
 	public static void main(String args[]) throws IOException
 	{
 		Client client = new Client("127.0.0.1",5000);
 		
 	}
 }
 	
 		

Server.java

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Server
{

	private static ArrayList<ClientHandler> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	public static void main(String args[]) throws IOException
	{
	  
		ServerSocket socket = new ServerSocket(5000);
		
		try
		{
	
			while(true)
			{
				Socket client = socket.accept();
				System.out.println("Connected");
			
				ClientHandler clientThread = new ClientHandler(client);
			
				clients.add(clientThread);
			
				pool.execute(clientThread);
			
			}
		
		}
		
		finally
		{
			socket.close();
		}
		
	}
}


ClientHandler.java

import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable{

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientHandler(Socket clientSocket) throws IOException
	{
		this.client = clientSocket;
		
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(),true);
	}
	
	@Override
	public void run()
	{
	
		String message="";
	
		try
		{
			message= in.readLine();
		
		}
		
		catch(IOException i)
		{
			System.out.println(i);
		}
		System.out.println(message);
		
		int deg = Integer.parseInt(message);
		double rad = Math.toRadians(deg);
		System.out.println(rad);
		
		String output = Double.toString(rad);
		out.println(output);
		
		try
		{
			in.close();
			out.close();
			client.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}
}


 


 

