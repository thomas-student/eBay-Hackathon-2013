package rest;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import resources.Attendance;
import resources.Event;
import resources.Post;
import resources.Response;
import resources.User;
import services.EventServiceFacade;
import services.impl.EventServiceFacadeImpl;

@Path("event")
public class EventController {
	
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Event getEventById(@PathParam("id") long id){
		EventServiceFacade service = new EventServiceFacadeImpl();
		Event event = service.getEventById(id);
		return event;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String createNewEvent(@QueryParam("name") String name, @QueryParam("date") long date, 
			@QueryParam("location") String location){
		EventServiceFacade service = new EventServiceFacadeImpl();
		if(service.createNewEvent(name, date, location))
			return "Success!";
		else
			return "Failed";
	}
	
	@PUT
	@Path("{id}")
	public void editFields(@PathParam("id") long id, @QueryParam("name") String name, @QueryParam("date") long date, 
			@QueryParam("location") String location, @QueryParam("userId") long userId){
		EventServiceFacade service = new EventServiceFacadeImpl();
		if(name != null)
			service.changeName(id, name, userId);
		if(date != 0)
			service.changeDate(id, date, userId);
		if(location != null)
			service.changeLocation(id, location, userId);
	}
	
	@DELETE
	@Path("{id}/admin")
	public void removeAdmin(@PathParam("id") long id, @QueryParam("userId") long userId){
		EventServiceFacade service = new EventServiceFacadeImpl();
		service.removeAdmin(id, userId);
	}
	
	@PUT
	@Path("{id}/admin")
	public void addAdmin(@PathParam("id") long id, @QueryParam("userId") long userId){
		EventServiceFacade service = new EventServiceFacadeImpl();
		service.addAdmin(id, userId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/admin")
	public List<User> getAdmins(@PathParam("id") long id, @QueryParam("userId") long userId){
		EventServiceFacade service = new EventServiceFacadeImpl();
		Event event = service.getEventById(id);
		return event.getAdmins();
	}

	@GET
	@Path("{id}/response")
	@Produces(MediaType.APPLICATION_JSON)
	public Attendance getResponses(@PathParam("id") long id, @QueryParam("userId") long userId){
		EventServiceFacade service = new EventServiceFacadeImpl();
		Event event = service.getEventById(id);
		Attendance attendance  = new Attendance();
		for(Response r : event.getAttending())
			attendance.addAttendingResponse(r);
		for(Response r : event.getNotAttending())
			attendance.addNotAttendingResponse(r);
		return attendance;
	}

	@PUT
	@Path("{id}/response")
	public void addResponse(@PathParam("id") long id, @QueryParam("userId") long userId, 
			@QueryParam("response") String response, @QueryParam("attending") boolean attending){
		EventServiceFacade service = new EventServiceFacadeImpl();
		service.addResponse(id, response, userId, attending);
	}
	
	@GET
	@Path("{id}/posts")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Post> getPosts(@PathParam("id") long id){
		EventServiceFacade service = new EventServiceFacadeImpl();
		Event event = service.getEventById(id);
		if(event == null)
			return null;
		return event.getPosts();
	}

	@PUT
	@Path("{id}/posts")
	public void addPost(@PathParam("id") long id, @QueryParam("userId") long userId, @QueryParam("content") String content, 
			@QueryParam("timestamp") long timestamp){
		EventServiceFacade service = new EventServiceFacadeImpl();
		service.addPost(id, userId, content, timestamp);
	}
	
}
