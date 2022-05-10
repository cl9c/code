
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("demo")
public class Demo 
{
	
	@GET
	@Path("hello/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello(@PathParam("name")String name) 
	{
		return "Hello "+name;
	}
}

ApplicationConfig.java

import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application
{
  @Override
  public Set<Class<?>> getClasses()
  {
	  Set<Class<?>> resources = new java.util.HashSet<>();
	  addRestResourceClasses(resources);
	  return resources;
  }
  
  private void addRestResourceClasses(Set<Class<?>> resources) 
  {
	  resources.add(  Demo.class);
  }
}
//*******************************************************//
package org.me.stringopsrest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("generic")
public class GenericResource 
{

    @Context
    private UriInfo context;

    public GenericResource()
    {
    
    }

    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() 
    {
        //TODO return proper representation object
        return "<hi>Hello</hi>";
			  //new UnsupportedOperationException();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) 
    {
    
    }
    
    @GET
    @Path("/concat/{a},{b}")
    public String concat(@PathParam("a") String a,@PathParam("b") String b)
    {
        String res = a.concat(b);
        return res;
    }
    
    @GET
    @Path("/compare/{a},{b}")
    public String compare(@PathParam("a") String a,@PathParam("b") String b)
    {
        int comp = a.compareTo(b);
        String msg = "";
        if(comp==0)
        {
            msg = "Both are equal";
        }
        else if(comp<0)
        {
            msg = "Second string is lexicographically greater";
        }
        else
        {
            msg = "First string is lexicographically greater";
        }
        return msg;
    }
    
    @GET
    @Path("/length/{a}")
    public int length(@PathParam("a") String a)
    {
        return a.length();
    }
    
    @GET
    @Path("/upper/{a}")
    public String upper(@PathParam("a") String a) {
        //TODO write your implementation code here:
        return a.toUpperCase();
    }
    
    @GET
    @Path("/lower/{a}")
    public String lower(@PathParam("a") String a) {
        //TODO write your implementation code here:
        return a.toUpperCase();
    }
}

