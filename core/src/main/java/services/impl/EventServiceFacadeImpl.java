package services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import resources.Event;
import resources.Response;
import services.EventServiceFacade;

public class EventServiceFacadeImpl extends EventServiceFacade {

	@Override
	public Event getEventById(long id) {
		Connection conn = AWSConnector.getConnection();
		try{
		ResultSet orig = conn.prepareStatement("SELECT * FROM events WHERE id="+id).executeQuery();
		if(orig.next())
			{
			Event event = new Event(orig.getLong("id"), orig.getString("name"),
					orig.getLong("date"), orig.getString("location"));
			
			ResultSet users = conn.prepareStatement("SELECT * FROM responses WHERE eventId="+id).executeQuery();
			while(users.next())
				if(users.getBoolean("attending"))
					event.addAttending(new Response(new UserServiceFacadeImpl().getUserById(users.getLong("id")), users.getString("content"), users.getLong("id")));
				else
					event.addNotAttending(new Response(new UserServiceFacadeImpl().getUserById(users.getLong("id")), users.getString("content"), users.getLong("id")));
			
			return event;
			}
		else
			return null;
					}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean createNewEvent(String name, long date, String location) {
		Connection conn = AWSConnector.getConnection();
		try{
		return conn.prepareStatement("INSERT INTO meet_db.events (name, date, location) VALUES ('"+name+"', "+date+", '"+location+"')").execute();
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean changeName(long id, String name, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return 1 == conn.prepareStatement("UPDATE meet_db.events SET name = '"+name+"' WHERE id="+id).executeUpdate();
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean changeDate(long id, long date, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return 1 == conn.prepareStatement("UPDATE meet_db.events SET date = "+date+" WHERE id="+id).executeUpdate();
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean changeLocation(long id, String location, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return 1 == conn.prepareStatement("UPDATE meet_db.events SET location = '"+location+"' WHERE id="+id).executeUpdate();
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean removeAdmin(long id, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return 1==conn.prepareStatement("DELETE meet_db.eventAdminMappings WHERE userId='"+userId+"' AND eventId="+id).executeUpdate();
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean addAdmin(long id, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return conn.prepareStatement("INSERT INTO meet_db.eventAdminMappings (userId, eventId) VALUES ("+userId+", "+id+")").execute();
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean addResponse(long id, String response, long userId,
			boolean attending) {
		Connection conn = AWSConnector.getConnection();
		try{
		PreparedStatement s = conn.prepareStatement("INSERT INTO meet_db.responses ( userId, response, attending, eventId) VALUES ("+userId+", '"+response+"'" +
				", "+attending+", "+id+")");
		boolean insert = s.execute();
		return insert;
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean addPost(long id, long userId, String content, long timestamp) {
		Connection conn = AWSConnector.getConnection();
		try{
		PreparedStatement s = conn.prepareStatement("INSERT INTO meet_db.posts (content, userId, timestamp) VALUES ('"+content+"', "+userId+", "+timestamp+")");
		boolean insert = s.execute();
		if(!insert)
			return false;
		long key = s.getGeneratedKeys().getLong(1);
		return conn.prepareStatement("INSERT INTO meet_db.eventPostMappings (groupId, postId) VALUES ("+id+", "+key+")").execute();
		}
		catch(Exception e){
			return false;
		}

	}

}
