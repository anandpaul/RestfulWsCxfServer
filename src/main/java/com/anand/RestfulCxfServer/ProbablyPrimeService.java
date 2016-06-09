/**
 * 
 */
package com.anand.RestfulCxfServer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Anand
 *
 */
@Service
@Path("/prime")
public class ProbablyPrimeService {

	@GET
	@Path("/{number}")
	@Produces({ "text/plain" })
	public String isPrime(@PathParam("number") int number)
	{
		System.out.println("Inside the method to check prime");
		return "Entered number is " + number+". Is this the prime number???";
	}
}
