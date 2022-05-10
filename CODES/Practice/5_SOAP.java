package org.me.stringmanip;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "stringpop")
@Stateless()
public class Stringop
{
	@WebMethod(operationName = "concat")
	public String concat(@WebParam(name = "a")String a, @WebParam(name="b")String b)
	{
		String res = a.concat(b);
		return res;
	}

	@WebMethod(operationName = "length")
	public String length(@WebParam(name= "a")String a)
	{
		return a.length();
	}

	@WebMethod(operationName="upper")
	public String upper(@WebParam(name="a")String a)
	{
		return a.toUpperCase();
	}

	@WebMethod(operationName="lower")
	public String lower(@WebParam(name="a")String a)
	{
		return a.toLowerCase();
	}

	@WebMethod(operationName="Compare")
	public String compare(@WebParam(name="a")String a,@WebParam(name="b")String b)
	{
		int comp = a.compareTo(b);
		String msg = "";
		if(comp==0)
		{
			msg="Both are equal";
		}
		else if(comp<0)
		{
			msg= "Second string is lexicologically greater";
		}
		else
		{
			msg = "First String is lexicologically greater";
		}
		return msg;
	}
}
