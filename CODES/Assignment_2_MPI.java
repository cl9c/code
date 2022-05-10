

import mpi.*;

public class MPI_Deg_to_Rad
{
	
	public static void main(String[] args)throws Exception
	{
	
		MPI.Init(args);
		
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		
		if(rank == 0 )
		{
			int degrees[] = {30,40,50,60};
			double radians[] = new double[1];
			
			for (int i=1;i<size;i++)
				MPI.COMM_WORLD.Send(degrees,0,4,MPI.INT,i,1);
				
			System.out.println("Degrees Radians");
			for (int i=1;i<size;i++)
			{
				MPI.COMM_WORLD.Recv(radians,0,1,MPI.DOUBLE,i,2);
				System.out.println(degrees[i-1]+"       "+radians[0]);
			}	
				
		}
		else
		{
			int degrees[]=new int[4];
			MPI.COMM_WORLD.Recv(degrees,0,4,MPI.INT,0,1);
			
			double rad[] = new double[1];
			rad[0]  = Math.toRadians(degrees[rank-1]);
			
			MPI.COMM_WORLD.Send(rad,0,1,MPI.DOUBLE,0,2);
		}

		MPI.Finalize();
	}
}


Output


 



