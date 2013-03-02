package rest;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import resources.Group;
import resources.Post;
import resources.User;
import services.GroupServiceFacade;
import services.impl.GroupServiceFacadeImpl;

@Path("group")
public class GroupController {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Group getGroupById(@PathParam("id") long id){
		GroupServiceFacade service = new GroupServiceFacadeImpl();
		Group group = service.getGroupById(id);
		return group;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String createNewGroup(@QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("userId") long userId){
		GroupServiceFacade service = new GroupServiceFacadeImpl();
		if(service.createNewGroup(name, description, userId))
			return "Success!";
		else
			return "Failed";
	}
	
	@PUT
	@Path("{id}")
	public void changeGroupFields(@PathParam("id") long id, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("userId") long userId){
		GroupServiceFacade service = new GroupServiceFacadeImpl();
		if(name != null)
			service.changeName(id, name, userId);
		if(description != null)
			service.changeDescription(id, description, userId);
	}
	
	@GET
	@Path("{id}/members")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getMembers(@PathParam("id") long id){
		GroupServiceFacade service = new GroupServiceFacadeImpl();
		Group group = service.getGroupById(id);
		if(group == null)
			return null;
		return group.getUsers();
	}

	@PUT
	@Path("{id}/members")
	public void addMember(@PathParam("id") long id, @QueryParam("userId") long userId){
		GroupServiceFacade service = new GroupServiceFacadeImpl();
		service.addMember(id, userId);
	}
	
	@GET
	@Path("{id}/posts")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Post> getPosts(@PathParam("id") long id){
		GroupServiceFacade service = new GroupServiceFacadeImpl();
		Group group = service.getGroupById(id);
		if(group == null)
			return null;
		return group.getPosts();
	}

	@PUT
	@Path("{id}/posts")
	public void addPost(@PathParam("id") long id, @QueryParam("userId") long userId, @QueryParam("content") String content, 
			@QueryParam("timestamp") long timestamp){
		GroupServiceFacade service = new GroupServiceFacadeImpl();
		service.addPost(id, userId, content, timestamp);
	}
	
}
