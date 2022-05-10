Conversioninterface.java

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConversionInterface extends Remote{
	public double convert(double n) throws RemoteException;
}


Implementer.java

import java.rmi.*;
import java.rmi.server.*;

public class Implementer extends UnicastRemoteObject implements ConversionInterface
{
	public Implementer() throws Exception{
		super();
	}

	public double convert(double n){
		double n_mts = n/100;
		return n_mts;
	}
}


Server.java

import rmi.*;
import rmi.registry.*;

public class Server
{
	public staic void main(String[] args) throws ExceptionInInitializerError
	{
		Implementer obj = new Implementer();
		Naming.rebind("CONVERTER",obj);
		System.out.println("--SERVER STARTED");
	}
	
}


Client.java

import rmi.*;
import rmi.util.*;

public class Client
{
	public staic void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the distance in Centimeters:")
		double n = sc.nextDouble();

		ConversionInterface c_obj = (ConversionInterface)Naming.lookup("CONVERTER");
		double n_mts = c_obj.convert(n);

		System.out.println("Distance in meters: "+ n_mts);
	}
}










