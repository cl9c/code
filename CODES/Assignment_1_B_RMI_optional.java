

DegtoRad.java

import java.rmi.*;

public interface DegtoRad extends Remote
{
	
	public double toRad(double deg)throws RemoteException;
}
	
DegtoRadRemote.java

import java.rmi.*;
import java.rmi.server.*;

public class DegtoRadRemote extends UnicastRemoteObject implements DegtoRad
{

	DegtoRadRemote()throws RemoteException
	{
		super();
	}
	
	public double toRad(double deg)
	{
		return Math.toRadians(deg);
	}
}

RmiServer.java

import java.rmi.*;
import java.rmi.registry.*;

public class RmiServer
{

	public static void main(String args[])
	{
		try
		{
			DegtoRad stub = new DegtoRadRemote();
			Naming.rebind("rmi://localhost:5000/conv",stub);
		}
	catch(Exception e){System.out.println(e);}

	}
}

RmiClient.java

import java.rmi.*;
import java.util.Scanner;

public class RmiClient
{
	public static void main(String args[])
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter degrees : ");
			double deg = sc.nextDouble();
			
			
			DegtoRad stub= (DegtoRad)Naming.lookup("rmi://localhost:5000/conv");
			System.out.println(stub.toRad(deg));
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

Output







