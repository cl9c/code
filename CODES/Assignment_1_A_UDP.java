
UDPClient.java

import java.net.*;  
import java.io.*;

public class UDPClient
{  

  public static void main(String[] args) throws Exception 
  {  
        /* * send to the server */
        //1. Define the server address, port number, Data package
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 8800;
        
        BufferedReader keyboard= new BufferedReader(new InputStreamReader(System.in));
        
        String message;
	
	    System.out.println("Enter the degrees : ");
	    message = keyboard.readLine();
        byte[] data = message.getBytes();
        //2. Create a data package, including Message sent
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        //3. Create DatagramSocket object
        DatagramSocket socket = new DatagramSocket();
        
   
        //4. Send data packet to the server
        System.out.println("The client is sending data to the server");
        socket.send(packet);
        /* * Receive server-side data */
        
        //1. Create a data packet for receiving response data 
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        //2. Receive data
      
        socket.receive(packet2);
     
        //3. Read data
        String reply = new String(data2);
        
        System.out.println("Client starts receiving data");
        System.out.println("Radians : "+reply);
        //4. Close the resource
        socket.close();

  } 
  
}  

UDPServer.java

import java.net.*;  
public class UDPServer
{  
  public static void main(String[] args) throws Exception 
    {   

        //1. Create server-side DatagramSocket, specify port
        DatagramSocket socket = new DatagramSocket(8800);
        //2. Create a data packet to receive the data sent by the client 
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        //3. Receive data sent by the client
        System.out.println("------ The server has been started, waiting for client data ----- -");
        //loop monitoring 
        while(true)
        {
            socket.receive(packet);//This method will block until a data packet is received 
            //open a thread to communicate with the client
            UDPServerThread thread = new UDPServerThread(data,socket, packet);
            thread.start();
        }

        //4. Close the resource 
	//socket.close();
    }
}

UDPServerThread.java

import java.net.*;
import java.io.*;

public class UDPServerThread extends Thread{
    DatagramSocket socket = null;
    byte[] data = null;
    int port;
    InetAddress address;

    public UDPServerThread(byte[] data,DatagramSocket socket,DatagramPacket packet) 
    {
        this.socket = socket;
        this.data = data;
        address = packet.getAddress();
        port = packet.getPort();
    }
    //Method to be executed by the thread 
    public void run()
    {
        //4. Read data
                System.out.println("Thread start");
                String info = new String(data);
                System.out.println(info);
                /* * Respond to the client */
		             
                int deg = (((int)info.charAt(0))-48)*10+ ((int)info.charAt(1))-48;
	            double rad = Math.toRadians(deg);
		
		  
		        String output = Double.toString(rad);
                byte[] data2 = output.getBytes();
                
                //2 create a data packet, including response information
                DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
                //3. Respond to client 
               
                try {
                    socket.send(packet2);
                    System.out.println("The server-side data has been sent");
                } catch (IOException e) {
                    //TODO Auto-generated catch block
                    e.printStackTrace();
                }finally{
              
                }
                
    }
}
 
 



