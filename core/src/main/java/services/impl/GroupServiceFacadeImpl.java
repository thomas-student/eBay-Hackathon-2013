package services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import resources.Group;
import resources.Post;
import resources.User;
import services.GroupServiceFacade;

public class GroupServiceFacadeImpl extends GroupServiceFacade {

	@Override
	public Group getGroupById(long id) {
		Connection conn = AWSConnector.getConnection();
		try{
		ResultSet orig = conn.prepareStatement("SELECT * FROM groups WHERE id="+id).executeQuery();
		if(orig.next())
			{
			Group group = new Group(orig.getString("name"), orig.getString("description"), orig.getLong("id"));
			ResultSet users = conn.prepareStatement("SELECT userId FROM groupMappings WHERE groupId="+id).executeQuery();
			while(users.next())
				group.addUser(new UserServiceFacadeImpl().getUserById(users.getLong("userId")));

			ResultSet posts = conn.prepareStatement("SELECT postId FROM groupPostMappings WHERE groupId="+id).executeQuery();
			while(users.next())
				group.addPost( getPostById(users.getLong("postId")));
			return group;
			}
		else
			return null;
					}
		catch(Exception e){
			return null;
		}
	}
	
	public Post getPostById(long id){
		try{
		Connection conn = AWSConnector.getConnection();
		ResultSet posts = conn.prepareStatement("SELECT * FROM posts WHERE id="+id).executeQuery();
		return new Post(posts.getLong("id"),  posts.getString("content"), new UserServiceFacadeImpl().getUserById(posts.getLong("id")), posts.getLong("timestamp"));
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean createNewGroup(String name, String description, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return conn.prepareStatement("INSERT INTO meet_db.groups (name, description) VALUES ('"+name+"', '"+description+"')").execute();
		}
		catch(Exception e){
			return false;
		}
		
	}

	@Override
	public boolean changeName(long id, String name, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return conn.prepareStatement("UPDATE meet_db.groups SET name = '"+name+"' WHERE id="+id).execute();
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean changeDescription(long id, String description, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return conn.prepareStatement("UPDATE meet_db.groups SET description = '"+description+"' WHERE id="+id).execute();
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean addMember(long id, long userId) {
		Connection conn = AWSConnector.getConnection();
		try{
		return conn.prepareStatement("INSERT meet_db.groupMappings (userId, groupId) VALUES ("+userId+", "+id+")").execute();
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
		return conn.prepareStatement("INSERT INTO meet_db.groupPostMappings (groupId, postId) VALUES ("+id+", "+key+")").execute();
		}
		catch(Exception e){
			return false;
		}

	}

}
