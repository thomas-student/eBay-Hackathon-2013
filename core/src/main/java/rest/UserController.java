package rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import resources.User;
import services.UserServiceFacade;
import services.impl.UserServiceFacadeImpl;

@Path("user")
public class UserController {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") long id){
		UserServiceFacade service = new UserServiceFacadeImpl();
		User event = service.getUserById(id);
		return event;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String createNewUser(@QueryParam("name") String name, @QueryParam("phoneNumber") long phoneNumber){
		UserServiceFacade service = new UserServiceFacadeImpl();
		if(service.createNewUser(name, phoneNumber))
			return "Success!";
		else
			return "Failed";
	}
	
	@PUT
	@Path("{id}")
	public void changeName(@PathParam("id") long id, @QueryParam("name") String name){
		UserServiceFacade service = new UserServiceFacadeImpl();
		service.changeName(id, name);
	}

	
}
